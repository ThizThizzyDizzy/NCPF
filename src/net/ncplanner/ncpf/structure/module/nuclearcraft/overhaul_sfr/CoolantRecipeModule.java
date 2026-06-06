package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_sfr;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_sfr:coolant_recipe_stats")
public class CoolantRecipeModule implements NcpfModule{
    public int heat;
    @Deprecated
    public Integer output_ratio;
    @Deprecated
    public NcpfElement output;
}
