package net.ncplanner.ncpf.structure.design.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
@NcpfRegistered("nuclearcraft:underhaul_sfr")
public class UnderhaulSFRDesign extends NcpfDesign{
    int[] dimensions = new int[3];
    int[][][] design;
    int[][][] block_recipes;
    int fuel;
}
