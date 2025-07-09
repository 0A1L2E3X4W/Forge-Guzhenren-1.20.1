package com.alex.guzhenren.item.custom.custom_material;

import com.alex.guzhenren.item.custom.CustomItem;
import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModRank;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuMaterial extends CustomItem {

    public GuMaterial(Properties properties, ModRank rank, ModPath path) {
        super(properties);
        this.rank = rank; this.path = path;
    }

    protected ModRank rank;
    protected ModPath path;

    // GETTERS
    public ModRank getRank() { return rank; }
    public ModPath getPath() { return path; }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);

        String rankKey = rank.getNameKey();
        String pathKey = path.getNameKey();

        Component text = Component.translatable(rankKey).append(" ").append(Component.translatable(pathKey))
                .withStyle(style -> style.withColor(0xAA00AA));
        tooltip.add(text);
    }
}
