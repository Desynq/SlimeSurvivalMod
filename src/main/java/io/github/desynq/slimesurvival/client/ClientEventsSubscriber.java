package io.github.desynq.slimesurvival.client;

import io.github.desynq.slimesurvival.event.LivingBeforeJumpEvent;
import io.github.desynq.slimesurvival.registry.SlimeSurvivalMobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.SubscribeEvent;

public class ClientEventsSubscriber {

    @SubscribeEvent
    public static void onLivingBeforeJump(LivingBeforeJumpEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(SlimeSurvivalMobEffects.ROOTED)) {
            event.setCanceled(true);
        }
    }
}
