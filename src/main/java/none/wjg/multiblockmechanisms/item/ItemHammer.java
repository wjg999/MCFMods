package none.wjg.multiblockmechanisms.item;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.MultiBlockMechanisms;
import none.wjg.multiblockmechanisms.init.ModBlocks;
import none.wjg.multiblockmechanisms.init.ModItems;
import none.wjg.multiblockmechanisms.reference.BlockNames;
import none.wjg.multiblockmechanisms.blocks.Table;
import none.wjg.multiblockmechanisms.reference.ModCreativeTabs;
import none.wjg.multiblockmechanisms.reference.ItemNames;
import none.wjg.multiblockmechanisms.utility.LogHelper;
import none.wjg.multiblockmechanisms.utility.UtilityFunctions;

public class ItemHammer extends MbmItemTool {
	private static final Set EFFECTIVE_ON = Sets.newHashSet(new Block[] {Blocks.glass,Blocks.glass_pane,Blocks.glowstone });
	public ItemHammer(){
		super(2.0F,ToolMaterial.IRON,EFFECTIVE_ON);
		this.setMaxStackSize(1);
		this.setMaxDamage(200);
		this.setUnlocalizedName(ItemNames.HAMMER_NAME);
		//LogHelper.info("setting CreativeTab to mbm tab");
		this.setCreativeTab(ModCreativeTabs.MBM_TAB);
		
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ){
		if(UtilityFunctions.hasEnoughOfItem(playerIn,new ItemStack(ModItems.nail,4))&&worldIn.getBlockState(pos).getBlock()==Blocks.planks)
		{
			int meta=-1;
			int otherMeta=0;
			Vec3i posHold=new Vec3i(0,0,0);
			if(worldIn.getBlockState(pos.add(new Vec3i(1,0,0))).getBlock()==Blocks.planks){
				meta=3;
				otherMeta=1;
				posHold = new Vec3i(1,0,0);
			}else if(worldIn.getBlockState(pos.add(new Vec3i(-1,0,0))).getBlock()==Blocks.planks){
				meta=1;
				otherMeta=3;
				posHold = new Vec3i(-1,0,0);
			}else if(worldIn.getBlockState(pos.add(new Vec3i(0,0,1))).getBlock()==Blocks.planks){
				meta=4;
				otherMeta=2;
				posHold = new Vec3i(0,0,1);
			}else if(worldIn.getBlockState(pos.add(new Vec3i(0,0,-1))).getBlock()==Blocks.planks){
				meta=2;
				otherMeta=4;
				posHold = new Vec3i(0,0,-1);
				
			}
			if(meta!=-1){
			LogHelper.info("Fired hammer rightclick");
			
			playerIn.inventory.consumeInventoryItem(ModItems.nail);
			playerIn.inventory.consumeInventoryItem(ModItems.nail);
			playerIn.inventory.consumeInventoryItem(ModItems.nail);
			playerIn.inventory.consumeInventoryItem(ModItems.nail);
			worldIn.setBlockState(pos, ModBlocks.table.getStateFromMeta(meta));
			worldIn.setBlockState(pos.add(posHold), ModBlocks.table.getStateFromMeta(otherMeta));
			}
		}
		return false;
	}
}
