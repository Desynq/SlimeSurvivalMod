package io.github.desynq.slimesurvival.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class PlayerEatEffectEvent extends PlayerEvent implements ICancellableEvent {
    private final ItemStack stack;
    private final FoodProperties foodProperties;

    public PlayerEatEffectEvent(Player player, ItemStack stack, FoodProperties foodProperties) {
        super(player);
        this.stack = stack;
        this.foodProperties = foodProperties;
    }

    public ItemStack getStack() {
        return this.stack;
    }

    public FoodProperties getFoodProperties() {
        return this.foodProperties;
    }
}
