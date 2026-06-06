package net.ncplanner.ncpf.structure.configuration;
import java.util.List;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
@NcpfRegistered("nuclearcraft:overhaul_turbine")
public class OverhaulTurbineConfiguration extends NcpfConfiguration{
    public List<NcpfElement> blocks;
    public List<NcpfElement> recipes;
}
