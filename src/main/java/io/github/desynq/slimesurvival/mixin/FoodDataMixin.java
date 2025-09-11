package io.github.desynq.slimesurvival.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.level.GameRules;
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
        // Add your extra condition
        return original && !player.getTags().contains("no_natural_regeneration");
    }
}
