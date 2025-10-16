package io.github.desynq.slimesurvival.event;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.EntityEvent;

public class DamageAfterArmorEvent extends EntityEvent {
    private final DamageSource source;
    private final float originalDamage;
    private final float afterArmorDamage;
    private float finalDamage;

    public DamageAfterArmorEvent(LivingEntity entity, DamageSource source, float originalDamage, float afterArmorDamage) {
        super(entity);
        this.source = source;
        this.originalDamage = originalDamage;
        this.afterArmorDamage = afterArmorDamage;
        this.finalDamage = afterArmorDamage;
    }

    public DamageSource getSource() {
        return source;
    }

    public float getOriginalDamage() {
        return originalDamage;
    }

    public float getAfterArmorDamage() {
        return afterArmorDamage;
    }


    public void setFinalDamage(float amount) {
        this.finalDamage = amount;
    }

    public float getFinalDamage() {
        return finalDamage;
    }
}
