package net.ncplanner.ncpf.structure.module;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
@NcpfRegistered("ncpf:block_recipes")
public class BlockRecipesModule implements NcpfModule{
    public NcpfElement[] recipes;
}
