package none.wjg.multiblockmechanisms;

import none.wjg.multiblockmechanisms.handler.ConfigurationHandler;
import none.wjg.multiblockmechanisms.init.ModBlocks;
import none.wjg.multiblockmechanisms.init.ModItems;
import none.wjg.multiblockmechanisms.proxy.IProxy;
import none.wjg.multiblockmechanisms.reference.Reference;
import none.wjg.multiblockmechanisms.utility.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, version = Reference.VERSION,guiFactory = Reference.GUIFACTORY_CLASS)
public class MultiBlockMechanisms
{

    @SidedProxy(clientSide=Reference.CLIENTPROXY,serverSide=Reference.SERVERPROXY)
    public static IProxy proxy;
    
    @Mod.Instance(Reference.MODID)
    public static MultiBlockMechanisms instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	ConfigurationHandler.Init(event.getSuggestedConfigurationFile());
    	FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    	
    	//ModItems.Init();
    	LogHelper.info("Pre Init finished for MBM");
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	ModItems.Init();
    	ModBlocks.Init();
    	LogHelper.info("Init finished for MBM");
    	
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    	LogHelper.info("Post Init finished for MBM");
    }
}
