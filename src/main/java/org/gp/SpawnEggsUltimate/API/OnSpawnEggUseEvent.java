/***
 * @author Gamephantom
 * SpawnEggs Ultimate API
 * 
 * Event : OnSpawnEggUse
 * This event get called when a Player uses an Spawn Egg on a certain Position!
 */
package org.gp.SpawnEggsUltimate.API;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.Event.HasResult;

@cpw.mods.fml.common.eventhandler.Cancelable
@HasResult
public class OnSpawnEggUseEvent extends PlayerEvent {

	public final World world;
	public final int x;
	public final int y;
	public final int z;
	
	public OnSpawnEggUseEvent(EntityPlayer player, World w, int posX, int posY, int posZ) {
		super(player);
		this.world = w;
		this.x = posX;
		this.y = posY;
		this.z = posZ;
	}
}
