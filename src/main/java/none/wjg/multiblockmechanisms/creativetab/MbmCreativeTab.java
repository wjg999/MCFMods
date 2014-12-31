package none.wjg.multiblockmechanisms.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import none.wjg.multiblockmechanisms.reference.Reference;

public class MbmCreativeTab extends CreativeTabs{

	private Item tabIcon;
	
	public MbmCreativeTab(int index, String label) {
		super(index, label.toLowerCase());
		// TODO Auto-generated constructor stub
	}

	public MbmCreativeTab SetTabIconItem(Item icon){
		tabIcon=icon;
		return this;
	}
	@Override
	@SideOnly(Side.CLIENT)
    public Item getTabIconItem()
    {
        return tabIcon;
    }

}
