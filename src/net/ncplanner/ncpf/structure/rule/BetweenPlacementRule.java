package net.ncplanner.ncpf.structure.rule;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.element.NcpfElement;
@NcpfRegistered("between")
public class BetweenPlacementRule implements NcpfPlacementRule{
    public NcpfElement block;
    public int min;
    public int max;
    @Deprecated
    public NcpfPlacementRule[] rules;
}
