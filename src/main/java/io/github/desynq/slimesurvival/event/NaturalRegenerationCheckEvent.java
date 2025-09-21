package io.github.desynq.slimesurvival.event;

import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class NaturalRegenerationCheckEvent extends PlayerEvent implements ICancellableEvent {
    private final boolean allow;

    public NaturalRegenerationCheckEvent(Player player, boolean initial) {
        super(player);
        this.allow = initial;
    }

    public boolean isAllowed() {
        return this.allow;
    }
}
