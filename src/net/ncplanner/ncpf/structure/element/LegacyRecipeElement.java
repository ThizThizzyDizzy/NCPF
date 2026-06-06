package net.ncplanner.ncpf.structure.element;
import java.util.Set;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.NcpfElementStack;
@NcpfRegistered("legacy_recipe")
public class LegacyRecipeElement extends NcpfElement{
    public Set<NcpfElementStack> inputs;
    public Set<NcpfElementStack> outputs;
}
