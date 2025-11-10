package io.github.desynq.slimesurvival.common.event;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.neoforged.neoforge.event.entity.EntityEvent;

public class BeeStingEvent extends EntityEvent {
    private final LivingEntity target;
    private boolean canStingAgain = false;

    public BeeStingEvent(Bee bee, LivingEntity target) {
        super(bee);
        this.target = target;
    }

    public LivingEntity getTarget() {
        return this.target;
    }

    public boolean canStingAgain() {
        return canStingAgain;
    }

    public void setCanStingAgain(boolean value) {
        this.canStingAgain = value;
    }
}
