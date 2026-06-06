package net.ncplanner.ncpf;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Stream;
import net.ncplanner.ncpf.structure.NcpfRoot;

public class NcpfTester {

    public static void main(String[] args) {
        Path testDir = Paths.get("test", "files");

        if (!Files.exists(testDir) || !Files.isDirectory(testDir)) {
            System.err.println("Directory 'test/files' does not exist.");
            return;
        }

        try (Stream<Path> paths = Files.walk(testDir)) {
            paths.filter(Files::isRegularFile)
                 .filter(p -> p.toString().endsWith(".json"))
                 .forEach(NcpfTester::testJsonMutation);
        } catch (IOException e) {
            System.err.println("Error walking the test directory: " + e.getMessage());
        }
    }

    private static void testJsonMutation(Path filePath) {
        System.out.println("Testing: " + filePath.getFileName());
        
        try {
            String rawBaselineJson = Files.readString(filePath);

            // 1. Map out Baseline structure
            Map<String, StructureContext> baselineMap = new HashMap<>();
            JsonElement baseElement = JsonParser.parseString(rawBaselineJson);
            flattenTree(baseElement, "", baselineMap);

            // 2. Parse into your object model
            NcpfRoot ncpf;
            try (JsonReader jsonReader = new JsonReader(new StringReader(rawBaselineJson))) {
                ncpf = Parser.parseNcpf(jsonReader);
            }

            // 3. Serialize back out and map Parsed structure
            String rawParsedJson = Parser.gson.toJson(ncpf);
            Map<String, StructureContext> parsedMap = new HashMap<>();
            JsonElement parsedElement = JsonParser.parseString(rawParsedJson);
            flattenTree(parsedElement, "", parsedMap);

            // 4. Structural Map Comparison
            boolean match = compareStructures(baselineMap, parsedMap);
            
            if (match) {
                System.out.println("  [PASS] No structural or value mutations detected.");
            }

        } catch (Exception e) {
            System.err.println("  [FAIL] Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Recursively walks the Gson tree structure, capturing paths and preserving 
     * a snapshot of the current parent node's schema layout.
     */
    private static void flattenTree(JsonElement element, String path, Map<String, StructureContext> outMap) {
        if (element.isJsonObject()) {
            JsonObject obj = element.getAsJsonObject();
            TreeSet<String> keys = new TreeSet<>(obj.keySet());
            String contextSnippet = obj.toString(); // Real JSON structure in memory
            
            // Register the object container itself
            outMap.put(path, new StructureContext(keys, contextSnippet, true));
            
            for (String key : keys) {
                flattenTree(obj.get(key), path + "/" + key, outMap);
            }
        } else if (element.isJsonArray()) {
            var array = element.getAsJsonArray();
            TreeSet<String> indices = new TreeSet<>();
            for (int i = 0; i < array.size(); i++) {
                indices.add("[" + i + "]");
            }
            
            outMap.put(path, new StructureContext(indices, "Array Size: " + array.size(), false));
            
            for (int i = 0; i < array.size(); i++) {
                flattenTree(array.get(i), path + "[" + i + "]", outMap);
            }
        } else {
            // Leaf node (Value matching)
            TreeSet<String> valueSet = new TreeSet<>();
            valueSet.add(element.getAsString());
            outMap.put(path, new StructureContext(valueSet, element.toString(), false));
        }
    }

    private static boolean compareStructures(Map<String, StructureContext> baseline, Map<String, StructureContext> parsed) {
        TreeSet<String> allPaths = new TreeSet<>(baseline.keySet());
        allPaths.addAll(parsed.keySet());

        int errors = 0;
        for (String path : allPaths) {
            StructureContext baseCtx = baseline.get(path);
            StructureContext parseCtx = parsed.get(path);

            // Case 1: An entire structural path disappeared from the parser
            if (baseCtx != null && parseCtx == null) {
                // If the parent path exists, let the parent handle the missing key error to prevent spam
                String parentPath = path.contains("/") ? path.substring(0, path.lastIndexOf('/')) : "";
                if (parentPath.isEmpty() || parsed.containsKey(parentPath)) {
                    System.err.println("\n[STRUCTURAL MUTATION] Path dropped or mismatched!");
                    System.err.println("  Path location: " + (path.isEmpty() ? "ROOT" : path));
                    System.err.println("  Expected keys/structure here: " + baseCtx.keysOrValues);
                    System.err.println("  Baseline Source Fragment:     " + baseCtx.jsonSnippet);
                    errors++;
                }
            } 
            // Case 2: The paths exist, but the interior object layouts diverge (Key mismatch)
            else if (baseCtx != null && parseCtx != null && baseCtx.isObject && !baseCtx.keysOrValues.equals(parseCtx.keysOrValues)) {
                System.err.println("\n[SCHEMA MISMATCH] Object keys do not match!");
                System.err.println("  Path location: " + (path.isEmpty() ? "ROOT" : path));
                System.err.println("  Expected Keys: " + baseCtx.keysOrValues);
                System.err.println("  Actual Keys:   " + parseCtx.keysOrValues);
                System.err.println("  <- Baseline Structure: " + baseCtx.jsonSnippet);
                System.err.println("  -> Parsed Structure:   " + parseCtx.jsonSnippet);
                errors++;
            }

            if (errors >= 3) {
                System.err.println("\n... Truncated further output. Fix these structural divergences first.");
                return false;
            }
        }
        return errors == 0;
    }

    private static class StructureContext {
        final TreeSet<String> keysOrValues;
        final String jsonSnippet;
        final boolean isObject;

        StructureContext(TreeSet<String> keysOrValues, String jsonSnippet, boolean isObject) {
            this.keysOrValues = keysOrValues;
            this.jsonSnippet = jsonSnippet;
            this.isObject = isObject;
        }
    }
}