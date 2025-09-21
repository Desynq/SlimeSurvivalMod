package io.github.desynq.slimesurvival.dev;

import io.github.desynq.slimesurvival.SlimeSurvival;
import io.github.desynq.slimesurvival.event.NaturalRegenerationCheckEvent;
import io.github.desynq.slimesurvival.event.PlayerEatEffectEvent;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.loading.FMLEnvironment;

public class DevOnlyEvents {

    @SubscribeEvent
    public static void onNaturalRegen(NaturalRegenerationCheckEvent event) {
        if (event.getEntity().getTags().stream().anyMatch(tag -> tag.endsWith("no_natural_regeneration"))) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void noHungerFromRottenFlesh(PlayerEatEffectEvent event) {
        if (event.getStack().is(Items.ROTTEN_FLESH)) {
            event.setCanceled(true);
        }
    }
}
