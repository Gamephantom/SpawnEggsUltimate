/***
 * @author Gamephantom
 * SpawnEggs Ultimate API
 * 
 * Event : OnSpawnEntity
 * This event get called when somebody/something uses an Spawn Egg to spawn an Entity!
 */
package org.gp.SpawnEggsUltimate.API;
import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;
import cpw.mods.fml.common.eventhandler.Event.HasResult;

@cpw.mods.fml.common.eventhandler.Cancelable
@HasResult
public class OnSpawnEntityEvent extends EntityEvent {

	public final Entity entity;
	public final int posX;
	public final int posY;
	public final int posZ;
	
	public OnSpawnEntityEvent(Entity e, int x, int y, int z) {
		super(e);
		this.entity = e;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
	}

}
