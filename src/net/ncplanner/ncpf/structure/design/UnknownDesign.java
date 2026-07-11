package net.ncplanner.ncpf.structure.design;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import net.ncplanner.ncpf.exception.NcpfUnknownDesignException;
import net.ncplanner.ncpf.io.NcpfJsonConverter;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.structure.NcpfModules;
import net.ncplanner.ncpf.structure.NcpfRoot;

@NcpfRegistered("")
@JsonAdapter(UnknownDesign.Adapter.class)
public class UnknownDesign extends NcpfDesign{
    private LinkedHashMap<String, JsonElement> rawJson = new LinkedHashMap<>();
    @Override
    public RuntimeDesign toRuntime(NcpfRoot ncpf){
        throw new NcpfUnknownDesignException(this, "Could not convert UnknownDesign to RuntimeDesign!");
    }
    public static class Adapter extends TypeAdapter<UnknownDesign>{
        @Override
        public void write(JsonWriter out, UnknownDesign value) throws IOException{
            JsonObject obj = new JsonObject();
            for (Map.Entry<String, JsonElement> entry : value.rawJson.entrySet()){
                obj.add(entry.getKey(), entry.getValue());
            }
            if (value.modules != null) obj.add("modules", NcpfJsonConverter.gson.toJsonTree(value.modules));
            Streams.write(obj, out);
        }
        @Override
        public UnknownDesign read(JsonReader in) throws IOException{
            JsonObject obj = Streams.parse(in).getAsJsonObject();
            UnknownDesign design = new UnknownDesign();
            for (Map.Entry<String, JsonElement> entry : obj.entrySet()){
                String key = entry.getKey();
                JsonElement element = entry.getValue();
                switch (key){
                    case "modules" -> design.modules = NcpfJsonConverter.gson.fromJson(element, NcpfModules.class);
                }
                design.rawJson.put(key, element);
            }
            return design;
        }
    }
}
