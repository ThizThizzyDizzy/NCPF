package net.ncplanner.ncpf.structure.element;
import net.ncplanner.ncpf.registry.NcpfRegistered;
@NcpfRegistered("legacy_item")
public class LegacyItemElement extends NcpfElement{
    public String name;
    public Integer metadata;
    public String nbt;
}
