package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_turbine;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_turbine:recipe_stats")
public class TurbineRecipeModule implements NcpfModule{
    @Deprecated
    public NcpfElement output;
    public double power;
    public double coefficient;
}
