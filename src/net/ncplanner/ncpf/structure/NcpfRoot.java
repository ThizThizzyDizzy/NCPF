package net.ncplanner.ncpf.structure;
import java.util.List;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
public class NcpfRoot{
    public int version;
    public NcpfConfigurations configuration;
    public List<NcpfAddon> addons;
    public List<NcpfDesign> designs;
    public NcpfModules modules;
}
