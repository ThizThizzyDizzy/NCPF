package net.ncplanner.ncpf.structure.module.nuclearcraft.underhaul_sfr;
import java.util.List;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
import net.ncplanner.ncpf.structure.rule.NcpfPlacementRule;
@NcpfRegistered("nuclearcraft:underhaul_sfr:cooler")
public class CoolerModule implements NcpfModule{
    public int cooling;
    public List<NcpfPlacementRule> rules;
}
