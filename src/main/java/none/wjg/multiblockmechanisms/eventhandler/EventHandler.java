package none.wjg.multiblockmechanisms.eventhandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import none.wjg.multiblockmechanisms.ExtendedPlayer;

public class EventHandler {
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null){
			ExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}
}
