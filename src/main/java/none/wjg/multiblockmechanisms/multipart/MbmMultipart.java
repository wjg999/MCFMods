package none.wjg.multiblockmechanisms.multipart;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.tileentities.TileMultipart;

public class MbmMultipart extends BlockContainer {

	protected MbmMultipart() {
		super(Material.ground);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileMultipart();
	}

}
