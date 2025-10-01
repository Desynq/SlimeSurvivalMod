package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.event.PlayerEatEffectEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Redirect(
            method = "eat(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/food/FoodProperties;)Lnet/minecraft/world/item/ItemStack;",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;addEatEffect(Lnet/minecraft/world/food/FoodProperties;)V"
            )
    )
    private void slimesurvival$maybeAddEatEffect(LivingEntity self, FoodProperties props, Level level, ItemStack stack) {
        if (self instanceof Player player) {
            PlayerEatEffectEvent event = new PlayerEatEffectEvent(player, stack, props);
            NeoForge.EVENT_BUS.post(event);

            if (!event.isCanceled()) {
                ((LivingEntityAccessor) self).slimesurvival$invokeAddEatEffect(props);
            }
        } else {
            ((LivingEntityAccessor) self).slimesurvival$invokeAddEatEffect(props);
        }
    }
}
