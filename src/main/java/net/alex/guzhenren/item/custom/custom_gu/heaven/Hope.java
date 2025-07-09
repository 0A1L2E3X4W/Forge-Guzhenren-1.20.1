package net.alex.guzhenren.item.custom.custom_gu.heaven;

import net.alex.guzhenren.item.custom.CustomItem;
import net.alex.guzhenren.networking.s2c_packet.OpenAwakenGuiS2CPacket;
import net.alex.guzhenren.utils.capability.PlayerAptitudesUtils;
import net.alex.guzhenren.utils.capability.PlayerEssenceUtils;
import net.alex.guzhenren.utils.capability.PlayerFlagsUtils;
import com.alex.guzhenren.utils.enums.*;
import net.alex.guzhenren.utils.enums.*;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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

public class Hope extends CustomItem {

    public Hope(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(
            @NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {

        ItemStack itemStack = player.getItemInHand(usedHand);

        if (level.isClientSide()) {
            return InteractionResultHolder.pass(itemStack);
        }

        boolean isAwaken = PlayerFlagsUtils.isApertureAwaken(player);
        if (isAwaken) {
            return InteractionResultHolder.fail(itemStack);
        }

        ModTalent talent = generateRandomTalent();
        ModExtremePhysique extremePhysique = generateExtremePhysique(talent);
        int maxEssence = generateMaxEssence(talent);

        PlayerAptitudesUtils.setTalent(player, talent);
        PlayerAptitudesUtils.setRank(player, ModRank.ONE);
        PlayerAptitudesUtils.setStage(player, ModStage.INIT);
        PlayerAptitudesUtils.setExtremePhysique(player, extremePhysique);

        PlayerEssenceUtils.setMaxEssence(player, maxEssence);
        PlayerEssenceUtils.setCurrentEssence(player, maxEssence * 0.8f);

        PlayerFlagsUtils.setApertureAwaken(player, true);

        OpenAwakenGuiS2CPacket.send((ServerPlayer) player);

        player.getCooldowns().addCooldown(this, 3);
        if (!player.getAbilities().instabuild) { itemStack.shrink(1); }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    @Override
    public void appendHoverText(
            @NotNull ItemStack itemStack, @Nullable Level level, @NotNull List<Component> tooltip, @NotNull TooltipFlag isAdvanced) {
        super.appendHoverText(itemStack, level, tooltip, isAdvanced);

        Component commonStats = Component.translatable(ModRank.ONE.getNameKey()).append(" ")
                .append(Component.translatable(ModPath.HEAVEN.getNameKey()))
                .withStyle(style -> style.withColor(0xAA00AA));
        Component hopeGuText = Component.translatable("tooltip.guzhenren.hope")
                .withStyle(style -> style.withColor(0xFFD700));

        tooltip.add(commonStats);
        tooltip.add(hopeGuText);
    }

    private ModTalent generateRandomTalent() {
        int r = ThreadLocalRandom.current().nextInt(100);
        if (r < 25) return ModTalent.D;       // 25% 概率
        if (r < 50) return ModTalent.C;       // 25% 概率 (累计50%)
        if (r < 70) return ModTalent.B;       // 20% 概率 (累计70%)
        if (r < 90) return ModTalent.A;       // 20% 概率 (累计90%)
        return ModTalent.TEN_EXTREME;         // 10% 概率
    }

    private int generateMaxEssence(ModTalent talent) {
        return switch (talent) {
            case TEN_EXTREME -> 100000;
            case A -> ThreadLocalRandom.current().nextInt(80000, 100000);  // [80000, 99999]
            case B -> ThreadLocalRandom.current().nextInt(60000, 80000);   // [60000, 79999]
            case C -> ThreadLocalRandom.current().nextInt(40000, 60000);   // [40000, 59999]
            case D -> ThreadLocalRandom.current().nextInt(20000, 40000);   // [20000, 39999]
            default -> ThreadLocalRandom.current().nextInt(10000, 100001); // [10000, 100000]
        };
    }

    private ModExtremePhysique generateExtremePhysique(ModTalent talent) {
        if (talent != ModTalent.TEN_EXTREME) return ModExtremePhysique.NULL;
        List<ModExtremePhysique> naturalPhysique = ModExtremePhysique.getNaturalPhysiques();
        int r = ThreadLocalRandom.current().nextInt(naturalPhysique.size());
        return naturalPhysique.get(r);
    }
}
