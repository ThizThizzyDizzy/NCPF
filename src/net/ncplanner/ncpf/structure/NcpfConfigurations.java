package net.ncplanner.ncpf.structure;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import net.ncplanner.ncpf.io.NcpfJsonConverter;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.registry.NcpfRegistry;
import net.ncplanner.ncpf.structure.configuration.NcpfConfiguration;

@JsonAdapter(NcpfConfigurations.Adapter.class)
public class NcpfConfigurations{
    private Map<String, NcpfConfiguration> configurations = new LinkedHashMap<>();

    public <T extends NcpfConfiguration> T getConfiguration(Class<T> type){
        String key = type.getAnnotation(NcpfRegistered.class).value();
        if(configurations.containsKey(key))return (T)configurations.get(key);
        return null;
    }
    public Collection<NcpfConfiguration> getConfigurations(){
        return configurations.values();
    }
    
    public static class Adapter extends TypeAdapter<NcpfConfigurations>{
        private static final Type MAP_TYPE = new TypeToken<Map<String, NcpfConfiguration>>(){
        }.getType();
 
        @Override
        public void write(JsonWriter out, NcpfConfigurations value) throws IOException{
            NcpfJsonConverter.gson.toJson(value.configurations, MAP_TYPE, out);
        }

        @Override
        public NcpfConfigurations read(JsonReader in) throws IOException{
            NcpfConfigurations configs = new NcpfConfigurations();
            in.beginObject();
            while(in.hasNext()){
                String key = in.nextName();
                Class<? extends NcpfConfiguration> type = NcpfRegistry.CONFIGURATION_REGISTRY.get(key);
                configs.configurations.put(key, NcpfJsonConverter.gson.fromJson(in, type));
            }
            in.endObject();
            return configs;
        }
    }
}
