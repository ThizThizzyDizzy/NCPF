package net.ncplanner.ncpf.structure.element;
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
@JsonAdapter(NcpfElement.Adapter.class)
public class NcpfElement{
    public NcpfModules modules;
    public static class Adapter extends TypeAdapter<NcpfElement>{
        @Override
        public void write(JsonWriter out, NcpfElement element) throws IOException{
            var json = Parser.gson.toJsonTree(element, element.getClass()).getAsJsonObject();
            json.addProperty("type", element.getClass().getAnnotation(NcpfRegistered.class).value());
            Parser.gson.toJson(json, out);
        }
        @Override
        public NcpfElement read(JsonReader in) throws IOException{
            JsonObject obj = Streams.parse(in).getAsJsonObject();
            var typeElement = obj.get("type");
            String typeStr = typeElement==null?NcpfRegistry.UNKNOWN_VALUE:typeElement.getAsString();
            Class<? extends NcpfElement> type = NcpfRegistry.ELEMENT_REGISTRY.get(typeStr);
            return Parser.gson.fromJson(obj, type);
        }
    }
}
