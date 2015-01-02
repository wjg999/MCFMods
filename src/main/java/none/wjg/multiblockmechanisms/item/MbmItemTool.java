package none.wjg.multiblockmechanisms.item;

import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import none.wjg.multiblockmechanisms.reference.Reference;

public class MbmItemTool extends ItemTool {

	protected MbmItemTool(float attackDamage, ToolMaterial material,
			Set effectiveBlocks) {
		super(attackDamage, material, effectiveBlocks);
		// TODO Auto-generated constructor stub
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
