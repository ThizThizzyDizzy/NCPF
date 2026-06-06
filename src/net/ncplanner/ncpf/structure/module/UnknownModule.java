package net.ncplanner.ncpf.structure.module;

import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import net.ncplanner.ncpf.registry.NcpfRegistered;

@NcpfRegistered("")
@JsonAdapter(UnknownModule.Adapter.class)
public class UnknownModule implements NcpfModule{
    private JsonElement rawJson;
    public static class Adapter extends TypeAdapter<UnknownModule>{
        @Override
        public void write(JsonWriter out, UnknownModule value) throws IOException{
            Streams.write(value.rawJson, out);
        }
        @Override
        public UnknownModule read(JsonReader in) throws IOException{
            UnknownModule module = new UnknownModule();
            module.rawJson = Streams.parse(in);
            return module;
        }
    }
}
