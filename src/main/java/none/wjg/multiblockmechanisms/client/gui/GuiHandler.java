package none.wjg.multiblockmechanisms.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import none.wjg.multiblockmechanisms.blocks.ContainerTable;
import none.wjg.multiblockmechanisms.tileentities.TETable;

public class GuiHandler implements IGuiHandler
{

        @Override
        public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
        { 
                TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));

                if(tileEntity != null)
                {
                        switch(ID)
                        {
                        case 0: return new ContainerTable(player.inventory, (TETable)tileEntity,world);
                       
                        }
                }
                return null;
        }

        @Override
        public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
        {
                TileEntity tileEntity = world.getTileEntity(new BlockPos(x,y,z));

                if (tileEntity != null)
                {
                        switch(ID)
                        {
                        case 0: return new GuiTable(player.inventory, (TETable)tileEntity);
           
                        }
                }
                return null;
        }
}