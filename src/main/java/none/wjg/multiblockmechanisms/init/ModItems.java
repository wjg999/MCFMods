package none.wjg.multiblockmechanisms.init;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.server.MinecraftServer;
import none.wjg.multiblockmechanisms.item.*;
import none.wjg.multiblockmechanisms.reference.*;
@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	
	public static final MbmItem basicTestItem= new BasicTestItem();
	
	public static void Init(){
		GameRegistry.registerItem(basicTestItem, ItemNames.BASIC_TEST_ITEM_NAME);
		if(!MinecraftServer.getServer().isDedicatedServer()){
		registerItemTexture(basicTestItem,ItemNames.BASIC_TEST_ITEM_NAME);
		}
		
		
		
	}
	//sets up all things needed for it to find the model files and bind the texture to it.
	private static void registerItemTexture(MbmItem item,String itemName){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(
				item,//item object
				0, //metadata
				new ModelResourceLocation(Reference.MODID+":"+itemName, "inventory") //mod id + item name
				);
	}
}
