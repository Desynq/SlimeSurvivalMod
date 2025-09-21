package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.event.NaturalRegenerationCheckEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyVariable(
            method = "tick", // target method
            at = @At(value = "STORE"), // when the variable is stored
            ordinal = 0 // index of 'flag' in locals (it's the first boolean)
    )
    private boolean modifyNaturalRegenFlag(boolean original, Player player) {
        NaturalRegenerationCheckEvent event = new NaturalRegenerationCheckEvent(player, original);
        NeoForge.EVENT_BUS.post(event);
        return original && !event.isCanceled();
    }
}
