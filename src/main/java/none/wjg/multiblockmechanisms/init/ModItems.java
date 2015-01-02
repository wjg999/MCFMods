package none.wjg.multiblockmechanisms.init;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import none.wjg.multiblockmechanisms.item.*;
import none.wjg.multiblockmechanisms.reference.*;
@GameRegistry.ObjectHolder(Reference.MODID)
public class ModItems {
	
	public static final MbmItem basicTestItem= new BasicTestItem();
	public static final MbmItem nail = new ItemNail();
	public static final MbmItemTool hammer = new ItemHammer();
	
	public static void PreInit(){
		GameRegistry.registerItem(basicTestItem, ItemNames.BASIC_TEST_ITEM_NAME);
		GameRegistry.registerItem(hammer, ItemNames.HAMMER_NAME);
		GameRegistry.registerItem(nail, ItemNames.NAIL_NAME);
	}
	public static void Init(){
		if(!MinecraftServer.getServer().isDedicatedServer()){
			registerItemTexture(basicTestItem, 0, ItemNames.BASIC_TEST_ITEM_NAME);
			registerItemTexture(hammer, 0, ItemNames.HAMMER_NAME);
			registerItemTexture(nail, 0, ItemNames.NAIL_NAME);
		
		}
		
	}
	//sets up all things needed for it to find the model files and bind the texture to it.
	private static void registerItemTexture(Item item, int meta, String itemName){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(
				item,//item object
				meta, //metadata
				new ModelResourceLocation(Reference.MODID+":"+itemName, "inventory") //mod id + item name
				);
	}
	private static void registerVariantBlock(MbmItem item,String[] variantNames){
		ModelBakery.addVariantName(item, variantNames);
	}
}
