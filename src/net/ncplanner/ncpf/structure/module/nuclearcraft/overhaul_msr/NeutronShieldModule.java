package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_msr;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_msr:neutron_shield")
public class NeutronShieldModule implements NcpfModule{
    public float efficiency;
    public float heat_per_flux;
    public NcpfElement closed;
}
