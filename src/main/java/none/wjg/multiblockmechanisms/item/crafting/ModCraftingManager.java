package none.wjg.multiblockmechanisms.item.crafting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.BlockStoneSlabNew;
import net.minecraft.block.BlockWall;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.utility.LogHelper;

public class ModCraftingManager
{
    private static final ModCraftingManager instance = new ModCraftingManager();
    private final List recipes = Lists.newArrayList();
    private final List crafters = Lists.newArrayList();
    public static ModCraftingManager getInstance()
    {
        return instance;
    }

    private ModCraftingManager()
    {
    	this.addCrafter("testCrafter");
        this.addRecipe(this.getCrafterFromName("testCrafter"),new ItemStack(Items.armor_stand, 1), new Object[] {"   ", "// ", "/  ", '/', Items.stick});
        Collections.sort(this.recipes, new Comparator()
        {
            public int compare(IRecipe recipe1, IRecipe recipe2)
            {
                return recipe1 instanceof ShapelessRecipes && recipe2 instanceof ShapedRecipes ? 1 : (recipe2 instanceof ShapelessRecipes && recipe1 instanceof ShapedRecipes ? -1 : (recipe2.getRecipeSize() < recipe1.getRecipeSize() ? -1 : (recipe2.getRecipeSize() > recipe1.getRecipeSize() ? 1 : 0)));
            }
            public int compare(Object  recipe1, Object recipe2)
            {
                return this.compare((IRecipe) recipe1, (IRecipe)recipe2);
            }
        });
    }

    public void addCrafter(String crafterName){
    	crafters.add(crafterName);
    }
    
    public int getCrafterFromName(String name){
    	return crafters.indexOf(name);
    }
    public ExtendedShapedRecipe addRecipe(int crafterId,ItemStack stack, Object ... recipeComponents)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;

        if (recipeComponents[i] instanceof String[])
        {
            String[] astring = (String[])((String[])recipeComponents[i++]);

            for (int l = 0; l < astring.length; ++l)
            {
                String s1 = astring[l];
                ++k;
                j = s1.length();
                s = s + s1;
            }
        }
        else
        {
            while (recipeComponents[i] instanceof String)
            {
                String s2 = (String)recipeComponents[i++];
                ++k;
                j = s2.length();
                s = s + s2;
            }
        }

        HashMap hashmap;

        for (hashmap = Maps.newHashMap(); i < recipeComponents.length; i += 2)
        {
            Character character = (Character)recipeComponents[i];
            ItemStack itemstack1 = null;

            if (recipeComponents[i + 1] instanceof Item)
            {
                itemstack1 = new ItemStack((Item)recipeComponents[i + 1]);
            }
            else if (recipeComponents[i + 1] instanceof Block)
            {
                itemstack1 = new ItemStack((Block)recipeComponents[i + 1], 1, 32767);
            }
            else if (recipeComponents[i + 1] instanceof ItemStack)
            {
                itemstack1 = (ItemStack)recipeComponents[i + 1];
            }

            hashmap.put(character, itemstack1);
        }

        ItemStack[] aitemstack = new ItemStack[j * k];

        for (int i1 = 0; i1 < j * k; ++i1)
        {
            char c0 = s.charAt(i1);

            if (hashmap.containsKey(Character.valueOf(c0)))
            {
                aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c0))).copy();
            }
            else
            {
                aitemstack[i1] = null;
            }
        }

        ExtendedShapedRecipe shapedrecipes = new ExtendedShapedRecipe(crafterId,j, k, aitemstack, stack);
        this.recipes.add(shapedrecipes);
        return shapedrecipes;
    }

    public void addShapelessRecipe(int crafterId,ItemStack stack, Object ... recipeComponents)
    {
        ArrayList arraylist = Lists.newArrayList();
        Object[] aobject = recipeComponents;
        int i = recipeComponents.length;

        for (int j = 0; j < i; ++j)
        {
            Object object1 = aobject[j];

            if (object1 instanceof ItemStack)
            {
                arraylist.add(((ItemStack)object1).copy());
            }
            else if (object1 instanceof Item)
            {
                arraylist.add(new ItemStack((Item)object1));
            }
            else
            {
                if (!(object1 instanceof Block))
                {
                    throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object1.getClass().getName() + "!");
                }

                arraylist.add(new ItemStack((Block)object1));
            }
        }

        this.recipes.add(new ExtendedShapelessRecipe(crafterId,stack, arraylist));
    }

    public ItemStack findMatchingRecipe(int crafterId,InventoryCrafting craftSlots, World worldIn)
    {
        Iterator iterator = this.recipes.iterator();
        IExtendedRecipe irecipe;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            irecipe = (IExtendedRecipe)iterator.next();
        }
        while (!irecipe.matches(crafterId,craftSlots, worldIn));

        return irecipe.getCraftingResult(craftSlots);
    }

    public ItemStack[] func_180303_b(int crafterId,InventoryCrafting craftSlots, World worldIn)
    {
        Iterator iterator = this.recipes.iterator();

        while (iterator.hasNext())
        {
            IExtendedRecipe irecipe = (IExtendedRecipe)iterator.next();

            if (irecipe.matches(crafterId,craftSlots, worldIn))
            {
                return irecipe.getRemainingItems(craftSlots);
            }
        }

        ItemStack[] aitemstack = new ItemStack[craftSlots.getSizeInventory()];

        for (int i = 0; i < aitemstack.length; ++i)
        {
            aitemstack[i] =craftSlots.getStackInSlot(i);
        }

        return aitemstack;
    }

    public List getRecipeList()
    {
        return this.recipes;
    }
}