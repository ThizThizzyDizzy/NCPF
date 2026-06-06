package net.ncplanner.ncpf.structure.module.nuclearcraft.underhaul_sfr;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:underhaul_sfr:fuel_stats")
public class FuelRecipeModule implements NcpfModule{
    public float power;
    public float heat;
    public int time;
}
