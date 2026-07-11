package net.ncplanner.ncpf.structure.design;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;
import net.ncplanner.ncpf.io.NcpfJsonConverter;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.registry.NcpfRegistry;
import net.ncplanner.ncpf.runtime.RuntimeBlock;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.structure.NcpfModules;
import net.ncplanner.ncpf.structure.NcpfRoot;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.BlockRecipesModule;
@JsonAdapter(NcpfDesign.Adapter.class)
public abstract class NcpfDesign{
    public NcpfModules modules;
    public abstract RuntimeDesign toRuntime(NcpfRoot ncpf);
    
    protected static void convertDesignArrays(int[][][] design, int[][][] blockRecipes, List<NcpfElement> blocks, RuntimeBlock[][][] target){
        int[] recipeIdx = new int[]{-1,-1,-1};
        int[] lastIdx = new int[]{-1,-1,-1};
        for(int x = 0; x<design.length; x++){
            recipeIdx[1] = lastIdx[1] = -1;
            for(int y = 0; y<design[x].length; y++){
                recipeIdx[2] = lastIdx[2] = -1;
                for(int z = 0; z<design[x][y].length; z++){
                    if(design[x][y][z]==-1)continue;
                    
                    RuntimeBlock block = new RuntimeBlock();
                    block.block = convertElement(design[x][y][z], blocks);
                    var recipesModule = block.block.modules.getModule(BlockRecipesModule.class);
                    if(recipesModule!=null){
                        if(x!=lastIdx[0]){
                            lastIdx[0] = x;
                            recipeIdx[0]++;
                        }
                        if(y!=lastIdx[1]){
                            lastIdx[1] = y;
                            recipeIdx[1]++;
                        }
                        if(z!=lastIdx[2]){
                            lastIdx[2] = z;
                            recipeIdx[2]++;
                        }
                        block.blockRecipe = convertElement(blockRecipes[recipeIdx[0]][recipeIdx[1]][recipeIdx[2]], block.block.modules.getOrCreateModule(BlockRecipesModule::new).recipes);
                    }
                    target[x][y][z] = block;
                }
            }
        }
    }
    protected static NcpfElement convertElement(int index, List<NcpfElement> elements){
        if(index==-1)return null;
        return elements.get(index);
    }
    protected static NcpfElement convertElement(int index, NcpfElement[] elements){
        if(index==-1)return null;
        return elements[index];
    }

    public static class Adapter extends TypeAdapter<NcpfDesign>{
        @Override
        public void write(JsonWriter out, NcpfDesign design) throws IOException{
            var json = NcpfJsonConverter.gson.toJsonTree(design, design.getClass()).getAsJsonObject();
            json.addProperty("type", design.getClass().getAnnotation(NcpfRegistered.class).value());
            NcpfJsonConverter.gson.toJson(json, out);
        }

        @Override
        public NcpfDesign read(JsonReader in) throws IOException{
            JsonObject obj = Streams.parse(in).getAsJsonObject();
            String typeStr = obj.get("type").getAsString();
            Class<? extends NcpfDesign> type = NcpfRegistry.DESIGN_REGISTRY.get(typeStr);
            return NcpfJsonConverter.gson.fromJson(obj, type);
        }
    }
}
