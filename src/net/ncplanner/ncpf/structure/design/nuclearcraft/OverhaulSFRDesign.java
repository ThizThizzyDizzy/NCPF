package net.ncplanner.ncpf.structure.design.nuclearcraft;
import net.ncplanner.ncpf.registry.NcpfRegistered;
import net.ncplanner.ncpf.structure.design.NcpfDesign;
@NcpfRegistered("nuclearcraft:overhaul_sfr")
public class OverhaulSFRDesign extends NcpfDesign{
    int[] dimensions = new int[3];
    int[][][] design;
    int[][][] block_recipes;
    int coolant_recipe;
}
