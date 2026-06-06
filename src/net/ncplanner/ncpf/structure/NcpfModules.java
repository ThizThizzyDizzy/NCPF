package net.ncplanner.ncpf.structure;

import net.ncplanner.ncpf.structure.module.NcpfModule;
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
import net.ncplanner.ncpf.registry.NcpfRegistry;

@JsonAdapter(NcpfModules.Adapter.class)
public class NcpfModules{
    private Map<String, NcpfModule> modules = new LinkedHashMap<>();

    public static class Adapter extends TypeAdapter<NcpfModules>{
        private static final Type MAP_TYPE = new TypeToken<Map<String, NcpfModule>>(){
        }.getType();
 
        @Override
        public void write(JsonWriter out, NcpfModules value) throws IOException{
            Parser.gson.toJson(value.modules, MAP_TYPE, out);
        }

        @Override
        public NcpfModules read(JsonReader in) throws IOException{
            NcpfModules modules = new NcpfModules();
            in.beginObject();
            while(in.hasNext()){
                String key = in.nextName();
                Class<? extends NcpfModule> type = NcpfRegistry.MODULE_REGISTRY.get(key);
                modules.modules.put(key, Parser.gson.fromJson(in, type));
            }
            in.endObject();
            return modules;
        }
    }
}
