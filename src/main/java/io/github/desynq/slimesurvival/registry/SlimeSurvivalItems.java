package io.github.desynq.slimesurvival.registry;

import io.github.desynq.slimesurvival.SlimeSurvival;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SlimeSurvivalItems {
    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(SlimeSurvival.MOD_ID);

    public static final List<Supplier<Item>> ITEMS_IN_ITEMS_TAB = new ArrayList<>();

    public static final Supplier<Item> UNBREAKING_TOME = createItem(
            "unbreaking_tome",
            createRarityProp(Rarity.EPIC)
    );

    public static final Supplier<Item> LESSER_UNBREAKING_TOME = createItem(
            "lesser_unbreaking_tome",
            createRarityProp(Rarity.EPIC)
    );

    public static final Supplier<Item> CRASH_HELMET = createItem(
            "crash_helmet",
            createRarityProp(Rarity.RARE)
    );

    public static final Supplier<Item> BAND_OF_REGENERATION = createItem(
            "band_of_regeneration",
            createRarityProp(Rarity.EPIC)
    );

    public static final Supplier<Item> ROYAL_JELLY = createItem(
            "royal_jelly",
            createRarityProp(Rarity.UNCOMMON)
    );

    public static final Supplier<Item> ROYAL_WAX = createItem(
            "royal_wax",
            createRarityProp(Rarity.UNCOMMON)
    );

    public static final Supplier<Item> MYSTITE_ORE = createItem(
            "mystite_ore",
            createRarityProp(Rarity.EPIC)
    );

    public static final Supplier<Item> MYSTITE_INGOT = createItem(
            "mystite_ingot",
            createRarityProp(Rarity.EPIC)
    );

    public static final Supplier<Item> MEDICAL_KIT = createItem(
            "medical_kit",
            new Item.Properties().stacksTo(4)
    );

    public static final Supplier<Item> KEYCARD_0 = createItem("keycard_0");
    public static final Supplier<Item> KEYCARD_1 = createItem("keycard_1");
    public static final Supplier<Item> KEYCARD_2 = createItem("keycard_2");
    public static final Supplier<Item> KEYCARD_3 = createItem("keycard_3");
    public static final Supplier<Item> KEYCARD_4 = createItem("keycard_4");
    public static final Supplier<Item> KEYCARD_5 = createItem("keycard_5");
    public static final Supplier<Item> KEYCARD_6 = createItem("keycard_6");

    public static final Supplier<Item> MORPHINE_SYRINGE = createItem(
            "morphine_syringe",
            new Item.Properties().stacksTo(4)
    );

    public static final Supplier<Item> SLIMY_EYE = createItem(
            "slimy_eye",
            new Item.Properties().rarity(Rarity.RARE)
    );



    private static Supplier<Item> createItem(String name) {
        return createItem(name, new Item.Properties());
    }

    private static Supplier<Item> createItem(String name, Item.Properties props) {
        Supplier<Item> item = ITEMS.registerItem(name, Item::new, props);
        ITEMS_IN_ITEMS_TAB.add(item);
        return item;
    }

    private static Item.Properties createRarityProp(Rarity rarity) {
        return new Item.Properties().rarity(rarity);
    }
}
