package io.github.desynq.slimesurvival.common.registry;

import io.github.desynq.slimesurvival.SlimeSurvival;
import io.github.desynq.slimesurvival.common.effect.AdventureEffect;
import io.github.desynq.slimesurvival.common.effect.PingedEffect;
import io.github.desynq.slimesurvival.common.effect.RootedEffect;
import io.github.desynq.slimesurvival.common.effect.WeakKneesEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SlimeSurvivalMobEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, SlimeSurvival.MOD_ID);

    public static final DeferredHolder<MobEffect, RootedEffect> ROOTED = EFFECTS.register("rooted", RootedEffect::new);

    public static final DeferredHolder<MobEffect, AdventureEffect> ADVENTURE = EFFECTS.register("adventure", AdventureEffect::new);

    public static final DeferredHolder<MobEffect, PingedEffect> PINGED = EFFECTS.register("pinged", PingedEffect::new);

    public static final DeferredHolder<MobEffect, WeakKneesEffect> WEAK_KNEES = EFFECTS.register("weak_knees", WeakKneesEffect::new);
}
