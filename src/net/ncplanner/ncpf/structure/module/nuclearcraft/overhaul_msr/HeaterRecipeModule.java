package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_msr;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_msr:heater_stats")
public class HeaterRecipeModule implements NcpfModule{
    public int cooling;
    @Deprecated
    public NcpfElement output;
}
