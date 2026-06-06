package net.ncplanner.ncpf.structure.module.plannerator;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("plannerator:configuration_metadata")
public class ConfigurationMetadataModule implements NcpfModule{
    public String name;
    public String version;
}
