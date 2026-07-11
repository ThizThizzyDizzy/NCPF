package net.ncplanner.ncpf.runtime.design;
import java.util.List;
import net.ncplanner.ncpf.runtime.RuntimeBlock;
import net.ncplanner.ncpf.runtime.RuntimeNcpf;
import net.ncplanner.ncpf.structure.NcpfModules;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.BlockRecipesModule;
public abstract class RuntimeDesign{
    public NcpfModules modules;
    public abstract NcpfDesign compile(RuntimeNcpf ncpf);
    
    protected static int[][][] convertDesignArrays(RuntimeBlock[][][] design, List<NcpfElement> blocks, int[][][] target){
        int[] recipeIdx = new int[]{-1,-1,-1};
        int[] lastIdx = new int[]{-1,-1,-1};
        int max0 = -1, max1 = -1, max2 = -1;
        for(int x = 0; x<design.length; x++){
            recipeIdx[1] = lastIdx[1] = -1;
            for(int y = 0; y<design[x].length; y++){
                recipeIdx[2] = lastIdx[2] = -1;
                for(int z = 0; z<design[x][y].length; z++){
                    RuntimeBlock block = design[x][y][z];
                    if(block!=null&&block.blockRecipe!=null){
                        if(x!=lastIdx[0]){
                            lastIdx[1] = x;
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
                        if(recipeIdx[0]>max0)max0 = recipeIdx[0];
                        if(recipeIdx[1]>max1)max1 = recipeIdx[1];
                        if(recipeIdx[2]>max2)max2 = recipeIdx[2];
                    }
                }
            }
        }
        int[][][] blockRecipes;
        if(max0==-1){
            blockRecipes = null;
        }else{
            blockRecipes = new int[max0+1][max1+1][max2+1];
        }
        recipeIdx = new int[]{-1,-1,-1};
        lastIdx = new int[]{-1,-1,-1};
        for(int x = 0; x<design.length; x++){
            recipeIdx[1] = lastIdx[1] = -1;
            for(int y = 0; y<design[x].length; y++){
                recipeIdx[2] = lastIdx[2] = -1;
                for(int z = 0; z<design[x][y].length; z++){
                    RuntimeBlock block = design[x][y][z];
                    if(block!=null){
                        target[x][y][z] = convertElement(block.block, blocks);
                        if(block.blockRecipe!=null){
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
                            var recipesModule = block.block.modules.getModule(BlockRecipesModule.class);
                            if(recipesModule!=null){
                                blockRecipes[recipeIdx[0]][recipeIdx[1]][recipeIdx[2]] = convertElement(block.blockRecipe, recipesModule.recipes);
                            }
                        }
                    }else{
                        target[x][y][z] = -1;
                    }
                }
            }
        }
        return blockRecipes;
    }
    
    protected static int convertElement(NcpfElement element, List<NcpfElement> elements){
        if(element==null)return -1;
        return elements.indexOf(element);
    }
    protected static int convertElement(NcpfElement element, NcpfElement[] elements){
        if(element==null)return -1;
        for(int i = 0; i<elements.length; i++){
            if(element==elements[i])return i;
        }
        return -1;
    }
}
