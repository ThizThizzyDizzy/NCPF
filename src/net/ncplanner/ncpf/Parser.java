package net.ncplanner.ncpf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.ncplanner.ncpf.structure.NcpfRoot;
public class Parser{
    public static final Gson gson = new GsonBuilder().disableJdkUnsafe().create();
    public static NcpfRoot parseNcpf(JsonReader reader){
        return gson.fromJson(reader, NcpfRoot.class);
    }
}
