package net.ncplanner.ncpf.structure;

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
import net.ncplanner.ncpf.structure.configuration.NcpfConfiguration;

@JsonAdapter(NcpfConfigurations.Adapter.class)
public class NcpfConfigurations{
    private Map<String, NcpfConfiguration> configurations = new LinkedHashMap<>();

    public static class Adapter extends TypeAdapter<NcpfConfigurations>{
        private static final Type MAP_TYPE = new TypeToken<Map<String, NcpfConfiguration>>(){
        }.getType();
 
        @Override
        public void write(JsonWriter out, NcpfConfigurations value) throws IOException{
            Parser.gson.toJson(value.configurations, MAP_TYPE, out);
        }

        @Override
        public NcpfConfigurations read(JsonReader in) throws IOException{
            NcpfConfigurations configs = new NcpfConfigurations();
            in.beginObject();
            while(in.hasNext()){
                String key = in.nextName();
                Class<? extends NcpfConfiguration> type = NcpfRegistry.CONFIGURATION_REGISTRY.get(key);
                configs.configurations.put(key, Parser.gson.fromJson(in, type));
            }
            in.endObject();
            return configs;
        }
    }
}
