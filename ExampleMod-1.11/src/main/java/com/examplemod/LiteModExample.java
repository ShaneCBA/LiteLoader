package com.examplemod;

import java.io.*;
import java.util.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.mumfrey.liteloader.PreRenderListener;
import com.mumfrey.liteloader.ChatFilter;
import com.mumfrey.liteloader.PreJoinGameListener;
import com.mumfrey.liteloader.ServerPlayerListener;
import com.mumfrey.liteloader.GameLoopListener;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.core.LiteLoaderEventBroker.ReturnValue;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.network.INetHandler;
import net.minecraft.network.play.server.SPacketJoinGame;


import com.mojang.authlib.GameProfile;

import org.lwjgl.input.Keyboard;


@ExposableOptions(strategy = ConfigStrategy.Versioned, filename="examplemod.json")
public class LiteModExample implements Tickable, ChatFilter, PreJoinGameListener, ServerPlayerListener, GameLoopListener, Configurable
{
	private String code;
	private boolean isCode = false;
	private long millisStart = 0;
	private int rand = 0;
  private boolean active = true;
  private boolean hasJoined = false;
  private boolean doesAJ = true;
  private ServerData serverData;
  private boolean trying = false;
  private long lastReconnect = 0;
	/**
	 * Default constructor. All LiteMods must have a default constructor. In general you should do very little
	 * in the mod constructor EXCEPT for initialising any non-game-interfacing components or performing
	 * sanity checking prior to initialisation
	 */
	public LiteModExample()
	{
	}
	
  @Override
  public Class<? extends ConfigPanel> getConfigPanelClass()
  {
      return ExampleModConfigPanel.class;
  } 

	@Override
	public String getName()
	{
		return "Reaction Cheat";
	}
	
	/**
	 * getVersion() should return the same version string present in the mod metadata, although this is
	 * not a strict requirement.
	 * 
	 * @see com.mumfrey.liteloader.LiteMod#getVersion()
	 */
	@Override
	public String getVersion()
	{
		return "0.0.0";
	}
	
	/**
	 * init() is called very early in the initialisation cycle, before the game is fully initialised, this
	 * means that it is important that your mod does not interact with the game in any way at this point.
	 * 
	 * @see com.mumfrey.liteloader.LiteMod#init(java.io.File)
	 */
	@Override
	public void init(File configPath)
	{
		// The key binding declared above won't do anything unless we register it, ModUtilties provides 
		// a convenience method for this
	}
	
	/**
	 * upgradeSettings is used to notify a mod that its version-specific settings are being migrated
	 * 
	 * @see com.mumfrey.liteloader.LiteMod#upgradeSettings(java.lang.String, java.io.File, java.io.File)
	 */
	@Override
	public void upgradeSettings(String version, File configPath, File oldConfigPath)
	{
	}
	
	@Override
	public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock)
	{
		if (isCode){
			if ((Calendar.getInstance().getTimeInMillis()-millisStart) > (4837+rand)){
				Minecraft.getMinecraft().player.sendChatMessage(code);
				millisStart=0;
				isCode=false;
				System.out.println("\n\n\n\n "+(4837+rand)+" \n\n\n\n");
			}
		}
	}

	@Override
	public boolean onChat(ITextComponent chat, String message, ReturnValue<ITextComponent> newMessage)
	{
		String json = ITextComponent.Serializer.componentToJson(chat);
		if (json.contains("Reaction") && json.contains("Hover for the word to type")){

			code = json.replace("{\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"color\":\"green\",\"text\":\"","").replace("\"}},\"extra\":[{\"color\":\"yellow\",\"text\":\"[\"},{\"bold\":true,\"color\":\"aqua\",\"text\":\"Reaction\"},{\"color\":\"yellow\",\"text\":\"] \"},{\"color\":\"aqua\",\"text\":\"Hover for the word to type!\"}],\"text\":\"\"}","");
			Random r = new Random();
			rand = r.nextInt(2400);
      isCode = true;

      switch (r.nextInt(5)){
        case 1:
          rand = rand + 4000;
          System.out.println("\n\n\nDELAYED\n\n\n");
        break;
        case 2:
          isCode = false;
          System.out.println("\n\n\nDEAD\n\n\n");
        break;
      }
			millisStart = Calendar.getInstance().getTimeInMillis();
		}
    if (message.equals("/getip")) {
      System.out.println("\n\n\n***"+Minecraft.getMinecraft().getCurrentServerData()+"***\n\n\n");
    }
		return true;
	}
  @Override
  public boolean onPreJoinGame(INetHandler netHandler, SPacketJoinGame joinGamePacket)
  {
    System.out.println("\n\n\n\n\n\nHEEEYYYYY\n\n\n\n\n\n\n");
    this.serverData = Minecraft.getMinecraft().getCurrentServerData();
    System.out.println("\n\n\n***"+this.serverData.serverIP+"***\n\n\n");
    this.hasJoined = true;
    this.trying = false;
    return true;
  }

  @Override
  public void onPlayerConnect(EntityPlayerMP player, GameProfile profile)
  {

  }

  @Override
  public void onPlayerLoggedIn(EntityPlayerMP player)
  {
    System.out.println("\n\n\n\n\n\nHEEEYYYYY\n\n\n\n\n\n\n");
    this.serverData = Minecraft.getMinecraft().getCurrentServerData();
    System.out.println("\n\n\n***"+this.serverData.serverIP+"***\n\n\n");
  }

  @Override
  public void onPlayerRespawn(EntityPlayerMP player, EntityPlayerMP oldPlayer, int newDimension, boolean playerWonTheGame)
  {

  }

  @Override
  public void onPlayerLogout(EntityPlayerMP player)
  {

  }

  @Override
  public void onRunGameLoop(Minecraft minecraft)
  {
    if ((this.doesAJ && Minecraft.getMinecraft().getCurrentServerData()==null && this.hasJoined) && !this.trying)
    {
      this.lastReconnect = Calendar.getInstance().getTimeInMillis();
      this.trying = true;
    }

    if (this.doesAJ && (Calendar.getInstance().getTimeInMillis() - this.lastReconnect > 5000) && this.trying)
    {
      System.out.println("\n\n\n\nRECONNECTING\n\n\n\n\n");
      this.lastReconnect = Calendar.getInstance().getTimeInMillis();
      Minecraft.getMinecraft().displayGuiScreen(new GuiConnecting(new GuiMainMenu(), Minecraft.getMinecraft(), this.serverData));}

  }

  public boolean isActive(){
    return this.active;
  }

  public void setActive(boolean val){
    this.active = val;
  }

  public boolean getAJ(){
    return this.doesAJ;
  }
  public void setAJ(boolean val){
    this.doesAJ = val;
  }
}
