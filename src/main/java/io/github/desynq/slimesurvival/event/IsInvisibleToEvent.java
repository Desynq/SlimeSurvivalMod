package io.github.desynq.slimesurvival.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.EntityEvent;

public class IsInvisibleToEvent extends EntityEvent {

    private final Player player;
    private final boolean originalInvisibility;
    private boolean finalInvisibility;

    public IsInvisibleToEvent(Entity entity, Player player, boolean invisible) {
        super(entity);
        this.player = player;
        this.originalInvisibility = invisible;
        this.finalInvisibility = invisible;
    }

    public Player getPlayer() {
        return player;
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
