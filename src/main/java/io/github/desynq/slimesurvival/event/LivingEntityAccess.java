package io.github.desynq.slimesurvival.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntity.class)
public interface LivingEntityAccess {

    @Invoker("addEatEffect")
    void slimesurvival$invokeAddEatEffect(FoodProperties props);
}
