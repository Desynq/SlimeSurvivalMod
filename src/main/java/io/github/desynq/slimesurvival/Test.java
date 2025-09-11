package io.github.desynq.slimesurvival;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.commands.EffectCommands;
import net.minecraft.server.commands.GameRuleCommand;
import net.minecraft.server.commands.MsgCommand;
import net.minecraft.server.commands.RideCommand;
import net.minecraft.server.commands.SayCommand;
import net.minecraft.server.commands.TeleportCommand;
import net.minecraft.server.commands.TimeCommand;
import net.minecraft.server.commands.WeatherCommand;
import net.minecraft.server.commands.data.DataCommands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.component.Unbreakable;
import net.minecraft.world.level.GameRules;

import java.util.Optional;

public class Test {
}
