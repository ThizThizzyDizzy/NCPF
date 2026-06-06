package net.ncplanner.ncpf.structure.module.plannerator;
import java.util.List;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
@NcpfRegistered("plannerator:global_elements")
public class GlobalElementsModule implements NcpfModule{
    public List<NcpfElement> elements;
}
