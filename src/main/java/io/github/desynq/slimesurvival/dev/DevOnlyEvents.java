package io.github.desynq.slimesurvival.dev;

import io.github.desynq.slimesurvival.common.event.BeeStingEvent;
import io.github.desynq.slimesurvival.common.event.ChickenLayEggEvent;
import io.github.desynq.slimesurvival.common.event.DamageAfterArmorEvent;
import io.github.desynq.slimesurvival.common.event.IsGlowingEvent;
import io.github.desynq.slimesurvival.common.event.IsInvisibleEvent;
import io.github.desynq.slimesurvival.common.event.IsInvisibleToEvent;
import io.github.desynq.slimesurvival.common.event.NaturalRegenerationCheckEvent;
import io.github.desynq.slimesurvival.common.event.PlayerEatEffectEvent;
import io.github.desynq.slimesurvival.common.event.SlimeCheckSpawnEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;

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

    @SubscribeEvent
    public static void infiniteBeeSting(BeeStingEvent event) {
        event.setCanStingAgain(true);
    }

    @SubscribeEvent
    public static void flatArmorToughnessAndRationalArmor(DamageAfterArmorEvent event) {
        float damage = event.getOriginalDamage();
        LivingEntity entity = (LivingEntity) event.getEntity();
        double armor = entity.getAttributeValue(Attributes.ARMOR);
        double toughness = entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS);
        double factor = 20.0;
        double newDamage = Math.max(1, damage - toughness) * (factor / (factor + armor));
        event.setFinalDamage((float) newDamage);
    }

    @SubscribeEvent
    public static void invisibleCreepers(IsInvisibleEvent event) {
        Entity entity = event.getEntity();
        if (!entity.level().isClientSide()) return;
        if (entity instanceof Creeper) {
            event.setInvisibility(true);
        }
    }

    @SubscribeEvent
    public static void semiInvisiblePhantoms(IsInvisibleToEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Phantom) {
            event.setInvisibility(false);
        }
    }

    @SubscribeEvent
    public static void glowingCreepers(IsGlowingEvent event) {
        Entity entity = event.getEntity();
        if (!entity.level().isClientSide()) return;

        if (entity instanceof Creeper) {
            event.setGlowing(true);
        }
    }

    @SubscribeEvent
    public static void goldLayingChickens(ChickenLayEggEvent event) {
        if (Math.random() < 0.5) {
            event.setItemToSpawn(Items.RAW_GOLD);
        }
    }

    @SubscribeEvent
    public static void slimesEverywhere(SlimeCheckSpawnEvent event) {
        if (event.getRandom().nextFloat() < 0.1 && event.checkMobSpawnRules()) {
            event.setResult(SlimeCheckSpawnEvent.Result.ALLOW);
        }
        else {
            event.setResult(SlimeCheckSpawnEvent.Result.DENY);
        }
    }
}
