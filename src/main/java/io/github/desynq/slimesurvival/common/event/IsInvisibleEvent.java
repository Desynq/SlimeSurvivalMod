package io.github.desynq.slimesurvival.common.event;

import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.event.entity.EntityEvent;

public class IsInvisibleEvent extends EntityEvent {

    private final boolean originalInvisibility;
    private boolean finalInvisibility;

    public IsInvisibleEvent(Entity entity, boolean invisible) {
        super(entity);
        this.originalInvisibility = invisible;
        this.finalInvisibility = invisible;
    }

    public boolean isOriginallyInvisible() {
        return originalInvisibility;
    }

    public boolean isInvisible() {
        return finalInvisibility;
    }

    public void setInvisibility(boolean value) {
        finalInvisibility = value;
    }
}
