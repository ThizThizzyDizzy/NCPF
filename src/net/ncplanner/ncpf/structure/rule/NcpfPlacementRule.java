package net.ncplanner.ncpf.structure.rule;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import net.ncplanner.ncpf.Parser;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.registry.NcpfRegistry;
@JsonAdapter(NcpfPlacementRule.Adapter.class)
public interface NcpfPlacementRule{
    public static class Adapter extends TypeAdapter<NcpfPlacementRule>{
        @Override
        public void write(JsonWriter out, NcpfPlacementRule rule) throws IOException{
            var json = Parser.gson.toJsonTree(rule, rule.getClass()).getAsJsonObject();
            json.addProperty("type", rule.getClass().getAnnotation(NcpfRegistered.class).value());
            Parser.gson.toJson(json, out);
        }

        @Override
        public NcpfPlacementRule read(JsonReader in) throws IOException{
            JsonObject obj = Streams.parse(in).getAsJsonObject();
            String typeStr = obj.get("type").getAsString();
            Class<? extends NcpfPlacementRule> type = NcpfRegistry.PLACEMENT_RULE_REGISTRY.get(typeStr);
            return Parser.gson.fromJson(obj, type);
        }
    }
}
