package none.wjg.multiblockmechanisms.item;

import none.wjg.multiblockmechanisms.reference.CreativeTabs;
import none.wjg.multiblockmechanisms.reference.ItemNames;

public class BasicTestItem extends MbmItem {

	public BasicTestItem(){
		super();
		this.setMaxStackSize(1);
		this.setUnlocalizedName(ItemNames.BASIC_TEST_ITEM_NAME);
		this.setCreativeTab(CreativeTabs.TEST_TAB);
	}
}
