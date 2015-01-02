package none.wjg.multiblockmechanisms.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.init.ModBlocks;
import none.wjg.multiblockmechanisms.reference.Reference;
import none.wjg.multiblockmechanisms.utility.LogHelper;
//Failed attempt
//I use this class in order to make a dynamic block that mimics everything from the parent block
public class BlockPositionHolder extends MbmBlock {
	private MbmBlock parent = ModBlocks.table;
	public BlockPositionHolder(){
		super();
	}

	public BlockPositionHolder setParent(MbmBlock parentBlock){
		parent=parentBlock;
		this.setBlockBounds((float)parent.getBlockBoundsMinX(), (float)parent.getBlockBoundsMinY(), (float)parent.getBlockBoundsMinZ(), (float)parent.getBlockBoundsMaxX(), (float)parent.getBlockBoundsMaxY(), (float)parent.getBlockBoundsMaxZ());
		return this;
	}
	@Override
    public Material getMaterial()
    {
        return parent.getMaterial();
    }
	@Override
	public int getMobilityFlag(){
		return parent.getMaterial().getMaterialMobility();
	}
	@Override
	public boolean isNormalCube()
    {
        return parent.getMaterial().isOpaque() && parent.isFullCube() && !parent.canProvidePower();
    }
	@Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return !parent.getMaterial().blocksMovement();
    }
	@Override
	public boolean isSolidFullCube()
    {
        return parent.getMaterial().blocksMovement() && this.isFullCube();
    }
	@Override
    public boolean isVisuallyOpaque()
    {
        return parent.getMaterial().blocksMovement() && this.isFullCube();
    }
	@Override 
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		return parent.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
    }

	public BlockPositionHolder registerBlockTextures(int meta,String blockName){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
		.register(
				Item.getItemFromBlock(this),//block object to item object
				meta, //metadata
				new ModelResourceLocation(Reference.MODID+":"+blockName, "inventory") //mod id + block name
				);
		return this;
	}
}
