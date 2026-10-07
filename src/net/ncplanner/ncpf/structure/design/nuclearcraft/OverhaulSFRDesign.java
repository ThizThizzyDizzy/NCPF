package net.ncplanner.ncpf.structure.design.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.runtime.design.nuclearcraft.RuntimeOverhaulSFRDesign;
import net.ncplanner.ncpf.structure.NcpfRoot;
import net.ncplanner.ncpf.structure.configuration.nuclearcraft.OverhaulSFRConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
@NcpfRegistered("nuclearcraft:overhaul_sfr")
public class OverhaulSFRDesign extends NcpfDesign{
    public int[] dimensions = new int[3];
    public int[][][] design;
    public int[][][] block_recipes;
    public int coolant_recipe;
    @Override
    public RuntimeDesign toRuntime(NcpfRoot ncpf){
        RuntimeOverhaulSFRDesign runtime = new RuntimeOverhaulSFRDesign(dimensions[0], dimensions[1], dimensions[2]);
        var config = ncpf.configuration.getConfiguration(OverhaulSFRConfiguration.class);
        runtime.modules = modules;
        convertDesignArrays(design, block_recipes, config.blocks, runtime.design);
        runtime.coolantRecipe = convertElement(coolant_recipe, config.coolant_recipes);
        return runtime;
    }
}
