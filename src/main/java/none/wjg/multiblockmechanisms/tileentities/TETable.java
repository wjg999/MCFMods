package none.wjg.multiblockmechanisms.tileentities;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3i;
import none.wjg.multiblockmechanisms.blocks.ContainerTable;
import none.wjg.multiblockmechanisms.init.ModItems;
import none.wjg.multiblockmechanisms.utility.LogHelper;

public class TETable extends TileEntityLockable implements IInventory
{
    public ItemStack[] stacks = new ItemStack[12];
    private BlockPos otherPos;
    private TETable otherEntity;
    protected String customName;

    
    
    public int getSizeInventory()
    {
        return 12;
    }
    
    public ItemStack getStackInSlot(int index)
    {
        return this.stacks[index];
    }
    public void connect(){
    	
    		if(otherEntity==null){ 
    		TETable ent=null;
        	Vec3i baseVec = new Vec3i(pos.getX(),pos.getY(),pos.getZ());
    		Vec3i posHold=new Vec3i(0,0,0);
    		if((worldObj.getTileEntity(new BlockPos(pos.getX()+1,pos.getY(),pos.getZ())))instanceof TETable){
    			
    			ent = (TETable)worldObj.getTileEntity(new BlockPos(pos.getX()+1,pos.getY(),pos.getZ()));
    			if (ent.otherEntity!=null)
    			ent = null;
    		}else if((worldObj.getTileEntity(new BlockPos(pos.getX()-1,pos.getY(),pos.getZ())))instanceof TETable){
    			ent = (TETable)worldObj.getTileEntity(new BlockPos(pos.getX()-1,pos.getY(),pos.getZ()));
    			if (ent.otherEntity!=null)
    			ent = null;
    		}else if((worldObj.getTileEntity(new BlockPos(pos.getX(),pos.getY(),pos.getZ()+1)))instanceof TETable){
    			ent = (TETable)worldObj.getTileEntity(new BlockPos(pos.getX(),pos.getY(),pos.getZ()+1));
    			if (ent.otherEntity!=null)
    			ent = null;
    		}else if((worldObj.getTileEntity(new BlockPos(pos.getX(),pos.getY(),pos.getZ()-1)))instanceof TETable){
    			ent = (TETable)worldObj.getTileEntity(new BlockPos(pos.getX(),pos.getY(),pos.getZ()-1));
    			if (ent.otherEntity!=null)
    			ent = null;
    			
    		}
    		if(ent.otherEntity==null){
    			ent.otherEntity=this;
    		}
        	otherEntity= ent;
        	
        	}
    }
    
    public void sync(){
    	if(otherPos!=null&&otherEntity==null){
    		otherEntity=(TETable) worldObj.getTileEntity(otherPos);
    	}
    	if(otherEntity!=null){
    		if(otherEntity.otherEntity==null){
    			otherEntity.otherEntity=this;
    		}
    	otherEntity.stacks=this.stacks;
    	}else{
    		String side=(worldObj.isRemote?"client":"server");
    		if(side=="server"){
    			LogHelper.error("TETable was null, this was on serverside, did you place a single table through creative?");
    		}
    		
    		
    	}
    }
    
    public ItemStack decrStackSize(int index, int count)
    {
    	
        if (this.stacks[index] != null)
        {
            ItemStack itemstack;

            if (this.stacks[index].stackSize <= count)
            {
                itemstack = this.stacks[index];
                this.stacks[index] = null;
                this.markDirty();
                sync();
                return itemstack;
            }
            else
            {
                itemstack = this.stacks[index].splitStack(count);

                if (this.stacks[index].stackSize == 0)
                {
                    this.stacks[index] = null;
                }

                this.markDirty();
                sync();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.stacks[index] != null)
        {
            ItemStack itemstack = this.stacks[index];
            this.stacks[index] = null;
            sync();
            return itemstack;
        }
        else
        {
        	sync();
            return null;
        }
    }

    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.stacks[index] = stack;
        sync();
        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    public int addItemStack(ItemStack stack)
    {
        for (int i = 0; i < this.stacks.length; ++i)
        {
            if (this.stacks[i] == null || this.stacks[i].getItem() == null)
            {
                this.setInventorySlotContents(i, stack);
                return i;
            }
        }
        sync();
        return -1;
    }

    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.table";
    }

    public void setCustomName(String customName)
    {
        this.customName = customName;
    }

    public boolean hasCustomName()
    {
        return this.customName != null;
    }
    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        NBTTagList nbttaglist = compound.getTagList("Items", 10);
        this.stacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 255;

            if (j >= 0 && j < this.stacks.length)
            {
                this.stacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
            
        }
        if(compound.hasKey("Positions",10))
        {
        	NBTTagCompound posList= compound.getCompoundTag("Positions");
        	int x=posList.getInteger("posX");
        	int y=posList.getInteger("posY");
        	int z=posList.getInteger("posZ");
        	LogHelper.info("recieved these values:"+x+" "+y+" "+z);
        	otherPos =new BlockPos(x,y,z);
        }

        if (compound.hasKey("CustomName", 8))
        {
            this.customName = compound.getString("CustomName");
        }
    }
    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.stacks.length; ++i)
        {
            if (this.stacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.stacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        

        compound.setTag("Items", nbttaglist);
        
        if(this.otherEntity!=null){
        	NBTTagCompound posList = new NBTTagCompound();
        	posList.setInteger("posX", otherEntity.pos.getX());
        	posList.setInteger("posY", otherEntity.pos.getY());
        	posList.setInteger("posZ", otherEntity.pos.getZ());
        	compound.setTag("Positions", posList);
        	LogHelper.info("sent these values over:"+otherEntity.pos.getX()+" "+otherEntity.pos.getY()+" "+otherEntity.pos.getZ());
        }
        
        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.customName);
        }
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player) {}

    public void closeInventory(EntityPlayer player) {}

    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
    	
    	if(index<9){
        return true;
    	}else{
    		switch(index){
        	case 9: return stack.getItem() == Items.paper;
        	case 10: return (stack.getItem() == Items.dye)&&(stack.getMetadata()==EnumDyeColor.BLACK.getDyeDamage());
        	default: return false;
        	}
    	}
    }

    public String getGuiID()
    {
        return "mbm:table";
    }

    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerTable(playerInventory, this, this.worldObj);
    }

    public int getField(int id)
    {
        return 0;
    }

    public void setField(int id, int value) {}

    public int getFieldCount()
    {
        return 0;
    }

    public void clear()
    {
        for (int i = 0; i < this.stacks.length; ++i)
        {
            this.stacks[i] = null;
        }
        sync();
        if(otherEntity!=null){
        	otherEntity.selfDestruct();
        }
    }
    
    public void selfDestruct(){

    	worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(ModItems.nail,2)));
    	worldObj.spawnEntityInWorld(new EntityItem(worldObj, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Blocks.planks,1,0)));
    	
    	worldObj.destroyBlock(pos, false);
    	worldObj.removeTileEntity(pos);
    }
}