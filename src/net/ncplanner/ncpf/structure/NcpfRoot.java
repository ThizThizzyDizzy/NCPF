package net.ncplanner.ncpf.structure;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
import java.util.List;
public class NcpfRoot{
    public int version;
    public NcpfConfigurations configuration;
    public List<NcpfAddon> addons;
    public List<NcpfDesign> designs;
    public NcpfModules modules;
}
