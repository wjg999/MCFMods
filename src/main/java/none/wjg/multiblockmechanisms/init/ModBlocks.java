package none.wjg.multiblockmechanisms.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.registry.GameRegistry;
import none.wjg.multiblockmechanisms.blocks.BasicTestBlock;
import none.wjg.multiblockmechanisms.blocks.BlockPositionHolder;
import none.wjg.multiblockmechanisms.blocks.MbmBlock;
import none.wjg.multiblockmechanisms.blocks.Table;
import none.wjg.multiblockmechanisms.item.MbmItem;
import none.wjg.multiblockmechanisms.reference.BlockNames;
import none.wjg.multiblockmechanisms.reference.Reference;
@GameRegistry.ObjectHolder(Reference.MODID)
public class ModBlocks {

	public static final MbmBlock basicTestBlock = new BasicTestBlock();
	public static final Table table = new Table();
	//public static final BlockPositionHolder placeHolder = new BlockPositionHolder();
	
	public static void PreInit(){
		GameRegistry.registerBlock(basicTestBlock, BlockNames.BASIC_TEST_BLOCK_NAME);	
		GameRegistry.registerBlock(table, BlockNames.TABLE_NAME);
		//GameRegistry.registerBlock(placeHolder, BlockNames.PLACEHOLDER_NAME);
	}
	public static void Init(){
		if(!MinecraftServer.getServer().isDedicatedServer()){
			registerBlockTextures(basicTestBlock, 0, BlockNames.BASIC_TEST_BLOCK_NAME);
			registerBlockTextures(table,0,BlockNames.TABLE_NAME);
			
		}
	}
	//sets up all things needed for it to find the model files and bind the texture to it.
	private static void registerBlockTextures(Block block,int meta,String blockName){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(
				Item.getItemFromBlock(block),//block object to item object
				meta, //metadata
				new ModelResourceLocation(Reference.MODID+":"+blockName, "inventory") //mod id + block name
				);
	}
}
