package net.ncplanner.ncpf.structure.design.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.runtime.RuntimeBlock;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.runtime.design.nuclearcraft.RuntimeOverhaulMSRDesign;
import net.ncplanner.ncpf.structure.NcpfRoot;
import net.ncplanner.ncpf.structure.configuration.nuclearcraft.OverhaulMSRConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
@NcpfRegistered("nuclearcraft:overhaul_msr")
public class OverhaulMSRDesign extends NcpfDesign{
    public int[] dimensions = new int[3];
    public int[][][] design;
    public int[][][] block_recipes;
    @Override
    public RuntimeDesign toRuntime(NcpfRoot ncpf){
        RuntimeOverhaulMSRDesign runtime = new RuntimeOverhaulMSRDesign(dimensions[0], dimensions[1], dimensions[2]);
        var config = ncpf.configuration.getConfiguration(OverhaulMSRConfiguration.class);
        runtime.modules = modules;
        runtime.design = new RuntimeBlock[dimensions[0]][dimensions[1]][dimensions[2]];
        convertDesignArrays(design, block_recipes, config.blocks, runtime.design);
        return runtime;
    }
}
