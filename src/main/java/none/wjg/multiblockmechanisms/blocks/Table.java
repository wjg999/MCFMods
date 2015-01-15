package none.wjg.multiblockmechanisms.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.MultiBlockMechanisms;
import none.wjg.multiblockmechanisms.init.ModItems;
import none.wjg.multiblockmechanisms.reference.BlockNames;
import none.wjg.multiblockmechanisms.reference.ModCreativeTabs;
import none.wjg.multiblockmechanisms.tileentities.TETable;
import none.wjg.multiblockmechanisms.utility.LogHelper;


public class Table extends BlockContainer {
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool HASPAPER = PropertyBool.create("hasPaper");

	public Table(){
		super(Material.wood);
		this.setHardness(2F);
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
    	//LogHelper.info(placer.getHorizontalFacing().getOpposite());
    	
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(HASPAPER, false);
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
    	boolean using=false;
    	if(meta>=10){
    		using=true;
    		meta-=10;
    	}
        return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta)).withProperty(HASPAPER,using);
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
    	//LogHelper.info(state);
    	int i=(state.getValue(HASPAPER).equals(true))?10:0;
        return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex()+i;
    }
    @Override
    public BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING,HASPAPER});
    }
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof TETable)
            {
                playerIn.openGui(MultiBlockMechanisms.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }

            return true;
        }
    }


    public int getRenderType()
    {
        return 3;
    }
    
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TETable();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

		IInventory inventory = worldIn.getTileEntity(pos) instanceof IInventory ? (IInventory)worldIn.getTileEntity(pos) : null;

		if (inventory != null){
			// For each slot in the inventory
			for (int i = 0; i < inventory.getSizeInventory(); i++){
				// If the slot is not empty
				if (inventory.getStackInSlot(i) != null)
				{
					// Create a new entity item with the item stack in the slot
					EntityItem item = new EntityItem(worldIn, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, inventory.getStackInSlot(i));

					// Apply some random motion to the item
					float multiplier = 0.1f;
					float motionX = worldIn.rand.nextFloat() - 0.5f;
					float motionY = worldIn.rand.nextFloat() - 0.5f;
					float motionZ = worldIn.rand.nextFloat() - 0.5f;

					item.motionX = motionX * multiplier;
					item.motionY = motionY * multiplier;
					item.motionZ = motionZ * multiplier;

					// Spawn the item in the world
					worldIn.spawnEntityInWorld(item);
				}
			}
			// Clear the inventory so nothing else (such as another mod) can do anything with the items
			inventory.clear();
		}

		// Super MUST be called last because it removes the tile entity
		super.breakBlock(worldIn, pos, state);
	}
    
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }

}
