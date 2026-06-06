package net.ncplanner.ncpf.structure.rule;
import net.ncplanner.ncpf.registry.NcpfRegistered;
@NcpfRegistered("and")
public class AndPlacementRule implements NcpfPlacementRule{
    public NcpfPlacementRule[] rules;
    @Deprecated
    public Integer min;
    @Deprecated
    public Integer max;
}
