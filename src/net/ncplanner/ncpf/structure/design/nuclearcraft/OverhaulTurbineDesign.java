package net.ncplanner.ncpf.structure.design.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.runtime.RuntimeBlock;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.runtime.design.nuclearcraft.RuntimeOverhaulTurbineDesign;
import net.ncplanner.ncpf.structure.NcpfRoot;
import net.ncplanner.ncpf.structure.configuration.nuclearcraft.OverhaulTurbineConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
@NcpfRegistered("nuclearcraft:overhaul_turbine")
public class OverhaulTurbineDesign extends NcpfDesign{
    public int[] dimensions = new int[3];
    public int[][][] design;
    public int recipe;
    @Override
    public RuntimeDesign toRuntime(NcpfRoot ncpf){
        RuntimeOverhaulTurbineDesign runtime = new RuntimeOverhaulTurbineDesign();
        var config = ncpf.configuration.getConfiguration(OverhaulTurbineConfiguration.class);
        runtime.modules = modules;
        runtime.design = new RuntimeBlock[dimensions[0]][dimensions[1]][dimensions[2]];
        convertDesignArrays(design, null, config.blocks, runtime.design);
        runtime.recipe = convertElement(recipe, config.recipes);
        return runtime;
    }
}
