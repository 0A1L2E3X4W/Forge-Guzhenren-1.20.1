package com.alex.guzhenren.item.custom.gu;

import com.alex.guzhenren.block.ModBlocks;
import com.alex.guzhenren.item.ModItems;
import com.alex.guzhenren.item.custom.ModCustomItem;
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

public class PrimevalElderGu extends ModCustomItem {

    public PrimevalElderGu(Properties properties, ModRank rank, int maxStorage) {
        super(properties, rank);
        this.maxStorage = maxStorage;
    }

    public static final String KEY_STORAGE = "guzhenren.item.primeval_elder";
    protected int maxStorage;

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);
        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        if (player.isShiftKeyDown()) {
            extractStones(player, itemStack);
            return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
        }

        int collected = calculateAndRemoveStones(player, itemStack);

        if (collected <= 0) {
            return InteractionResultHolder.fail(itemStack);
        }

        addStoredStones(itemStack, collected);
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(@NotNull ItemStack itemStack,
                                @Nullable Level level,
                                @NotNull List<Component> tooltip,
                                @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);
        tooltip.add(Component.translatable("guzhenren.text.storage")
                .append(": " + getStoredStones(itemStack) + "/" + maxStorage));
    }

    private int calculateAndRemoveStones(Player player, ItemStack itemStack) {
        int count = 0;
        int currentStored = getStoredStones(itemStack);
        int remainingSpace = maxStorage - currentStored;

        if (remainingSpace <= 0) { return 0; } // 没有剩余空间，直接返回

        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            if (remainingSpace <= 0) break; // 空间已满，停止处理

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

    private void addStoredStones(ItemStack itemStack, int amount) {
        CompoundTag nbt = itemStack.getOrCreateTag();
        int current = nbt.getInt(KEY_STORAGE);
        int newAmount = Math.min(current + amount, maxStorage); // 确保不超过上限
        nbt.putInt(KEY_STORAGE, newAmount);
        itemStack.setTag(nbt);
    }

    private int getStoredStones(ItemStack itemStack) {
        if (!itemStack.hasTag()) return 0;
        assert itemStack.getTag() != null;
        return itemStack.getTag().getInt(KEY_STORAGE);
    }

    private void removeStoredStones(ItemStack itemStack, int amount) {
        CompoundTag nbt = itemStack.getOrCreateTag();
        int current = nbt.getInt(KEY_STORAGE);
        nbt.putInt(KEY_STORAGE, Math.max(0, current - amount));
        itemStack.setTag(nbt);
    }

    private void extractStones(Player player, ItemStack itemStack) {
        int stored = getStoredStones(itemStack);

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

        removeStoredStones(itemStack, outputAmount);
    }
}
