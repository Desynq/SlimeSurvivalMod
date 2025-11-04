package io.github.desynq.slimesurvival.common;

import io.github.desynq.slimesurvival.event.LivingBeforeJumpEvent;
import io.github.desynq.slimesurvival.registry.SlimeSurvivalMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public class CommonEventsSubscriber {

    @SubscribeEvent
    public static void onLivingJump(LivingEvent.LivingJumpEvent event) {
        LivingEntity entity = event.getEntity();

        MobEffectInstance effect = entity.getEffect(SlimeSurvivalMobEffects.WEAK_KNEES);
        if (effect == null) return;

        double effectJumpPower = -Math.min(0.1 * (effect.getAmplifier() + 1), entity.getJumpPower());

        entity.addDeltaMovement(new Vec3(0, effectJumpPower, 0));
    }

    @SubscribeEvent
    public static void onLivingBeforeJump(LivingBeforeJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(SlimeSurvivalMobEffects.ROOTED)) {
            event.setCanceled(true);
        }
    }
}
