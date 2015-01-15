package none.wjg.multiblockmechanisms;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties{
	public static final  String EXT_PROP_NAME = "MbmExtendedPlayer";
	private final EntityPlayer player;
	private int mbmUnderstanding, mbmUnderstandingPts;
	
	public ExtendedPlayer(EntityPlayer player)
	{
		this.player = player;
		this.mbmUnderstanding=this.mbmUnderstandingPts = 0;
	}
	
	public static final void register(EntityPlayer player)
	{
		player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
	}
	
	public static final ExtendedPlayer get(EntityPlayer player)
	{
		return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
				NBTTagCompound properties = new NBTTagCompound();
				
				// We only have 2 variables currently; save them both to the new tag
				properties.setInteger("mbmUnderstanding", this.mbmUnderstanding);
				properties.setInteger("mbmUnderstandingPts", this.mbmUnderstandingPts);
				
				compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		// Here we fetch the unique tag compound we set for this class of Extended Properties
		NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
		// Get our data from the custom tag compound
		this.mbmUnderstanding = properties.getInteger("mbmUnderstanding");
		this.mbmUnderstandingPts = properties.getInteger("mbmUnderstandingPts");
		// Just so you know it's working, add this line:
		System.out.println("[TUT PROPS] Stats from NBT: " + this.mbmUnderstanding + "-" + this.mbmUnderstandingPts);
	}

	@Override
	public void init(Entity entity, World world) {
		// TODO Auto-generated method stub
		
	}
	
	public int getUnderstanding()
	{
		return this.mbmUnderstanding;
	}
	
	public void updateStats(int pts){
		this.mbmUnderstandingPts+=pts;
		if(this.mbmUnderstanding*2+1<=this.mbmUnderstandingPts){
			this.mbmUnderstandingPts-=this.mbmUnderstanding*2+1;
			this.mbmUnderstanding+=1;
		}
	}

}
