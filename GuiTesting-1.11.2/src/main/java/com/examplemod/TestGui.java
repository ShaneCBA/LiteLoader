package CoffeeAndBatteryAcid.guiTesting;

import CoffeeAndBatteryAcid.guiTesting.custom.GuiTextFieldInt;
import CoffeeAndBatteryAcid.guiTesting.custom.GuiTextFieldFiltered;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import java.io.IOException;
import java.util.ArrayList;

public class TestGui extends GuiScreen
{
  ArrayList<GuiTextField> textFieldList = new ArrayList<GuiTextField>();

  GuiTextField selectedTextField;

  GuiButton closeGui;
  GuiTextField textBox;
  GuiTextField filteredText;

  @Override
  public void drawScreen(int mouseX, int mouseY, float partialTicks)
  {

    super.drawDefaultBackground();

    super.drawScreen(mouseX, mouseY, partialTicks);

    for (int i = 0; i < this.textFieldList.size(); i++)
    {
      ((GuiTextField)this.textFieldList.get(i)).drawTextBox();
      ((GuiTextField)this.textFieldList.get(i)).drawTextBox();
    }

  }

  @Override
  protected void mouseClicked(int mouseX, int mouseY, int mouseButton)
    throws IOException
  {
    if (mouseButton == 0) {
      for (int i = 0; i < this.textFieldList.size(); i++)
      {
        GuiTextField guitextfield = (GuiTextField)this.textFieldList.get(i);
        guitextfield.mouseClicked(mouseX, mouseY, mouseButton);
        if (guitextfield.isFocused())
        {
          this.selectedTextField = guitextfield;
        }
      }
    }
    super.mouseClicked(mouseX, mouseY, mouseButton);
  }

  @Override
  protected void keyTyped(char typedChar, int keyCode)
    throws IOException
  {
    for (int i = 0; i < this.textFieldList.size(); i++)
    {
      GuiTextField guitextfield = (GuiTextField)this.textFieldList.get(i);
      guitextfield.textboxKeyTyped(typedChar, keyCode);
    }
    super.keyTyped(typedChar, keyCode);
  }

  @Override
  public void initGui()
  {
    buttonList.clear();

    closeGui = new GuiButton(0, this.width/2 - 50, this.height -24, 100, 20, "Close");
    buttonList.add(closeGui);

    textBox = new GuiTextFieldInt(1, Minecraft.getMinecraft().fontRendererObj, this.width/2 - 60, this.height /2 -40, 120, 20);
    textFieldList.add(textBox);
    filteredText = new GuiTextFieldFiltered("abcdefghijklmnopqrstuvwxyz", 1, Minecraft.getMinecraft().fontRendererObj, this.width/2 - 60, this.height /2, 120, 20);
    textFieldList.add(filteredText);

    super.initGui();
  }
  @Override
  public boolean doesGuiPauseGame() {
      return false;
  }

  @Override
  protected void actionPerformed(GuiButton button) throws IOException {
    if (button == this.closeGui){
      this.mc.displayGuiScreen(null);
      if (this.mc.currentScreen   == null)
        this.mc.setIngameFocus();
    }
  }

}