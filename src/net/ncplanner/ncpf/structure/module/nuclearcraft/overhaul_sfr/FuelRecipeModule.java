package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_sfr;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_sfr:fuel_stats")
public class FuelRecipeModule implements NcpfModule{
    public float efficiency;
    public int heat;
    public int time;
    public int criticality;
    public boolean self_priming;
    @Deprecated
    public NcpfElement output;
}
