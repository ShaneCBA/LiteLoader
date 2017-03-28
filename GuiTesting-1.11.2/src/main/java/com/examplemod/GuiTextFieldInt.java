package CoffeeAndBatteryAcid.guiTesting.custom;

import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.FontRenderer;

public class GuiTextFieldInt extends GuiTextField{

  public GuiTextFieldInt(int componentId, FontRenderer fontrendererObj, int x, int y, int par5Width, int par6Height){
    super(componentId, fontrendererObj, x, y, par5Width, par6Height);
  }

  @Override
  public boolean textboxKeyTyped(char typedChar, int keyCode) {
    if (("1234567890").indexOf(typedChar) >= 0 || keyCode == 14){
      return super.textboxKeyTyped(typedChar, keyCode);
    }
    else {
      System.out.print("\n\n\nCHAR:    "+typedChar);
      System.out.print("\n\n\nKeyCode: "+keyCode+"\n\n\n");
      return false;
    }
  }
}