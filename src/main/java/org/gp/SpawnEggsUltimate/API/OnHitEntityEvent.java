/***
 * @author Gamephantom
 * SpawnEggs Ultimate API
 * 
 * Event : OnHitEntity
 * This event get called when a Entity gets hit by an SpawnEgg!
 */
package org.gp.SpawnEggsUltimate.API;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityEvent;

public class OnHitEntityEvent extends EntityEvent {

	public final ItemStack stack;
	public OnHitEntityEvent(Entity entity, ItemStack s) {
		super(entity);
		this.stack = s;
	}

}
