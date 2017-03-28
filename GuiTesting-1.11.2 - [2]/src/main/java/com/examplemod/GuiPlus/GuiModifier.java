package coffeebattryacid.guiplus;

import coffeebattryacid.guiplus.GuiButtonOpensGui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GuiModifier {
  public static Map<Class,List<GuiButton>> buttonList = new HashMap<Class,List<GuiButton>>();
  public static Map<Class,List<GuiLabel>> labelList = new HashMap<Class,List<GuiLabel>>();
  public static GuiScreen lastGui = null;
//[BUTTON SHIT]
  public static void putButton(GuiScreen gui, GuiButton button){
    try {
      Field f = gui.getClass().getSuperclass().getDeclaredField("buttonList");
      f.setAccessible(true);
      ((ArrayList<GuiButton>)f.get(gui)).add(button);
    }
    catch(IllegalAccessException e){}
    catch(NoSuchFieldException e){}
  }


  public static <T> void addButton(Class<T> guiClass, GuiButton btn){
    if (buttonList.get(guiClass) == null) {
      buttonList.put(guiClass, new ArrayList<GuiButton>());
    }
    buttonList.get(guiClass).add(btn);
  }

  public static void appendButtons(Minecraft minecraft){
    if (minecraft == null){System.out.println("\n\n\n\nNULL\n\n\n\n\n");return;}


    if (buttonList.get(minecraft.currentScreen.getClass()) == null) {
      buttonList.put(minecraft.currentScreen.getClass(), new ArrayList<GuiButton>());
    }


    if (minecraft == null) System.out.println("\n\n\n\n\n\n\n\nOHSHITBOII\n\n\n\n\n\n\n\n\n\n");


    for (int i = 0; i < buttonList.get(minecraft.currentScreen.getClass()).size(); i++){
      System.out.println(minecraft.currentScreen.getClass().getName());
      putButton(minecraft.currentScreen,  buttonList.get(minecraft.currentScreen.getClass()).get(i));
    }
  }
//[\BUTTON SHIT]

//[LABEL SHIT]
  public static void putLabel(GuiScreen gui, GuiLabel label){
    try {
      Field f = gui.getClass().getSuperclass().getDeclaredField("labelList");
      f.setAccessible(true);
      ((ArrayList<GuiLabel>)f.get(gui)).add(label);
    }
    catch(IllegalAccessException e){}
    catch(NoSuchFieldException e){}
  }


  public static <T> void addLabel(Class<T> guiClass, GuiLabel lbl, String text){
    lbl.addLine(text);
    if (labelList.get(guiClass) == null) {
      labelList.put(guiClass, new ArrayList<GuiLabel>());
    }
    labelList.get(guiClass).add(lbl);
  }

  public static void appendLabels(Minecraft minecraft){
    if (minecraft == null){System.out.println("\n\n\n\nNULL\n\n\n\n\n");return;}


    if (labelList.get(minecraft.currentScreen.getClass()) == null) {
      labelList.put(minecraft.currentScreen.getClass(), new ArrayList<GuiLabel>());
    }


    if (minecraft == null) System.out.println("\n\n\n\n\n\n\n\nOHSHITBOII\n\n\n\n\n\n\n\n\n\n");


    for (int i = 0; i < labelList.get(minecraft.currentScreen.getClass()).size(); i++){
      System.out.println(minecraft.currentScreen.getClass().getName());
      putLabel(minecraft.currentScreen,  labelList.get(minecraft.currentScreen.getClass()).get(i));
    }
  }
//[\LABEL SHIT]


//update GUIs
  public static void update(Minecraft minecraft){

    if (minecraft.currentScreen == null){
      lastGui = null;
    }
    else{
      if (lastGui == null){
        lastGui = minecraft.currentScreen;
        appendButtons(minecraft);
        appendLabels(minecraft);
      }
      else if( !(minecraft.currentScreen.getClass().equals( lastGui.getClass() ) ) ) {
          lastGui = minecraft.currentScreen;

          if (minecraft.currentScreen != null)
          {
            appendButtons(minecraft);
            appendLabels(minecraft);
          }

      }
    }
  }

}