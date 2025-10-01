package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.event.BeeStingEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bee.class)
public class BeeMixin {

    @Inject(
            method = "doHurtTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/Bee;setHasStung(Z)V",
                    shift = At.Shift.AFTER
            )
    )
    private void onBeeSting(Entity target, CallbackInfoReturnable<Boolean> cir) {
        Bee bee = (Bee) (Object) this;
        if (target instanceof LivingEntity living) {
            BeeStingEvent event = new BeeStingEvent(bee, living);
            NeoForge.EVENT_BUS.post(event);

            if (event.canStingAgain()) {
                ((BeeAccessor) bee).callSetHasStung(false);
            }
        }
    }
}
