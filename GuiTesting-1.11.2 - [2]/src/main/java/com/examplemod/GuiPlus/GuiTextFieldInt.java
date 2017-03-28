package coffeebattryacid.guiplus;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.FontRenderer;

public class GuiTextFieldInt extends GuiTextFieldFiltered{

  public GuiTextFieldInt(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height){
    super("1234567890",componentId, fontrendererObj, x, y, par5Width, par6Height);
  }
}