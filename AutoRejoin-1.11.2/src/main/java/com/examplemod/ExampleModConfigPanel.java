package coffeebattryacid.autoreconnect;

import com.mumfrey.liteloader.Configurable;
import com.mumfrey.liteloader.client.gui.GuiCheckbox;
import com.mumfrey.liteloader.modconfig.ConfigPanelHost;
import com.mumfrey.liteloader.modconfig.AbstractConfigPanel;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.Minecraft;

/**
 * This is a simple example of adding a config panel to a mod. Your LiteMod class should implement
 * {@link Configurable} and return this class in order to support the settings functionality of the
 * mod panel.
 * 
 * @author Adam Mummery-Smith
 */
public class ExampleModConfigPanel extends AbstractConfigPanel
{
    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.modconfig.ConfigPanel#getPanelTitle()
     */
    @Override
    public String getPanelTitle()
    {
        return I18n.format("Auto Rejoin Settings");
    }
    
    /* (non-Javadoc)
     * @see com.mumfrey.liteloader.modconfig.AbstractConfigPanel#addOptions(com.mumfrey.liteloader.modconfig.ConfigPanelHost)
     */
    @Override
    protected void addOptions(ConfigPanelHost host)
    {
        final LiteModExample mod = host.<LiteModExample>getMod();
        
        this.addLabel(1, 0, 0, 200, 32, 0xFFFF55, I18n.format("Change Settings"));
        this.addControl(new GuiCheckbox(0, 0, 32, "AutoReconnect"), new ConfigOptionListener<GuiCheckbox>(){

            @Override
            public void actionPerformed(GuiCheckbox control)
            {
                mod.setAJ(control.checked = !control.checked);
            }
        }).checked = mod.getAJ();
    }

    @Override
    public void onPanelHidden()
    {
        // This example applies the changes immediately, however you may wish to only save changes
        // when the user clicks "save and close". In which case you should apply your changes here
    }
}
