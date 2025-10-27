package io.github.desynq.slimesurvival.mixin;

import io.github.desynq.slimesurvival.util.ItemCooldownsExt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemCooldowns;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Mixin(ItemCooldowns.class)
public abstract class ItemCooldownsMixin implements ItemCooldownsExt {

    @Shadow @Final private Map<Item, ?> cooldowns;

    @Override
    public Set<Item> getItems() {
        return Collections.unmodifiableSet(cooldowns.keySet());
    }
}
