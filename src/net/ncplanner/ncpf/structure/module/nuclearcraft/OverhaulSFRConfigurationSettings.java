package net.ncplanner.ncpf.structure.module.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_sfr_configuration_settings")
public class OverhaulSFRConfigurationSettings implements NcpfModule{
    public int min_size;
    public int max_size;
    public int neutron_reach;
    public float cooling_efficiency_leniency;
    public float sparsity_penalty_multiplier;
    public float sparsity_penalty_threshold;
}
