package net.ncplanner.ncpf.structure.module.plannerator;
import java.util.List;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("plannerator:legacy_names")
public class LegacyNamesModule implements NcpfModule{
    public List<String> legacy_names;
}
