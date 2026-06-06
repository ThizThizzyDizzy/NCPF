package net.ncplanner.ncpf.structure.rule;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import net.ncplanner.ncpf.registry.NcpfRegistered;

@NcpfRegistered("")
@JsonAdapter(UnknownPlacementRule.Adapter.class)
public class UnknownPlacementRule implements NcpfPlacementRule{
    private JsonElement rawJson;
    public static class Adapter extends TypeAdapter<UnknownPlacementRule>{
        @Override
        public void write(JsonWriter out, UnknownPlacementRule rule) throws IOException{
            Streams.write(rule.rawJson, out);
        }
        @Override
        public UnknownPlacementRule read(JsonReader in) throws IOException{
            UnknownPlacementRule rule = new UnknownPlacementRule();
            rule.rawJson = Streams.parse(in);
            return rule;
        }
    }
}
