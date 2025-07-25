package net.alex.guzhenren.item;

import net.alex.guzhenren.GuzhenrenMod;
import net.alex.guzhenren.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GuzhenrenMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GU_MATERIAL_TAB = CREATIVE_MODE_TABS.register("gu_material_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PRIMEVAL_STONE.get()))
                    .title(Component.translatable("creative_tab.gu_material_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.PRIMEVAL_STONE.get());
                        output.accept(ModItems.APERTURE.get());
                        output.accept(ModItems.HUMAN_HEART.get());
                        output.accept(ModItems.LIQUOR.get());
                    })).build());

    public static final RegistryObject<CreativeModeTab> GU_WORLD_TAB = CREATIVE_MODE_TABS.register("gu_world_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PRIMEVAL_STONE_ORE.get()))
                    .title(Component.translatable("creative_tab.gu_world_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.PRIMEVAL_STONE_BLOCK.get());
                        output.accept(ModBlocks.PRIMEVAL_STONE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_PRIMEVAL_STONE_ORE.get());
                        output.accept(ModBlocks.NETHER_PRIMEVAL_STONE_ORE.get());
                        output.accept(ModBlocks.END_STONE_PRIMEVAL_STONE_ORE.get());
                        output.accept(ModBlocks.MOON_ORCHID.get());
                    })).build());

    public static final RegistryObject<CreativeModeTab> MORTAL_GU_TAB = CREATIVE_MODE_TABS.register("mortal_gu_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.HOPE_GU.get()))
                    .title(Component.translatable("creative_tab.mortal_gu_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.HOPE_GU.get());
                        output.accept(ModItems.YEAR_LIFESPAN_GU.get());
                        output.accept(ModItems.TEN_YEAR_LIFESPAN_GU.get());
                        output.accept(ModItems.PRIMEVAL_ELDER_GU_3.get());
                        output.accept(ModItems.PRIMEVAL_ELDER_GU_4.get());
                    })).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
