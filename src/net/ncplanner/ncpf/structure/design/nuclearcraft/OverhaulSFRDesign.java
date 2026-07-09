package net.ncplanner.ncpf.structure.design.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
@NcpfRegistered("nuclearcraft:overhaul_sfr")
public class OverhaulSFRDesign extends NcpfDesign{
    public int[] dimensions = new int[3];
    public int[][][] design;
    public int[][][] block_recipes;
    public int coolant_recipe;
}
