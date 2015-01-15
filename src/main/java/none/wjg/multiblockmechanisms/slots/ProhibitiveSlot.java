package none.wjg.multiblockmechanisms.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ProhibitiveSlot extends Slot {

	ItemStack[] allowedItems;
	public ProhibitiveSlot(IInventory inventoryIn, ItemStack[] items, int index, int xPosition,
			int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		allowedItems = items;
	}
	
	public boolean isItemValid(ItemStack stack)
    {
		boolean isSame=false;
		for(int i=0;i<allowedItems.length;i++){
			if(stack.isItemEqual(allowedItems[i])){
				isSame=true;
				break;
			}
		}
        return isSame;
    }
}
