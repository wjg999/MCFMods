package none.wjg.multiblockmechanisms.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import none.wjg.multiblockmechanisms.blocks.BasicTestBlock;
import none.wjg.multiblockmechanisms.blocks.MbmBlock;
import none.wjg.multiblockmechanisms.item.MbmItem;
import none.wjg.multiblockmechanisms.reference.BlockNames;
import none.wjg.multiblockmechanisms.reference.Reference;
@GameRegistry.ObjectHolder(Reference.MODID)
public class ModBlocks {

	public static final MbmBlock basicTestBlock = new BasicTestBlock();
	
	public static void Init(){
		GameRegistry.registerBlock(basicTestBlock, BlockNames.BASIC_TEST_BLOCK_NAME);
		if(!MinecraftServer.getServer().isDedicatedServer()){
		registerBlockTextures(basicTestBlock, BlockNames.BASIC_TEST_BLOCK_NAME);
		}
		
		
	}
	//sets up all things needed for it to find the model files and bind the texture to it.
	private static void registerBlockTextures(MbmBlock block,String blockName){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(
				Item.getItemFromBlock(block),//block object to item object
				0, //metadata
				new ModelResourceLocation(Reference.MODID+":"+blockName, "inventory") //mod id + block name
				);
	}
}
