package net.ncplanner.ncpf.runtime.design.nuclearcraft;
import net.ncplanner.ncpf.runtime.RuntimeBlock;
import net.ncplanner.ncpf.runtime.RuntimeNcpf;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.structure.configuration.nuclearcraft.UnderhaulSFRConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
import net.ncplanner.ncpf.structure.design.nuclearcraft.UnderhaulSFRDesign;
import net.ncplanner.ncpf.structure.element.NcpfElement;
public class RuntimeUnderhaulSFRDesign extends RuntimeDesign{
    public RuntimeBlock[][][] design;
    public NcpfElement fuel;
    public RuntimeUnderhaulSFRDesign(int x, int y, int z){
        design = new RuntimeBlock[x][y][z];
    }
    @Override
    public NcpfDesign compile(RuntimeNcpf ncpf){
        UnderhaulSFRDesign compiled = new UnderhaulSFRDesign();
        var config = ncpf.configuration.getConfiguration(UnderhaulSFRConfiguration.class);
        compiled.modules = modules;
        compiled.dimensions = new int[]{design.length, design[0].length, design[0][0].length};
        compiled.design = new int[compiled.dimensions[0]][compiled.dimensions[1]][compiled.dimensions[2]];
        compiled.block_recipes = convertDesignArrays(design, config.blocks, compiled.design);
        compiled.fuel = convertElement(fuel, config.fuels);
        return compiled;
    }
}
