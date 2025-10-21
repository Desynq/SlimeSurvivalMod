package io.github.desynq.slimesurvival;

import io.github.desynq.slimesurvival.client.ClientEventsSubscriber;
import io.github.desynq.slimesurvival.dev.DevOnlyEvents;
import io.github.desynq.slimesurvival.registry.SlimeSurvivalMobEffects;
import net.neoforged.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(SlimeSurvival.MOD_ID)
public class SlimeSurvival {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "slimesurvival";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public SlimeSurvival(IEventBus modEventBus, ModContainer modContainer) {
        if (!FMLEnvironment.production) {
            NeoForge.EVENT_BUS.register(DevOnlyEvents.class);
        }
        if (FMLEnvironment.dist.isClient()) {
            NeoForge.EVENT_BUS.register(ClientEventsSubscriber.class);
        }

        SlimeSurvivalMobEffects.EFFECTS.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
