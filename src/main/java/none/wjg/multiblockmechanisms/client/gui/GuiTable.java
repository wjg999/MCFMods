package none.wjg.multiblockmechanisms.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import none.wjg.multiblockmechanisms.blocks.ContainerTable;
import none.wjg.multiblockmechanisms.tileentities.TETable;

@SideOnly(Side.CLIENT)
public class GuiTable extends GuiContainer
{
    private static final ResourceLocation tableGuiTextures = new ResourceLocation("mbm:textures/gui/container/table.png");
    private final InventoryPlayer playerInventory;
    public IInventory tableInventory;

    public GuiTable(InventoryPlayer playerInv, TETable tableInv)
    {
        super(new ContainerTable(playerInv, tableInv,tableInv.getWorld()));
        this.playerInventory = playerInv;
        this.tableInventory = tableInv;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tableInventory.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(tableGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }
}