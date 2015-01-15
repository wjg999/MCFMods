package none.wjg.multiblockmechanisms.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public interface IExtendedRecipe extends IRecipe {

	public int getCrafterId();
	
	public boolean matches(int crafterId,InventoryCrafting craftingSlots,World world);
}
