package none.wjg.multiblockmechanisms.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.world.World;

public class ExtendedShapedRecipe extends ShapedRecipes implements IExtendedRecipe{
	private int crafterId;
	public ExtendedShapedRecipe(int crafterId,int width, int height, ItemStack[] input,
			ItemStack output) {
		super(width, height, input, output);
		this.crafterId=crafterId;
	}
	public int getCrafterId(){
		return crafterId;
	}
	public boolean matches(int crafterId,InventoryCrafting craftSlots, World worldIn)
    {
		if(crafterId != this.crafterId){
			return false;
		}
        for (int i = 0; i <= 3 - this.recipeWidth; ++i)
        {
            for (int j = 0; j <= 3 - this.recipeHeight; ++j)
            {
                if (this.checkMatch(craftSlots, i, j, true))
                {
                    return true;
                }

                if (this.checkMatch(craftSlots, i, j, false))
                {
                    return true;
                }
            }
        }

        return false;
    }
	private boolean checkMatch(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_)
    {
        for (int k = 0; k < 3; ++k)
        {
            for (int l = 0; l < 3; ++l)
            {
                int i1 = k - p_77573_2_;
                int j1 = l - p_77573_3_;
                ItemStack itemstack = null;

                if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
                {
                    if (p_77573_4_)
                    {
                        itemstack = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
                    }
                    else
                    {
                        itemstack = this.recipeItems[i1 + j1 * this.recipeWidth];
                    }
                }

                ItemStack itemstack1 = p_77573_1_.getStackInRowAndColumn(k, l);

                if (itemstack1 != null || itemstack != null)
                {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null)
                    {
                        return false;
                    }

                    if (itemstack.getItem() != itemstack1.getItem())
                    {
                        return false;
                    }

                    if (itemstack.getMetadata() != 32767 && itemstack.getMetadata() != itemstack1.getMetadata())
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
