package net.ncplanner.ncpf.registry;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import java.util.HashMap;
import net.ncplanner.ncpf.exception.NcpfRegistryException;
import net.ncplanner.ncpf.structure.configuration.NcpfConfiguration;
import net.ncplanner.ncpf.structure.configuration.UnknownConfiguration;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
import net.ncplanner.ncpf.structure.design.UnknownDesign;
import net.ncplanner.ncpf.structure.design.UnknownElement;
import net.ncplanner.ncpf.structure.element.NcpfElement;
import net.ncplanner.ncpf.structure.module.NcpfModule;
import net.ncplanner.ncpf.structure.module.UnknownModule;
import net.ncplanner.ncpf.structure.rule.NcpfPlacementRule;
import net.ncplanner.ncpf.structure.rule.UnknownPlacementRule;

public class NcpfRegistry<T>{
    public static final String UNKNOWN_VALUE = "";

    private NcpfRegistry(){
    }

    public static final NcpfRegistry<NcpfConfiguration> CONFIGURATION_REGISTRY = new NcpfRegistry<>();
    public static final NcpfRegistry<NcpfModule> MODULE_REGISTRY = new NcpfRegistry<>();
    public static final NcpfRegistry<NcpfDesign> DESIGN_REGISTRY = new NcpfRegistry<>();
    public static final NcpfRegistry<NcpfElement> ELEMENT_REGISTRY = new NcpfRegistry<>();
    public static final NcpfRegistry<NcpfPlacementRule> PLACEMENT_RULE_REGISTRY = new NcpfRegistry<>();

    static{
        CONFIGURATION_REGISTRY.registerInternal(UnknownConfiguration.class);
        MODULE_REGISTRY.registerInternal(UnknownModule.class);
        DESIGN_REGISTRY.registerInternal(UnknownDesign.class);
        ELEMENT_REGISTRY.registerInternal(UnknownElement.class);
        PLACEMENT_RULE_REGISTRY.registerInternal(UnknownPlacementRule.class);

        try(ScanResult scanResult = new ClassGraph()
            .enableClassInfo()
            .enableAnnotationInfo()
            .scan()){

            for(ClassInfo classInfo : scanResult.getClassesWithAnnotation(NcpfRegistered.class.getName())){
                try{
                    Class<?> clazz = classInfo.loadClass();
                    register(clazz);
                }catch(Throwable t){
                    System.err.println("NCPF Registry failed to safely load discovered class: "+classInfo.getName());
                    t.printStackTrace();
                }
            }
        }catch(Exception e){
            System.err.println("Critical error running ClassGraph scanning inside NcpfRegistry!");
            e.printStackTrace();
        }
    }

    private final HashMap<String, Class<? extends T>> registry = new HashMap<>();

    public Class<? extends T> get(String key){
        var value = registry.get(key);
        if(value==null){
            value = registry.get(UNKNOWN_VALUE);
            System.out.println("NCPF Registry is missing value for key: "+key+"! Returning "+value.getName()+"...");
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public static void register(Class<?> item){
        if(!item.isAnnotationPresent(NcpfRegistered.class)){
            return;
        }

        if(NcpfConfiguration.class.isAssignableFrom(item)){
            CONFIGURATION_REGISTRY.registerInternal((Class<? extends NcpfConfiguration>)item);
        }else if(NcpfModule.class.isAssignableFrom(item)){
            MODULE_REGISTRY.registerInternal((Class<? extends NcpfModule>)item);
        }else if(NcpfDesign.class.isAssignableFrom(item)){
            DESIGN_REGISTRY.registerInternal((Class<? extends NcpfDesign>)item);
        }else if(NcpfElement.class.isAssignableFrom(item)){
            ELEMENT_REGISTRY.registerInternal((Class<? extends NcpfElement>)item);
        }else if(NcpfPlacementRule.class.isAssignableFrom(item)){
            PLACEMENT_RULE_REGISTRY.registerInternal((Class<? extends NcpfPlacementRule>)item);
        }else{
            System.err.println("Skipping auto-registration for "+item.getName()+": Does not implement a recognized NCPF structure type.");
        }
    }

    private void registerInternal(Class<? extends T> item){
        NcpfRegistered annotation = item.getAnnotation(NcpfRegistered.class);
        if(annotation==null){
            throw new NcpfRegistryException(this, "Class missing @NcpfRegistered annotation: "+item.getName());
        }

        String key = annotation.value();
        if(registry.containsKey(key)){
            // If it's already registered by our manual static block, skip throwing an error
            if(registry.get(key).equals(item)){
                return;
            }
            throw new NcpfRegistryException(this, "Key already registered: "+key+" (Attempted by "+item.getName()+")");
        }
        registry.put(key, item);
    }
}
