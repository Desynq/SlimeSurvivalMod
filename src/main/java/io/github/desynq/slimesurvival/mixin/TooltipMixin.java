package io.github.desynq.slimesurvival.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.List;

@Mixin(Tooltip.class)
public abstract class TooltipMixin {

    @ModifyConstant(
            method = "splitTooltip",
            constant = @Constant(intValue = 170)
    )
    private static int modifyTooltipWidth(int original) {
        Minecraft mc = Minecraft.getInstance();
        double guiScale = mc.getWindow().getGuiScale();

        int width = (int) (320 * (3.0 / guiScale));
        width = Mth.clamp(width, 240, 640);
        return width;
    }
}
