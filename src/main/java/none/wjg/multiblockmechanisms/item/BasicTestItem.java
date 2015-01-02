package none.wjg.multiblockmechanisms.item;

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
}
