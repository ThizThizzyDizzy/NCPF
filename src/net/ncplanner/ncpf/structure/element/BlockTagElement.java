package net.ncplanner.ncpf.structure.element;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.BlockstateMap;
@NcpfRegistered("block_tag")
public class BlockTagElement extends NcpfElement{
    public String name;
    public BlockstateMap blockstate;
    public String nbt;
}
