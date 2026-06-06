package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_msr;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_msr:fuel_stats")
public class FuelRecipeModule implements NcpfModule{
    public float efficiency;
    public int heat;
    public int time;
    public int criticality;
    public boolean self_priming;
    @Deprecated
    public NcpfElement output;
}
