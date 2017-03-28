package coffeebattryacid.autoreconnect;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public class ScreenReconnect extends GuiScreen{
  public boolean cancel;

  private GuiScreen previousGuiScreen;
  private LiteModExample mod;

  public ScreenReconnect(GuiScreen previousGuiScreen, LiteModExample mod){
    this.previousGuiScreen = previousGuiScreen;
    this.mod = mod;
  }

  @Override
  public void initGui()
  {
    this.buttonList.clear();
    this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 120 + 12, "Cancel"));
  }

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks)
  {
    drawDefaultBackground();
    drawCenteredString(this.fontRendererObj, "Auto-Reconnecting...", this.width / 2, this.height / 2 - 50, 16777215);
    super.drawScreen(mouseX, mouseY, partialTicks);
  }

  @Override
  public void actionPerformed(GuiButton button)
    throws IOException
  {
    if (button.id == 0)
    {
      this.cancel = true;
      mod.hasJoined = false;
      mod.cancelReconnect();
      this.mc.displayGuiScreen(this.previousGuiScreen);
    }
  }
}