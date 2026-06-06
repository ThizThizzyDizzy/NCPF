package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_sfr;
import java.util.List;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
import net.ncplanner.ncpf.structure.rule.NcpfPlacementRule;
@NcpfRegistered("nuclearcraft:overhaul_sfr:heat_sink")
public class HeatSinkModule implements NcpfModule{
    public int cooling;
    public List<NcpfPlacementRule> rules;
}
