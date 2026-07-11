package net.ncplanner.ncpf.structure;
import java.util.ArrayList;
import java.util.List;
import net.ncplanner.ncpf.runtime.RuntimeNcpf;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
public class NcpfRoot{
    public int version;
    public NcpfConfigurations configuration;
    public List<NcpfAddon> addons;
    public List<NcpfDesign> designs;
    public NcpfModules modules;
    public RuntimeNcpf toRuntime(){
        //TODO decoupled copy?
        RuntimeNcpf runtime = new RuntimeNcpf();
        runtime.version = version;
        runtime.configuration = configuration;
        runtime.addons = addons;
        runtime.modules = modules;
        if(designs!=null){
            runtime.designs = new ArrayList<>();
            for(NcpfDesign design : designs){
                runtime.designs.add(design.toRuntime(this));
            }
        }
        return runtime;
    }
}
