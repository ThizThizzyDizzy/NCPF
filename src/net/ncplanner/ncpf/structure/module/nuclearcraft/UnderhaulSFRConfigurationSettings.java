package net.ncplanner.ncpf.structure.module.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:underhaul_sfr_configuration_settings")
public class UnderhaulSFRConfigurationSettings implements NcpfModule{
    public int min_size;
    public int max_size;
    public int neutron_reach;
    public int active_cooler_rate;
    public float moderator_extra_heat;
    public float moderator_extra_power;
}
