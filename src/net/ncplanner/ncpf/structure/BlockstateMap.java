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
import net.ncplanner.ncpf.io.NcpfJsonConverter;

@JsonAdapter(BlockstateMap.Adapter.class)
public class BlockstateMap{
    private Map<String, Object> blockstates = new LinkedHashMap<>();

    public static class Adapter extends TypeAdapter<BlockstateMap>{
        private static final Type MAP_TYPE = new TypeToken<Map<String, Object>>(){
        }.getType();

        @Override
        public void write(JsonWriter out, BlockstateMap value) throws IOException{
            NcpfJsonConverter.gson.toJson(value.blockstates, MAP_TYPE, out);
        }

        @Override
        public BlockstateMap read(JsonReader in) throws IOException{
            BlockstateMap blockstateMap = new BlockstateMap();
            blockstateMap.blockstates = NcpfJsonConverter.gson.fromJson(in, MAP_TYPE);
            return blockstateMap;
        }
    }
}
