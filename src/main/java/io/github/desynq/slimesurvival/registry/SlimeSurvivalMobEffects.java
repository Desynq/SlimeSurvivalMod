package io.github.desynq.slimesurvival.registry;

import io.github.desynq.slimesurvival.SlimeSurvival;
import io.github.desynq.slimesurvival.common.effect.AdventureEffect;
import io.github.desynq.slimesurvival.common.effect.RootedEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

public class SlimeSurvivalMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, SlimeSurvival.MOD_ID);

    public static final DeferredHolder<MobEffect, RootedEffect> ROOTED = EFFECTS.register("rooted", RootedEffect::new);

    public static final DeferredHolder<MobEffect, AdventureEffect> ADVENTURE = EFFECTS.register("adventure", AdventureEffect::new);
}
