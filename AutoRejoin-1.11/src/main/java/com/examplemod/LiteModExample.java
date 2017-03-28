package coffeebattryacid.autoreconnect;

import java.io.*;
import java.util.*;

import coffeebattryacid.autoreconnect.ScreenReconnect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.mumfrey.liteloader.PreRenderListener;
import com.mumfrey.liteloader.ChatFilter;
import com.mumfrey.liteloader.PreJoinGameListener;
import com.mumfrey.liteloader.ServerPlayerListener;
import com.mumfrey.liteloader.GameLoopListener;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.OutboundChatFilter;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.core.LiteLoaderEventBroker.ReturnValue;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiErrorScreen;
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
public class LiteModExample implements Tickable, OutboundChatFilter, PreJoinGameListener, ServerPlayerListener, GameLoopListener, Configurable
{
  private boolean hasJoined = false;
  private boolean doesAJ = true;
  private ServerData serverData;
  public boolean trying = false;
  private long lastReconnect = 0;
  private ScreenReconnect reconnectScreen;
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
		return "Auto Rejoin";
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
	}

	@Override
  public boolean onSendChatMessage(String message){
    return true;
  }

  @Override
  public boolean onPreJoinGame(INetHandler netHandler, SPacketJoinGame joinGamePacket)
  {
    if (!Minecraft.getMinecraft().isSingleplayer()){
      System.out.println("\n\n\n\n\n\nHEEEYYYYY\n\n\n\n\n\n\n");
      this.serverData = Minecraft.getMinecraft().getCurrentServerData();
      System.out.println("\n\n\n***"+this.serverData.serverIP+"***\n\n\n");
      this.hasJoined = true;
      this.trying = false;
    }
    return true;
  }

  @Override
  public void onPlayerConnect(EntityPlayerMP player, GameProfile profile)
  {

  }

  @Override
  public void onPlayerLoggedIn(EntityPlayerMP player)
  {
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
    if ((this.doesAJ && minecraft.getCurrentServerData()==null && this.hasJoined) && !this.trying)
    {
      System.out.println("\n\n\n\nStart Reconnect\n\n\n\n\n");

      this.lastReconnect = Calendar.getInstance().getTimeInMillis();

      reconnectScreen = new ScreenReconnect(minecraft.currentScreen, this);
      if (!reconnectScreen.cancel){
        minecraft.displayGuiScreen(reconnectScreen);
        this.trying = true;
      }
    }

    if (this.doesAJ && (Calendar.getInstance().getTimeInMillis() - this.lastReconnect > 5000) && this.trying && this.hasJoined)
    {

      if (!reconnectScreen.cancel){
        System.out.println("\n\n\n\nRECONNECTING\n\n\n\n\n");
        this.lastReconnect = Calendar.getInstance().getTimeInMillis();
        minecraft.displayGuiScreen(new GuiConnecting(new GuiMainMenu(), Minecraft.getMinecraft(), this.serverData));
      }
    }

  }

  public boolean getAJ(){
    return this.doesAJ;
  }
  public void setAJ(boolean val){
    this.doesAJ = val;
  }

  public void setString(String str)
  {
    System.out.println("\n\n\n>>>"+str+"\n\n\n\n");
  }

  public void cancelReconnect(){
    this.trying = false;
    this.hasJoined = false;
  }
}
