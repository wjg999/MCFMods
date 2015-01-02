package none.wjg.multiblockmechanisms.item;

import none.wjg.multiblockmechanisms.MultiBlockMechanisms;
import none.wjg.multiblockmechanisms.reference.ModCreativeTabs;
import none.wjg.multiblockmechanisms.reference.ItemNames;
import none.wjg.multiblockmechanisms.utility.LogHelper;

public class ItemNail extends MbmItem {
	public ItemNail(){
		super();
		this.setMaxStackSize(64);
		this.setUnlocalizedName(ItemNames.NAIL_NAME);
		this.setCreativeTab(ModCreativeTabs.MBM_TAB);
	}
}
