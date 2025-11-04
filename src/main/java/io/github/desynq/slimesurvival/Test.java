package io.github.desynq.slimesurvival;

import io.github.desynq.slimesurvival.util.ItemCooldownsExt;
import net.minecraft.client.model.EntityModel;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.client.event.RenderLivingEvent;
import net.neoforged.neoforge.event.VanillaGameEvent;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Path;

public class Test {

    public static void player(ServerPlayer player) {
        player.getCooldowns().isOnCooldown(Items.DIAMOND_SWORD);
        player.disableShield();
        player.hasInfiniteMaterials();
        player.jumpFromGround();
        player.getBlockStateOn();

        ItemCooldowns cooldowns = player.getCooldowns();
        ((ItemCooldownsExt) cooldowns).getItems()
                .forEach(cooldowns::removeCooldown);
    }

    public static void bee(Bee bee) {
    }

    public static void skeleton(Skeleton skeleton) {
    }

    public static void arrow(Arrow arrow) {
    }

    public static void damage(DamageSource source, LivingEntity entity) {
        source.is(DamageTypeTags.ALWAYS_HURTS_ENDER_DRAGONS);
    }

    public static void event(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event) {
    }

    public static void block(BlockState state) {
    }

    public static void stack(ItemStack stack) {
        CompoundTag tag = stack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag();
        stack.getComponents().get(DataComponents.CUSTOM_DATA).copyTag().getString("");
    }
}
