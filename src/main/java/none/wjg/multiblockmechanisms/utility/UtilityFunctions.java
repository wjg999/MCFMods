package none.wjg.multiblockmechanisms.utility;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class UtilityFunctions {

	public static boolean hasEnoughOfItem(EntityPlayer playerIn,ItemStack stack)
    {
        int i;
        int itemsFound=0;
        for (i = 0; i < playerIn.inventory.mainInventory.length; ++i)
        {
            if (playerIn.inventory.mainInventory[i] != null && playerIn.inventory.mainInventory[i].isItemEqual(stack))
            {
            	if(playerIn.inventory.mainInventory[i].stackSize>=stack.stackSize-itemsFound)
            	{
            		//LogHelper.info("enough");
            		return true;
            	}
            	else{
                	itemsFound+=playerIn.inventory.mainInventory[i].stackSize;
            		LogHelper.info(itemsFound+"/"+stack.stackSize);
            	}
            }
        }
        return false;
    }
}
