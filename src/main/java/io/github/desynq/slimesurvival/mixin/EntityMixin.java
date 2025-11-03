package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.event.IsInvisibleEvent;
import net.minecraft.world.entity.Entity;
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
}
