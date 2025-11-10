package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.common.event.SlimeCheckSpawnEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.neoforge.common.NeoForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slime.class)
public class SlimeMixin {

    @Inject(
            method = "checkSlimeSpawnRules",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void slime$overrideSlimeSpawnRules(
            EntityType<Slime> slime,
            LevelAccessor level,
            MobSpawnType spawnType,
            BlockPos pos,
            RandomSource random,
            CallbackInfoReturnable<Boolean> cir
    ) {
        SlimeCheckSpawnEvent event = new SlimeCheckSpawnEvent(slime, level, spawnType, pos, random);
        NeoForge.EVENT_BUS.post(event);

        switch (event.getResult()) {
            case ALLOW -> cir.setReturnValue(true);
            case DENY -> cir.setReturnValue(false);
            case DEFAULT -> {}
        }
    }
}
