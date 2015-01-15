package none.wjg.multiblockmechanisms.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import none.wjg.multiblockmechanisms.item.crafting.ModCraftingManager;

public class ModRecipes {
	
	public static void Init(){
		ModCraftingManager mCraftManager = ModCraftingManager.getInstance();
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.hammer,1), "x","y",'x',new ItemStack(Items.iron_ingot),'y',new ItemStack(Items.stick));
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.nail,32),"x","x",'x',new ItemStack(Items.iron_ingot));
		mCraftManager.addCrafter("table");
		mCraftManager.addRecipe(mCraftManager.getCrafterFromName("table"), new ItemStack(ModItems.nail), " x ",'x',new ItemStack(Items.iron_ingot));
		
	}
}
