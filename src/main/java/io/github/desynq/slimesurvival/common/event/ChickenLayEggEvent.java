package io.github.desynq.slimesurvival.common.event;

import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public class ChickenLayEggEvent extends LivingEvent implements ICancellableEvent {

    private Item itemToSpawn = Items.EGG;

    public ChickenLayEggEvent(Chicken entity) {
        super(entity);
    }

    public Item getItemToSpawn() {
        return itemToSpawn;
    }

    public void setItemToSpawn(ItemLike item) {
        itemToSpawn = item.asItem();
    }
}
