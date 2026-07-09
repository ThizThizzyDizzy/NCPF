package net.ncplanner.ncpf.structure.element;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.BlockstateMap;
@NcpfRegistered("block")
public class BlockElement extends NcpfElement{
    public String name;
    public BlockstateMap blockstate;
    public String nbt;
}
