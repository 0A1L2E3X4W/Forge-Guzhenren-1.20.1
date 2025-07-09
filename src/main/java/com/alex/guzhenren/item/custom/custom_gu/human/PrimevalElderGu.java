package com.alex.guzhenren.item.custom.custom_gu.human;

import com.alex.guzhenren.block.ModBlocks;
import com.alex.guzhenren.item.ModItems;
import com.alex.guzhenren.item.custom.custom_gu.MortalGu;
import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModRank;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PrimevalElderGu extends MortalGu {

    public PrimevalElderGu(Properties properties, ModRank rank, ModPath path, int refinementCost, int maxStorage) {
        super(properties, rank, path, refinementCost);
        this.maxStorage = maxStorage;
    }

    public static final String KEY_STORAGE = "key.guzhenren.item.primeval_elder";
    private final int maxStorage;

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        // 炼化蛊虫
        int refineResult = refinement(itemStack, player, refineValue, refinementCost);
        if (refineResult == -1) { return InteractionResultHolder.fail(itemStack); }
        else if (refineResult == 0) { return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide()); }

        // 使用物品
        if (player.isShiftKeyDown()) {
            extractItems(player, itemStack);
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }

        int collected = calculateAndRemoveTargetItem(player, itemStack);
        if (collected <= 0) {
            return InteractionResultHolder.fail(itemStack);
        }

        addStoredItems(itemStack, collected);
        player.getCooldowns().addCooldown(this, 3);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);
        tooltip.add(Component.translatable("guzhenren.text.storage").append(": " + getStoredItems(itemStack) + "/" + maxStorage));

        refinementTooltips(itemStack, tooltip, refinementCost);
    }

    private int calculateAndRemoveTargetItem(Player player, ItemStack itemStack) {
        int count = 0;
        int currentStored = getStoredItems(itemStack);
        int remainingSpace = maxStorage - currentStored;

        if (remainingSpace <= 0) { return 0; }

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            if (remainingSpace <= 0) break;

            ItemStack stack = player.getInventory().getItem(i);
            if (stack.isEmpty()) continue;

            if (stack.getItem() == ModItems.PRIMEVAL_STONE.get()) {
                int available = stack.getCount();
                int canAdd = Math.min(available, remainingSpace);

                if (canAdd > 0) {
                    count += canAdd;
                    remainingSpace -= canAdd;

                    if (canAdd == available) {
                        player.getInventory().setItem(i, ItemStack.EMPTY);
                    } else {
                        stack.shrink(canAdd);
                    }
                }
            }
            else if (stack.getItem() == ModBlocks.PRIMEVAL_STONE_BLOCK.get().asItem()) {
                int blockValue = 9; // 每个元石块相当于9个元石
                int available = stack.getCount();

                // 计算可以添加的完整块数
                int canAddBlocks = Math.min(available, remainingSpace / blockValue);
                if (canAddBlocks > 0) {
                    int stonesToAdd = canAddBlocks * blockValue;
                    count += stonesToAdd;
                    remainingSpace -= stonesToAdd;

                    if (canAddBlocks == available) {
                        player.getInventory().setItem(i, ItemStack.EMPTY);
                    } else {
                        stack.shrink(canAddBlocks);
                    }
                }
            }
        }
        return count;
    }

    private void addStoredItems(ItemStack itemStack, int amount) {
        CompoundTag nbt = itemStack.getOrCreateTag();
        int current = nbt.getInt(KEY_STORAGE);
        int newAmount = Math.min(current + amount, maxStorage); // 确保不超过上限
        nbt.putInt(KEY_STORAGE, newAmount);
        itemStack.setTag(nbt);
    }

    private int getStoredItems(ItemStack itemStack) {
        if (!itemStack.hasTag()) return 0;
        assert itemStack.getTag() != null;
        return itemStack.getTag().getInt(KEY_STORAGE);
    }

    private void removeStoredItems(ItemStack itemStack, int amount) {
        CompoundTag nbt = itemStack.getOrCreateTag();
        int current = nbt.getInt(KEY_STORAGE);
        nbt.putInt(KEY_STORAGE, Math.max(0, current - amount));
        itemStack.setTag(nbt);
    }

    private void extractItems(Player player, ItemStack itemStack) {
        int stored = getStoredItems(itemStack);

        if (stored <= 0) {
            return;
        }

        int outputAmount = Math.min(stored, 64);
        ItemStack outputStack = new ItemStack(ModItems.PRIMEVAL_STONE.get(), outputAmount);

        if (player.getOffhandItem().isEmpty()) {
            player.setItemInHand(InteractionHand.OFF_HAND, outputStack);
        } else {
            ItemHandlerHelper.giveItemToPlayer(player, outputStack);
        }

        removeStoredItems(itemStack, outputAmount);
    }
}
