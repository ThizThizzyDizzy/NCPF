package net.ncplanner.ncpf.runtime.design.nuclearcraft;
import net.ncplanner.ncpf.runtime.RuntimeBlock;
import net.ncplanner.ncpf.runtime.RuntimeNcpf;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.structure.configuration.nuclearcraft.OverhaulSFRConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
import net.ncplanner.ncpf.structure.design.nuclearcraft.OverhaulSFRDesign;
import net.ncplanner.ncpf.structure.element.NcpfElement;
public class RuntimeOverhaulSFRDesign extends RuntimeDesign{
    public RuntimeBlock[][][] design;
    public NcpfElement coolantRecipe;
    public RuntimeOverhaulSFRDesign(int x, int y, int z){
        design = new RuntimeBlock[x][y][z];
    }
    @Override
    public NcpfDesign compile(RuntimeNcpf ncpf){
        OverhaulSFRDesign compiled = new OverhaulSFRDesign();
        var config = ncpf.configuration.getConfiguration(OverhaulSFRConfiguration.class);
        compiled.modules = modules;
        compiled.dimensions = new int[]{design.length, design[0].length, design[0][0].length};
        compiled.design = new int[compiled.dimensions[0]][compiled.dimensions[1]][compiled.dimensions[2]];
        compiled.block_recipes = convertDesignArrays(design, config.blocks, compiled.design);
        compiled.coolant_recipe = convertElement(coolantRecipe, config.coolant_recipes);
        return compiled;
    }
}
