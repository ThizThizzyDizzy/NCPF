package net.ncplanner.ncpf.structure;
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
import net.ncplanner.ncpf.structure.element.*;
@JsonAdapter(NcpfElementStack.Adapter.class)
public class NcpfElementStack{
    public NcpfElement element;
    public int amount;
    public static class Adapter extends TypeAdapter<NcpfElementStack>{
        @Override
        public void write(JsonWriter out, NcpfElementStack stack) throws IOException{
            var json = Parser.gson.toJsonTree(stack.element, stack.element.getClass()).getAsJsonObject();
            var elementClass = stack.element.getClass();
            if(elementClass==StackListElement.class)elementClass = ListElement.class;
            json.addProperty("type", elementClass.getAnnotation(NcpfRegistered.class).value());
            if(elementClass!=ListElement.class)json.addProperty("amount", stack.amount);
            Parser.gson.toJson(json, out);
        }
        @Override
        public NcpfElementStack read(JsonReader in) throws IOException{
            JsonObject obj = Streams.parse(in).getAsJsonObject();
            NcpfElementStack stack = new NcpfElementStack();
            var typeElement = obj.get("type");
            String typeStr = typeElement==null?NcpfRegistry.UNKNOWN_VALUE:typeElement.getAsString();
            Class<? extends NcpfElement> type = NcpfRegistry.ELEMENT_REGISTRY.get(typeStr);
            if(type==ListElement.class)type = StackListElement.class;
            stack.element = Parser.gson.fromJson(obj, type);
            if(type!=StackListElement.class)stack.amount = obj.get("amount").getAsInt();
            return stack;
        }
    }
}
