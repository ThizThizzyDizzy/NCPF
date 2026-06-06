package net.ncplanner.ncpf.structure.module.nuclearcraft.overhaul_turbine;
import java.util.List;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.module.NcpfModule;
import net.ncplanner.ncpf.structure.rule.NcpfPlacementRule;
@NcpfRegistered("nuclearcraft:overhaul_turbine:connector")
public class ConnectorModule implements NcpfModule{
    public List<NcpfPlacementRule> rules;
}
