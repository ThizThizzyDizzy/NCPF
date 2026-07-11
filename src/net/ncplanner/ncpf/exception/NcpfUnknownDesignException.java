package net.ncplanner.ncpf.exception;
import net.ncplanner.ncpf.structure.design.UnknownDesign;
public class NcpfUnknownDesignException extends NcpfException{
    public final UnknownDesign design;
    public NcpfUnknownDesignException(UnknownDesign design, String message){
        super(message);
        this.design = design;
    }
}
