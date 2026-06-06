package net.ncplanner.ncpf.exception;
import net.ncplanner.ncpf.registry.NcpfRegistry;
public class NcpfRegistryException extends RuntimeException{
    public final NcpfRegistry registry;
    public NcpfRegistryException(NcpfRegistry registry, String message){
        super(message);
        this.registry = registry;
    }
}
