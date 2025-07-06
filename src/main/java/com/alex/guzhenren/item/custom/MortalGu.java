package com.alex.guzhenren.item.custom;

import com.alex.guzhenren.capability.providers.PlayerEssenceProvider;
import com.alex.guzhenren.networking.packet.EssenceSyncS2CPacket;
import com.alex.guzhenren.utils.enums.ModRank;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.logging.Level;

public class MortalGu extends ModCustomItem {

    public MortalGu(Properties properties, ModRank rank, int refinementCost) {
        super(properties, rank);
        this.refinementCost = refinementCost;
    }

    public static final String KEY_REFINE = "key.guzhenren.item.refine";
    public static final String KEY_CAN_USE = "key.guzhenren.item.can_use";

    protected ModRank itemRank;
    protected int refinementCost;
    protected float refineValue = refinementCost / 12.0F;

    // REFINEMENT
    protected int refinement(ItemStack itemStack, Player player, float progress, int totalCost) {

        var essenceCap = player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE);
        if (!essenceCap.isPresent()) return -1; // failed
        var essence = essenceCap.resolve().orElseThrow(
                () -> new IllegalStateException("PlayerEssence capability not resolved"));

        CompoundTag nbt = itemStack.getOrCreateTag();
        if (!nbt.contains(KEY_REFINE)) nbt.putFloat(KEY_REFINE, 0.0F);
        float currentProgress = nbt.getFloat(KEY_REFINE);

        if (currentProgress >= totalCost) return 1; // Max refinement reached
        if (essence.getEssence() < progress) return -1; // Insufficient essence

        nbt.putFloat(KEY_REFINE, currentProgress + progress);
        itemStack.setTag(nbt);
        essence.subEssence(progress);
        EssenceSyncS2CPacket.send((ServerPlayer) player, essence);

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
    public ModRank getItemRank() { return itemRank; }
    public int getRefinementCost() { return refinementCost; }
    public float getRefineValue() { return refineValue; }
}
