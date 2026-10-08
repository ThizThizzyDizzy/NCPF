package net.ncplanner.ncpf.io;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/** Accepts both nested arrays and flattened design/recipe arrays. */
final class NcpfIntArrayAdapter extends TypeAdapter<int[][][]>{
    @Override
    public int[][][] read(JsonReader reader) throws IOException{
        JsonElement json = Streams.parse(reader);
        if(json.isJsonNull())return null;
        JsonArray array = json.getAsJsonArray();
        if(!array.isEmpty()&&array.get(0).isJsonPrimitive()){
            int[] flat = new int[array.size()];
            for(int i=0;i<flat.length;i++)flat[i]=integer(array.get(i));
            return new int[][][]{{flat}};
        }
        int[][][] result = new int[array.size()][][];
        for(int x=0;x<result.length;x++){
            JsonArray rows = array.get(x).getAsJsonArray();
            result[x] = new int[rows.size()][];
            for(int y=0;y<rows.size();y++){
                JsonArray row = rows.get(y).getAsJsonArray();
                result[x][y] = new int[row.size()];
                for(int z=0;z<row.size();z++)result[x][y][z]=integer(row.get(z));
            }
        }
        return result;
    }
    private static int integer(JsonElement value){
        try{
            if(!value.isJsonPrimitive()||!value.getAsJsonPrimitive().isNumber())throw new ArithmeticException();
            return value.getAsBigDecimal().intValueExact();
        }catch(RuntimeException ex){throw new com.google.gson.JsonParseException("Design indices must be integers", ex);}
    }
    @Override
    public void write(JsonWriter writer, int[][][] value) throws IOException{
        if(value==null){writer.nullValue();return;}
        writer.beginArray();
        for(int[][] plane : value){
            writer.beginArray();
            for(int[] row : plane){
                writer.beginArray();
                for(int entry : row)writer.value(entry);
                writer.endArray();
            }
            writer.endArray();
        }
        writer.endArray();
    }
}
