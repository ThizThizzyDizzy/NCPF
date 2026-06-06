package net.ncplanner.ncpf.structure.module.plannerator;
import java.util.List;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("plannerator:tags")
public class TagsModule implements NcpfModule{
    public List<String> tags;
}
