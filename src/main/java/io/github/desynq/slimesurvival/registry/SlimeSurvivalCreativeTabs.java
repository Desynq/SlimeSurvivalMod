package io.github.desynq.slimesurvival.registry;

import io.github.desynq.slimesurvival.SlimeSurvival;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class SlimeSurvivalCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SlimeSurvival.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ITEMS_TAB = CREATIVE_MODE_TABS.register("items_tab", () -> CreativeModeTab.builder()
            .title(SlimeSurvival.translatable("itemGroup."))
            .icon(() -> SlimeSurvivalItems.SLIMY_EYE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                SlimeSurvivalItems.ITEMS_IN_ITEMS_TAB.forEach(itemSupplier -> {
                    output.accept(itemSupplier.get());
                });
            }).build());
}
