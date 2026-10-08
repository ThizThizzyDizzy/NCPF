package net.ncplanner.ncpf.io;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import net.ncplanner.ncpf.structure.NcpfRoot;
public class NcpfJsonConverter{
    public static final Gson gson = new GsonBuilder().disableJdkUnsafe().registerTypeAdapter(int[][][].class, new NcpfIntArrayAdapter()).create();
    public static NcpfRoot parseJson(File file) throws IOException{
        try(InputStream in = new FileInputStream(file)){
            return parseJson(in);
        }
    }
    public static NcpfRoot parseJson(InputStream in){
        return parseJson(new JsonReader(new InputStreamReader(in, StandardCharsets.UTF_8)));
    }
    public static NcpfRoot parseJson(JsonReader reader){
        JsonElement document = JsonParser.parseReader(reader);
        if(!document.isJsonObject()||!document.getAsJsonObject().has("version"))throw new JsonParseException("Missing NCPF version");
        JsonElement version = document.getAsJsonObject().get("version");
        try{
            if(!version.isJsonPrimitive()||!version.getAsJsonPrimitive().isNumber())throw new ArithmeticException();
            version.getAsBigDecimal().intValueExact();
        }catch(RuntimeException ex){throw new JsonParseException("NCPF version must be an integer", ex);}
        NcpfRoot root = gson.fromJson(document, NcpfRoot.class);
        try{
            if(reader.peek()!=JsonToken.END_DOCUMENT)throw new JsonParseException("Trailing data after NCPF document");
        }catch(IOException ex){throw new JsonParseException("Invalid NCPF document", ex);}
        return root;
    }
    public static void writeJson(NcpfRoot root, JsonWriter writer){
        if(root==null)throw new IllegalArgumentException("Empty NCPF document");
        gson.toJson(root, NcpfRoot.class, writer);
    }
    public static void writeJson(NcpfRoot root, File file) throws IOException{
        try(JsonWriter writer = new JsonWriter(Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8))){
            writeJson(root, writer);
        }
    }
}
