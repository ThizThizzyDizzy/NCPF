package net.ncplanner.ncpf.runtime;
import java.util.ArrayList;
import java.util.List;
import net.ncplanner.ncpf.runtime.design.RuntimeDesign;
import net.ncplanner.ncpf.structure.NcpfAddon;
import net.ncplanner.ncpf.structure.NcpfConfigurations;
import net.ncplanner.ncpf.structure.NcpfModules;
import net.ncplanner.ncpf.structure.NcpfRoot;
public class RuntimeNcpf{
    public int version;
    public NcpfConfigurations configuration;
    public List<NcpfAddon> addons;
    public List<RuntimeDesign> designs;
    public NcpfModules modules;
    public NcpfRoot compile(){
        //TODO decoupled copy?
        NcpfRoot ncpf = new NcpfRoot();
        ncpf.version = version;
        ncpf.configuration = configuration;
        ncpf.addons = addons;
        ncpf.modules = modules;
        if(designs!=null){
            ncpf.designs = new ArrayList<>();
            for(RuntimeDesign design : designs){
                ncpf.designs.add(design.compile(this));
            }
        }
        return ncpf;
    }
}
