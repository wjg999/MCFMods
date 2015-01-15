package none.wjg.multiblockmechanisms.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.ExtendedPlayer;
import none.wjg.multiblockmechanisms.MultiBlockMechanisms;
import none.wjg.multiblockmechanisms.reference.ModCreativeTabs;
import none.wjg.multiblockmechanisms.reference.ItemNames;
import none.wjg.multiblockmechanisms.utility.LogHelper;

public class BasicTestItem extends MbmItem {

	public BasicTestItem(){
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(ItemNames.BASIC_TEST_ITEM_NAME);
		//LogHelper.info("setting CreativeTab to test tab");
		this.setCreativeTab(ModCreativeTabs.TEST_TAB);
		
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
    	{
		if (!world.isRemote)
		{
			ExtendedPlayer props = ExtendedPlayer.get(player);
			System.out.println(props.getUnderstanding());
		}
		return itemstack;
    }
}
