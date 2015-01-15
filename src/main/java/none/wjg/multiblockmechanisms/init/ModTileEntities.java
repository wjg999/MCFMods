package none.wjg.multiblockmechanisms.init;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import none.wjg.multiblockmechanisms.reference.BlockNames;
import none.wjg.multiblockmechanisms.tileentities.TETable;
import none.wjg.multiblockmechanisms.tileentities.TileMultipart;

public class ModTileEntities {
	
	public static void Init(){
		GameRegistry.registerTileEntity(TETable.class, BlockNames.TILE_TABLE_NAME);
	}
}
