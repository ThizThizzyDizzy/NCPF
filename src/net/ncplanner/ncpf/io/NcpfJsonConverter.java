package net.ncplanner.ncpf.io;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.ncplanner.ncpf.structure.NcpfRoot;
public class NcpfJsonConverter{
    public static final Gson gson = new GsonBuilder().disableJdkUnsafe().create();
    public static NcpfRoot parseJson(File file) throws FileNotFoundException{
        return parseJson(new FileInputStream(file));
    }
    public static NcpfRoot parseJson(InputStream in){
        return parseJson(new JsonReader(new InputStreamReader(in)));
    }
    public static NcpfRoot parseJson(JsonReader reader){
        return gson.fromJson(reader, NcpfRoot.class);
    }
    public static void writeJson(NcpfRoot root, JsonWriter writer){
        gson.toJson(root, NcpfRoot.class, writer);
    }
    public static void writeJson(NcpfRoot root, File file) throws IOException{
        NcpfJsonConverter.writeJson(root, new JsonWriter(new FileWriter(file)));
    }
}
