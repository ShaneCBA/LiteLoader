package LiteModTesting;

import com.mumfrey.liteloader.GameLoopListener;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTwo;
import net.minecraft.block.SoundType;

import net.minecraft.init.Bootstrap;

import java.io.File;

public class LiteModTesting implements GameLoopListener
{

  public LiteModTesting(){
    BlockTwo nBlock = new BlockTwo();
  }

  @Override
  public void upgradeSettings(String version, File configPath, File oldConfigPath)
  {
  }

  @Override
  public String getName()
  {
    return "Mod Testing";
  }

  @Override
  public String getVersion()
  {
    return "None";
  }
  
  @Override
  public void init(File configPath)
  {
  }

  @Override
  public void onRunGameLoop(Minecraft minecraft)
  {
    
  }
}