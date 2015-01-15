package none.wjg.multiblockmechanisms.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.world.World;
import none.wjg.multiblockmechanisms.item.crafting.ModCraftingManager;
import none.wjg.multiblockmechanisms.slots.ProhibitiveSlot;
import none.wjg.multiblockmechanisms.tileentities.TETable;

public class ContainerTable extends Container
{
    private TETable tableInventory;
    private World worldObj;
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public IInventory craftResult = new InventoryCraftResult();
    public ContainerTable(InventoryPlayer playerInventory, TETable tableInventoryIn,World world)
    {
    	worldObj = world;
        this.tableInventory = tableInventoryIn;
        int i;
        int j;

        
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 3; ++j)
            {
                this.addSlotToContainer(new Slot(craftMatrix, j + i * 3, 62 + j * 18, 17 + i * 18));
            }
        }
       
        this.addSlotToContainer(new ProhibitiveSlot(tableInventoryIn,new ItemStack[]{new ItemStack(Items.dye,1,15)}, 9, 8, 17));
        this.addSlotToContainer(new ProhibitiveSlot(tableInventoryIn,new ItemStack[]{new ItemStack(Items.paper)}, 10, 26, 17));
        
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
        this.addSlotToContainer(new SlotCrafting(playerInventory.player,craftMatrix,craftResult, 11,26,53));
        this.updateCraftingMatrix();
        this.onCraftMatrixChanged(this.craftMatrix);
    }

    
    private void updateCraftingMatrix() {
    	for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
    	craftMatrix.setInventorySlotContents(i, tableInventory.stacks[i]);
    	}
    	tableInventory.sync();

    }
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
    	
        this.craftResult.setInventorySlotContents(0, ModCraftingManager.getInstance().findMatchingRecipe(ModCraftingManager.getInstance().getCrafterFromName("table"),this.craftMatrix, this.worldObj));
    }

    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);
                
                tableInventory.stacks[i]=itemstack;
            }
        }
        this.tableInventory.closeInventory(playerIn);
    }
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.tableInventory.isUseableByPlayer(playerIn);
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < 9)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, 9, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemstack1);
        }

        return itemstack;
    }
}