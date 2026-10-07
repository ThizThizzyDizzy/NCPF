package net.ncplanner.ncpf.runtime;
import net.ncplanner.ncpf.structure.element.NcpfElement;
public class RuntimeBlock{
    public RuntimeBlock(NcpfElement block){
        this.block = block;
    }
    public final NcpfElement block;
    public NcpfElement blockRecipe;
}
