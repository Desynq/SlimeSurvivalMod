package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.event.DamageAfterArmorEvent;
import io.github.desynq.slimesurvival.event.LivingBeforeJumpEvent;
import io.github.desynq.slimesurvival.event.PlayerEatEffectEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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
        }
        else {
            ((LivingEntityAccessor) self).slimesurvival$invokeAddEatEffect(props);
        }
    }

    @Unique
    private static final ThreadLocal<Float> slimesurvival$originalDamage = new ThreadLocal<>();

    @Inject(
            method = "getDamageAfterArmorAbsorb",
            at = @At("HEAD")
    )
    private void slimesurvival$storeDamageBeforeArmorAbsorb(DamageSource damageSource, float damageAmount, CallbackInfoReturnable<Float> cir) {
        slimesurvival$originalDamage.set(damageAmount);
    }

    @Inject(
            method = "getDamageAfterArmorAbsorb",
            at = @At("RETURN"),
            cancellable = true
    )
    private void slimesurvival$modifyDamageAfterArmorAbsorb(DamageSource damageSource, float damageAmount, CallbackInfoReturnable<Float> cir) {
        Float orig = slimesurvival$originalDamage.get();
        slimesurvival$originalDamage.remove();

        Float afterArmor = cir.getReturnValue();

        float original = (orig != null) ? orig : damageAmount;
        float after = (afterArmor != null) ? afterArmor : damageAmount;

        LivingEntity entity = (LivingEntity) (Object) this;

        DamageAfterArmorEvent event = new DamageAfterArmorEvent(entity, damageSource, original, after);

        NeoForge.EVENT_BUS.post(event);

        cir.setReturnValue((event.getFinalDamage()));
    }

    @Inject(
            method = "jumpFromGround",
            at = @At("HEAD"),
            cancellable = true
    )
    private void slimesurvival$jumpFromGround(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        LivingBeforeJumpEvent event = new LivingBeforeJumpEvent(entity);
        NeoForge.EVENT_BUS.post(event);

        if (event.isCanceled()) {
            ci.cancel();
        }
    }
}
