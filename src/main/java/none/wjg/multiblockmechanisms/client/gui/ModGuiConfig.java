package none.wjg.multiblockmechanisms.client.gui;

import java.util.List;

import none.wjg.multiblockmechanisms.handler.ConfigurationHandler;
import none.wjg.multiblockmechanisms.reference.Reference;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ModGuiConfig extends GuiConfig {
	public ModGuiConfig(GuiScreen guiScreen){
		super(guiScreen,new ConfigElement(ConfigurationHandler.configuration.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements()
				,Reference.MODID
				,false //no world restart
				,false //no Minecraft restart
				,GuiConfig.getAbridgedConfigPath(ConfigurationHandler.configuration.toString()));
	}

}
