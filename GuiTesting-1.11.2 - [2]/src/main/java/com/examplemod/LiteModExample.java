package CoffeeAndBatteryAcid.guiTesting;

import CoffeeAndBatteryAcid.guiTesting.ExampleModConfigPanel;
import coffeebattryacid.guiplus.GuiModifier;
import coffeebattryacid.guiplus.GuiButtonOpensGui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.GameLoopListener;
import com.mumfrey.liteloader.PreRenderListener;
import com.mumfrey.liteloader.Tickable;
import com.mumfrey.liteloader.core.LiteLoader;
import com.mumfrey.liteloader.modconfig.ConfigPanel;
import com.mumfrey.liteloader.modconfig.ConfigStrategy;
import com.mumfrey.liteloader.modconfig.ExposableOptions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import org.lwjgl.input.Keyboard;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
/**
 * This is a very simple example LiteMod, it draws an analogue clock on the minecraft HUD using
 * a traditional onTick hook supplied by LiteLoader's "Tickable" interface.
 *
 * @author Adam Mummery-Smith
 */
@ExposableOptions(strategy = ConfigStrategy.Versioned, filename="examplemod.json")
public class LiteModExample implements Tickable, PreRenderListener, Configurable, GameLoopListener
{
	/**
	 * This is our instance of Clock which we will draw every tick
	 */
	
	/**
	 * This is a keybinding that we will register with the game and use to toggle the clock
	 * 
	 * Notice that we specify the key name as an *unlocalised* string. The localisation is provided from the included resource file
	 */
	private static KeyBinding keyBinding = new KeyBinding("Toggle Gui", Keyboard.KEY_F12, "key.categories.litemods");
	
	GuiButtonOpensGui cstm;
	public GuiScreen lastGui = null;
	/**
	 * Default constructor. All LiteMods must have a default constructor. In general you should do very little
	 * in the mod constructor EXCEPT for initialising any non-game-interfacing components or performing
	 * sanity checking prior to initialisation
	 */
	public LiteModExample()
	{
	}
	
	/**
	 * getName() should be used to return the display name of your mod and MUST NOT return null
	 * 
	 * @see com.mumfrey.liteloader.LiteMod#getName()
	 */
	@Override
	public String getName()
	{
		return "Example Mod";
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
	
	@Override
	public Class<? extends ConfigPanel> getConfigPanelClass()
	{
	    return ExampleModConfigPanel.class;
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
		LiteLoader.getInput().registerKeyBinding(LiteModExample.keyBinding);

	  //GuiModifier.addButton(GuiMainMenu.class, new GuiButtonOpensGui(42, 0, 0, 100, 20, "Open Gui", new TestGui()));
	  GuiModifier.addLabel(GuiMainMenu.class, new GuiLabel(Minecraft.getMinecraft().fontRendererObj, 1, 60, 60, 100, 20, 0xDDDDFF), "yup");
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
  public void onRunGameLoop(Minecraft minecraft)
  {
  	GuiModifier.update(minecraft);
  }

	@Override
	public void onRenderWorld(float partialTicks)
	{
//		System.err.printf(">> onRenderWorld!\n");
	}

	@Override
	public void onSetupCameraTransform(float partialTicks, int pass, long timeSlice)
	{
//		System.err.printf(">> onSetupCameraTransform %s, %d, %d!\n", partialTicks, pass, timeSlice);
	}

	@Override
	public void onRenderSky(float partialTicks, int pass)
	{
//		System.err.printf(">> onRenderSky %s, %d!\n", partialTicks, pass);
	}

	@Override
	public void onRenderClouds(float partialTicks, int pass, RenderGlobal renderGlobal)
	{
//		System.err.printf(">> onRenderClouds %s, %d!\n", partialTicks, pass);
	}

	@Override
	public void onRenderTerrain(float partialTicks, int pass)
	{
//		System.err.printf(">> onRenderTerrain %s, %d!\n", partialTicks, pass);
	}
}
