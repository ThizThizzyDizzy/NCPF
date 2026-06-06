package net.ncplanner.ncpf.structure.design;
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
import net.ncplanner.ncpf.structure.NcpfModules;
@JsonAdapter(NcpfDesign.Adapter.class)
public abstract class NcpfDesign{
    public NcpfModules modules;

    public static class Adapter extends TypeAdapter<NcpfDesign>{
        @Override
        public void write(JsonWriter out, NcpfDesign design) throws IOException{
            var json = Parser.gson.toJsonTree(design, design.getClass()).getAsJsonObject();
            json.addProperty("type", design.getClass().getAnnotation(NcpfRegistered.class).value());
            Parser.gson.toJson(json, out);
        }

        @Override
        public NcpfDesign read(JsonReader in) throws IOException{
            JsonObject obj = Streams.parse(in).getAsJsonObject();
            String typeStr = obj.get("type").getAsString();
            Class<? extends NcpfDesign> type = NcpfRegistry.DESIGN_REGISTRY.get(typeStr);
            return Parser.gson.fromJson(obj, type);
        }
    }
}
