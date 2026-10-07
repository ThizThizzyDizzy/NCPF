package net.ncplanner.ncpf.structure.design.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.runtime.design.nuclearcraft.RuntimeUnderhaulSFRDesign;
import net.ncplanner.ncpf.structure.NcpfRoot;
import net.ncplanner.ncpf.structure.configuration.nuclearcraft.UnderhaulSFRConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
@NcpfRegistered("nuclearcraft:underhaul_sfr")
public class UnderhaulSFRDesign extends NcpfDesign{
    public int[] dimensions = new int[3];
    public int[][][] design;
    public int[][][] block_recipes;
    public int fuel;
    @Override
    public RuntimeUnderhaulSFRDesign toRuntime(NcpfRoot ncpf){
        RuntimeUnderhaulSFRDesign runtime = new RuntimeUnderhaulSFRDesign(dimensions[0], dimensions[1], dimensions[2]);
        var config = ncpf.configuration.getConfiguration(UnderhaulSFRConfiguration.class);
        runtime.modules = modules;
        convertDesignArrays(design, block_recipes, config.blocks, runtime.design);
        runtime.fuel = convertElement(fuel, config.fuels);
        return runtime;
    }
}
