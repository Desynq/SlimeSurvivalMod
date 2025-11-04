package io.github.desynq.slimesurvival.event;

import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.event.entity.EntityEvent;

/**
 * Runs only on the clientside
 */
public class IsGlowingEvent extends EntityEvent {

    private final boolean originalGlowing;
    private boolean finalGlowing;

    public IsGlowingEvent(Entity entity, boolean glowing) {
        super(entity);
        this.originalGlowing = glowing;
        this.finalGlowing = glowing;
    }

    public boolean isOriginallyGlowing() {
        return originalGlowing;
    }

    public boolean isGlowing() {
        return finalGlowing;
    }

    public void setGlowing(boolean value) {
        finalGlowing = value;
    }
}
