package none.wjg.multiblockmechanisms.handler;

import java.io.File;

import none.wjg.multiblockmechanisms.reference.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {
	
	public static Configuration configuration;
	public static boolean configValue=false;
	
	public static void Init(File configFile){
		if(configuration==null){
		configuration = new Configuration(configFile);
		LoadConfiguration();
		}
	}
	
	@SubscribeEvent
	public void OnConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event){
		if(Reference.MODID.equalsIgnoreCase(event.modID)){
			LoadConfiguration();
		}
	}
	
	private static void LoadConfiguration(){
		configValue = configuration.getBoolean("configValue", Configuration.CATEGORY_GENERAL, true, "This is an example config value");

		if(configuration.hasChanged())
		{
			configuration.save();
		}
	
	}
}
