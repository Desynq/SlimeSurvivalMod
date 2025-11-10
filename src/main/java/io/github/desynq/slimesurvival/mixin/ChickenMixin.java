package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.common.event.ChickenLayEggEvent;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Chicken.class)
public final class ChickenMixin {

    @Unique
    private static final ThreadLocal<ChickenLayEggEvent> slimesurvival$layEggEvent = new ThreadLocal<>();

    @Inject(
            method = "aiStep",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/animal/Chicken;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"
            ),
            cancellable = true
    )
    private void slimesurvival$onLayEgg(CallbackInfo ci) {
        Chicken chicken = Chicken.class.cast(this);
        ChickenLayEggEvent event = new ChickenLayEggEvent(chicken);
        NeoForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
            return;
        }

        slimesurvival$layEggEvent.set(event);
    }

    @Redirect(
            method = "aiStep",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/world/item/Items;EGG:Lnet/minecraft/world/item/Item;"
            )
    )
    private Item redirectEggItem() {
        ChickenLayEggEvent event = slimesurvival$layEggEvent.get();
        slimesurvival$layEggEvent.remove();

        // Safety check: fallback to vanilla egg if no event is active
        return event != null ? event.getItemToSpawn() : Items.EGG;
    }
}
