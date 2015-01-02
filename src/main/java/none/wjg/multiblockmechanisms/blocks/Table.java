package none.wjg.multiblockmechanisms.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.reference.BlockNames;
import none.wjg.multiblockmechanisms.reference.ModCreativeTabs;
import none.wjg.multiblockmechanisms.utility.LogHelper;


public class Table extends MbmBlock {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public Table(){
		super(Material.wood);
		this.setUnlocalizedName(BlockNames.TABLE_NAME);
		this.setCreativeTab(ModCreativeTabs.MBM_TAB);
		//this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}
	
	
    @Override
    public boolean isOpaqueCube(){
            return false;
    }
    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
    	LogHelper.info(placer.getHorizontalFacing().getOpposite());
    	
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	LogHelper.info(meta);
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	LogHelper.info(state);
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
    }
    @Override
    public BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
    	return false;
    }

    

}
