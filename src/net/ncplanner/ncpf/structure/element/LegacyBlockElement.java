package net.ncplanner.ncpf.structure.element;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.BlockstateMap;
@NcpfRegistered("legacy_block")
public class LegacyBlockElement extends NcpfElement{
    public String name;
    public Integer metadata;
    public BlockstateMap blockstate;
    public String nbt;
}
