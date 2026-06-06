package net.ncplanner.ncpf.structure.module.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("nuclearcraft:overhaul_turbine_configuration_settings")
public class OverhaulTurbineConfigurationSettingsModule implements NcpfModule{
    public int min_width;
    public int min_length;
    public int max_size;
    public int fluid_per_blade;
    public float throughput_factor;
    public float power_bonus;
    public float throughput_efficiency_leniency_multiplier;
    public float throughput_efficiency_leniency_threshold;
}
