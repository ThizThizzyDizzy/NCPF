package net.ncplanner.ncpf.structure.module.plannerator;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;
import net.ncplanner.ncpf.Parser;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("plannerator:metadata")
@JsonAdapter(MetadataModule.Adapter.class)
public class MetadataModule implements NcpfModule{
    public Map<String, String> metadata = new LinkedHashMap<>();
    public static class Adapter extends TypeAdapter<MetadataModule>{
        private static final Type MAP_TYPE = new TypeToken<Map<String, String>>(){}.getType();
        @Override
        public void write(JsonWriter out, MetadataModule module) throws IOException{
            Parser.gson.toJson(module.metadata, MAP_TYPE, out);
        }
        @Override
        public MetadataModule read(JsonReader in) throws IOException{
            MetadataModule module = new MetadataModule();
            module.metadata = Parser.gson.fromJson(in, MAP_TYPE);
            return module;
        }
    }
}
