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
import java.util.function.Supplier;
import net.ncplanner.ncpf.io.NcpfJsonConverter;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.registry.NcpfRegistry;
import net.ncplanner.ncpf.structure.module.NcpfModule;

@JsonAdapter(NcpfModules.Adapter.class)
public class NcpfModules{
    private final Map<String, NcpfModule> modules = new LinkedHashMap<>();
    
    public <T extends NcpfModule> T getModule(Class<T> type){
        String key = type.getAnnotation(NcpfRegistered.class).value();
        if(modules.containsKey(key))return (T)modules.get(key);
        return null;
    }
    public <T extends NcpfModule> T getOrCreateModule(Supplier<T> supplier){
        var module = supplier.get();
        var clazz = module.getClass();
        String key = clazz.getAnnotation(NcpfRegistered.class).value();
        if(modules.containsKey(key))return (T)modules.get(key);
        modules.put(key, module);
        return module;
    }

    public static class Adapter extends TypeAdapter<NcpfModules>{
        private static final Type MAP_TYPE = new TypeToken<Map<String, NcpfModule>>(){
        }.getType();
 
        @Override
        public void write(JsonWriter out, NcpfModules value) throws IOException{
            NcpfJsonConverter.gson.toJson(value.modules, MAP_TYPE, out);
        }

        @Override
        public NcpfModules read(JsonReader in) throws IOException{
            NcpfModules modules = new NcpfModules();
            in.beginObject();
            while(in.hasNext()){
                String key = in.nextName();
                Class<? extends NcpfModule> type = NcpfRegistry.MODULE_REGISTRY.get(key);
                modules.modules.put(key, NcpfJsonConverter.gson.fromJson(in, type));
            }
            in.endObject();
            return modules;
        }
    }
}
