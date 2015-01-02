package none.wjg.multiblockmechanisms.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {

	public static void Init(){
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.hammer,1), "x","y",'x',new ItemStack(Items.iron_ingot),'y',new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.nail,32),"x","x",'x',new ItemStack(Items.iron_ingot));
		
	}
}
