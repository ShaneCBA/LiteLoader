package net.minecraft.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.init.Bootstrap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class BlockTwo
  extends Block
{
  public BlockTwo()
  {
    super(Material.ROCK, Material.ROCK.getMaterialMapColor());
    this.setHardness(3.0F).setResistance(5.0F).setSoundType(SoundType.STONE).setUnlocalizedName("twoBlock");
    super.REGISTRY.register(256, new ResourceLocation("block_two"), this);
    setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    Bootstrap.register();
  }
  
  public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
  {
    return new ItemStack(this);
  }
  
  public int damageDropped(IBlockState state)
  {
    return 0;
  }
}
