package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_sfr;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_sfr:neutron_shield")
public class NeutronShieldModule implements NcpfModule{
    public float efficiency;
    public float heat_per_flux;
    public NcpfElement closed;
}
