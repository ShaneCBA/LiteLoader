package LiteModTesting;

import net.minecraft.util.ResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.Block;

public class NewBlock extends Block {

  public NewBlock(int id, Material mat){
    super(mat);

    Block.REGISTRY.register(id, new ResourceLocation("minecraft:newblock"), this);

    setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    setUnlocalizedName("minecraft:newblock");
    setHardness(1);   
    setResistance(1);

  }

}