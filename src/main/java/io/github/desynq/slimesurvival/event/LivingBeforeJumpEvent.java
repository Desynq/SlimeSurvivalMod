package io.github.desynq.slimesurvival.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.EntityEvent;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

public class LivingBeforeJumpEvent extends LivingEvent implements ICancellableEvent {
    public LivingBeforeJumpEvent(LivingEntity entity) {
        super(entity);
    }
}
