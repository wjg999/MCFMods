package none.wjg.multiblockmechanisms.reference;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import none.wjg.multiblockmechanisms.init.ModItems;

public class ModCreativeTabs {
	public static final CreativeTabs TEST_TAB = new CreativeTabs(CreativeTabs.getNextID(),Reference.TESTTAB_NAME) {//best to have mod id in name so that it is YOUR tab
        public Item getTabIconItem() {
            return ModItems.basicTestItem; //returns item or block you want as the icon though for block you need  to use Item.getItemFromBlock(block)
        }
    };
	public static final CreativeTabs MBM_TAB = new CreativeTabs(CreativeTabs.getNextID(),Reference.MBMTAB_NAME) {//best to have mod id in name so that it is YOUR tab
        public Item getTabIconItem() {
            return ModItems.hammer; //returns item or block you want as the icon though for block you need  to use Item.getItemFromBlock(block)
        }
    };
}
