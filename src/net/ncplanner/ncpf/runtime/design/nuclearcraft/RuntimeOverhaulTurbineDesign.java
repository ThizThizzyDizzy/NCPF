package net.ncplanner.ncpf.runtime.design.nuclearcraft;
import net.ncplanner.ncpf.runtime.RuntimeBlock;
import net.ncplanner.ncpf.runtime.RuntimeNcpf;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.structure.configuration.nuclearcraft.OverhaulTurbineConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
import net.ncplanner.ncpf.structure.design.nuclearcraft.OverhaulTurbineDesign;
import net.ncplanner.ncpf.structure.element.NcpfElement;
public class RuntimeOverhaulTurbineDesign extends RuntimeDesign{
    public RuntimeBlock[][][] design;
    public NcpfElement recipe;
    public RuntimeOverhaulTurbineDesign(int x, int y, int z){
        design = new RuntimeBlock[x][y][z];
    }
    @Override
    public NcpfDesign compile(RuntimeNcpf ncpf){
        OverhaulTurbineDesign compiled = new OverhaulTurbineDesign();
        var config = ncpf.configuration.getConfiguration(OverhaulTurbineConfiguration.class);
        compiled.modules = modules;
        compiled.dimensions = new int[]{design.length, design[0].length, design[0][0].length};
        compiled.design = new int[compiled.dimensions[0]][compiled.dimensions[1]][compiled.dimensions[2]];
        convertDesignArrays(design, config.blocks, compiled.design);
        compiled.recipe = convertElement(recipe, config.recipes);
        return compiled;
    }
}
