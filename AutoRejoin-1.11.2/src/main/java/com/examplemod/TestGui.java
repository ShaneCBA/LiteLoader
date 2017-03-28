package com.examplemod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiButton;

import java.io.IOException;

public class TestGui extends GuiScreen
{
  GuiButton a;

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks)
  {
    super.drawDefaultBackground();
    super.drawScreen(mouseX, mouseY, partialTicks);

    System.out.print("\n\n\n[DEBUG] >>> drawScreen() executed\n\n\n");
  }

  @Override
  public boolean doesGuiPauseGame() {
      return false;
  }
  @Override
  public void initGui() {
    System.out.println("\n\nCreating new gui\n\n\n");
    buttonList.clear();
    this.a = new GuiButton(0, this.width/2 -100, this.height / 2 -24, "This is button a");
    this.buttonList.add(this.a);

    super.initGui();
  }
  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    if (button == this.a){
      this.mc.displayGuiScreen(null);
      if (this.mc.currentScreen   == null)
        this.mc.setIngameFocus();
    }
  }
}