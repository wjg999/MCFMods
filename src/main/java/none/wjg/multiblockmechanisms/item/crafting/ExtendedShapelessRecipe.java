package none.wjg.multiblockmechanisms.item.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class ExtendedShapelessRecipe extends ShapelessRecipes implements IExtendedRecipe {
	private int crafterId;
	public ExtendedShapelessRecipe(int crafterId,ItemStack output, List inputList) {
		super(output, inputList);
		this.crafterId=crafterId;
		// TODO Auto-generated constructor stub
	}
	public int getCrafterId(){
		return crafterId;
	}
	
	public boolean matches(int crafterId,InventoryCrafting craftSlots, World worldIn)
    {
		if(crafterId!=this.crafterId){
			return false;
		}
        ArrayList arraylist = Lists.newArrayList(this.recipeItems);

        for (int i = 0; i < craftSlots.getHeight(); ++i)
        {
            for (int j = 0; j < craftSlots.getWidth(); ++j)
            {
                ItemStack itemstack = craftSlots.getStackInRowAndColumn(j, i);

                if (itemstack != null)
                {
                    boolean flag = false;
                    Iterator iterator = arraylist.iterator();

                    while (iterator.hasNext())
                    {
                        ItemStack itemstack1 = (ItemStack)iterator.next();

                        if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getMetadata() == 32767 || itemstack.getMetadata() == itemstack1.getMetadata()))
                        {
                            flag = true;
                            arraylist.remove(itemstack1);
                            break;
                        }
                    }

                    if (!flag)
                    {
                        return false;
                    }
                }
            }
        }

        return arraylist.isEmpty();
    }
	
}
