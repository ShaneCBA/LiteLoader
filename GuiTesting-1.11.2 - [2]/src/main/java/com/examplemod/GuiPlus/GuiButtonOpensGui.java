package coffeebattryacid.guiplus;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiButton;

import java.util.*;

public class GuiButtonOpensGui extends GuiButton {
  GuiScreen customGui;

  public GuiButtonOpensGui(int id, int x, int y, int width, int height, String text, GuiScreen customGui){
    super(id, x, y, width, height, text);
    this.customGui = customGui;
  }

  @Override
  public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
  {
    boolean pressed = super.mousePressed(mc, mouseX, mouseY);
    if (pressed)
      mc.displayGuiScreen(this.customGui);
    return pressed;
  }

}