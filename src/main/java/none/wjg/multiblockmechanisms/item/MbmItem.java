package none.wjg.multiblockmechanisms.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import none.wjg.multiblockmechanisms.reference.Reference;
public class MbmItem extends Item {

	public MbmItem(){
		super();
	}
	@Override
	public String getUnlocalizedName(){
		
		return String.format("item.%s%s", Reference.MODID.toLowerCase()+":",getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemStack){
		
		return String.format("item.%s%s", Reference.MODID.toLowerCase()+":",getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}

	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName){
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
}
