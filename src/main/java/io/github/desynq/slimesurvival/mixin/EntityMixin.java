package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.event.IsGlowingEvent;
import io.github.desynq.slimesurvival.event.IsInvisibleEvent;
import io.github.desynq.slimesurvival.event.IsInvisibleToEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {

    @Inject(
            method = "isInvisible",
            at = @At("RETURN"),
            cancellable = true
    )
    public void slime$isInvisible(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = Entity.class.cast(this);
        IsInvisibleEvent event = new IsInvisibleEvent(entity, cir.getReturnValue());
        NeoForge.EVENT_BUS.post(event);
        cir.setReturnValue(event.isInvisible());
    }

    @Inject(
            method = "isInvisibleTo",
            at = @At("RETURN"),
            cancellable = true
    )
    public void slime$isInvisibleTo(Player player, CallbackInfoReturnable<Boolean> cir) {
        Entity entity = Entity.class.cast(this);
        IsInvisibleToEvent event = new IsInvisibleToEvent(entity, player, cir.getReturnValue());
        NeoForge.EVENT_BUS.post(event);
        cir.setReturnValue(event.isInvisible());
    }

    @Inject(
            method = "isCurrentlyGlowing",
            at = @At("RETURN"),
            cancellable = true
    )
    public void slime$isCurrentlyGlowing(CallbackInfoReturnable<Boolean> cir) {
        Entity entity = Entity.class.cast(this);
        if (!entity.level().isClientSide()) return;

        IsGlowingEvent event = new IsGlowingEvent(entity, cir.getReturnValue());
        NeoForge.EVENT_BUS.post(event);
        cir.setReturnValue(event.isGlowing());
    }
}
