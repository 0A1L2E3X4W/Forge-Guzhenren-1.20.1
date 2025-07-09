package com.alex.guzhenren.item.custom.custom_gu;

import com.alex.guzhenren.item.custom.CustomItem;
import com.alex.guzhenren.utils.capability.PlayerAptitudesUtils;
import com.alex.guzhenren.utils.capability.PlayerEssenceUtils;
import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModRank;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MortalGu extends CustomItem {

    public MortalGu(Properties properties, ModRank rank, ModPath path, int refinementCost) {
        super(properties);
        this.rank = rank; this.path = path;
        this.refinementCost = refinementCost;
    }

    public static final String KEY_REFINE = "key.guzhenren.item.refine";
    public static final String KEY_CAN_USE = "key.guzhenren.item.can_use";

    protected ModRank rank;
    protected ModPath path;

    protected int refinementCost;
    protected float refineValue = refinementCost / 12.0F;

    // REFINEMENT
    protected int refinement(ItemStack itemStack, Player player, float progress, int totalCost) {

        CompoundTag nbt = itemStack.getOrCreateTag();
        if (!nbt.contains(KEY_REFINE)) nbt.putFloat(KEY_REFINE, 0.0F);

        float currentProgress = nbt.getFloat(KEY_REFINE);
        float currentEssence = PlayerEssenceUtils.getCurrentEssence(player) * PlayerAptitudesUtils.getEssenceModifier(player);
        float essenceRequired = progress / PlayerAptitudesUtils.getEssenceModifier(player);

        if (currentProgress >= totalCost) return 1; // Max refinement reached
        if (currentEssence < progress) return -1; // Insufficient essence

        PlayerEssenceUtils.subCurrentEssence(player, essenceRequired);
        nbt.putFloat(KEY_REFINE, currentProgress + progress);
        itemStack.setTag(nbt);

        return 0; // success, not enough refinement progress
    }

    // FEED
    protected boolean feedGu(Level level, Player player, InteractionHand usedHand, int feedAmount, Item food) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        ItemStack offhandItem = player.getOffhandItem();

        if (offhandItem.isEmpty() || !itemStack.isDamageableItem()) return false;
        if (offhandItem.getItem() != food) return false;

        int currentDamage = itemStack.getDamageValue();
        if (currentDamage > 0) {
            int damage = Math.max(currentDamage - feedAmount, 0);
            itemStack.setDamageValue(damage);
            offhandItem.shrink(1);
            return true;
        }

        return false;
    }

    // CAN USE for STRENGTH PATH
    protected void changeCanUse(ItemStack itemStack) {
        CompoundTag nbt = itemStack.getOrCreateTag();
        boolean canUse = nbt.getBoolean(KEY_CAN_USE);
        nbt.putBoolean(KEY_CAN_USE, !canUse);
        itemStack.setTag(nbt);
    }

    protected boolean getCanUse(ItemStack itemStack) {
        CompoundTag nbt = itemStack.getOrCreateTag();
        return nbt.getBoolean(KEY_CAN_USE);
    }

    // TOOLTIPS
    protected static void refinementTooltips(@NotNull ItemStack itemStack, @NotNull List<Component> tooltip, int refinementCost) {

        float refinement = itemStack.getOrCreateTag().getFloat(KEY_REFINE);
        if (refinement < refinementCost) {
            tooltip.add(Component.translatable("guzhenren.text.refinement_progress")
                    .append(": " + itemStack.getOrCreateTag().getFloat(KEY_REFINE) + "/" + refinementCost));
        } else {
            tooltip.add(Component.translatable("guzhenren.text.refinement_done"));
        }
    }

    // GETTERS
    public ModRank getRank() { return rank; }
    public ModPath getPath() { return path; }
    public int getRefinementCost() { return refinementCost; }
    public float getRefineValue() { return refineValue; }

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
