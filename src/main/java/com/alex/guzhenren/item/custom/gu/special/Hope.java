package com.alex.guzhenren.item.custom.gu.special;

import com.alex.guzhenren.capability.providers.PlayerAptitudesProvider;
import com.alex.guzhenren.capability.providers.PlayerEssenceProvider;
import com.alex.guzhenren.capability.providers.PlayerFlagsProvider;
import com.alex.guzhenren.item.custom.ModCustomItem;
import com.alex.guzhenren.utils.enums.ModPath;
import com.alex.guzhenren.utils.enums.ModRank;
import com.alex.guzhenren.utils.enums.ModStage;
import com.alex.guzhenren.utils.enums.ModTalent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Hope extends ModCustomItem {

    public Hope(Properties properties) {
        super(properties, ModRank.ONE, ModPath.HEAVEN);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level,
            @NotNull Player player,
            @NotNull InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        var essenceCap = player.getCapability(PlayerEssenceProvider.PLAYER_ESSENCE);
        var aptitudeCap = player.getCapability(PlayerAptitudesProvider.PLAYER_APTITUDE);
        var flagsCap = player.getCapability(PlayerFlagsProvider.PLAYER_FLAGS);

        if (!essenceCap.isPresent() || !aptitudeCap.isPresent() || !flagsCap.isPresent()) {
            return InteractionResultHolder.fail(itemStack);
        }

        var essence = essenceCap.resolve().orElseThrow(
                () -> new IllegalStateException("PlayerEssence capability not resolved"));;
        var aptitude = aptitudeCap.resolve().orElseThrow(
                () -> new IllegalStateException("PlayerAptitude capability not resolved"));;
        var flags = flagsCap.resolve().orElseThrow(
                () -> new IllegalStateException("PlayerFlags capability nor resolved"));

        boolean isAwaken = flags.isAwaken();

        if (isAwaken) {
            return InteractionResultHolder.fail(itemStack);
        }

        ModTalent talent = getRandomTalent();
        int maxEssence = getMaxEssence(talent);

        aptitude.setTalent(talent);
        aptitude.setRank(ModRank.ONE);
        aptitude.setStage(ModStage.INIT);
        essence.setMaxEssence(maxEssence);
        essence.setCurrentEssence(maxEssence * 0.8f);
        flags.setAwaken(true);

        player.getCooldowns().addCooldown(this, 3);
        if (!player.getAbilities().instabuild) { itemStack.shrink(1); }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack,
            @Nullable Level level,
            @NotNull List<Component> tooltip,
            @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);
        tooltip.add(Component.translatable("tooltip.guzhenren.hope")
                .withStyle(style -> style.withColor(0xFFD700)));
    }

    private ModTalent getRandomTalent() {
        int r = ThreadLocalRandom.current().nextInt(100);
        if (r < 25) return ModTalent.D;       // 25% 概率
        if (r < 50) return ModTalent.C;       // 25% 概率 (累计50%)
        if (r < 70) return ModTalent.B;       // 20% 概率 (累计70%)
        if (r < 90) return ModTalent.A;       // 20% 概率 (累计90%)
        return ModTalent.TEN_EXTREME;         // 10% 概率
    }

    private int getMaxEssence(ModTalent talent) {
        return switch (talent) {
            case TEN_EXTREME -> 100000;
            case A -> ThreadLocalRandom.current().nextInt(80000, 100000);  // [80000, 99999]
            case B -> ThreadLocalRandom.current().nextInt(60000, 80000);   // [60000, 79999]
            case C -> ThreadLocalRandom.current().nextInt(40000, 60000);   // [40000, 59999]
            case D -> ThreadLocalRandom.current().nextInt(20000, 40000);   // [20000, 39999]
            default -> ThreadLocalRandom.current().nextInt(10000, 100001); // [10000, 100000]
        };
    }
}
