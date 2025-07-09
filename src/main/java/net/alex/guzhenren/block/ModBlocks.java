package net.alex.guzhenren.block;

import net.alex.guzhenren.GuzhenrenMod;
import net.alex.guzhenren.block.flower.MoonOrchid;
import net.alex.guzhenren.block.flower.PottedMoonOrchid;
import net.alex.guzhenren.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, GuzhenrenMod.MOD_ID);

    public static final RegistryObject<Block> PRIMEVAL_STONE_BLOCK = registerBlock("primeval_stone_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST)));

    // ========== ORES ==========
    public static final RegistryObject<Block> PRIMEVAL_STONE_ORE = registerBlock("primeval_stone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(5, 9)));
    public static final RegistryObject<Block> DEEPSLATE_PRIMEVAL_STONE_ORE = registerBlock("deepslate_primeval_stone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(6, 10)));
    public static final RegistryObject<Block> NETHER_PRIMEVAL_STONE_ORE = registerBlock("nether_primeval_stone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHERRACK)
                    .strength(1f).requiresCorrectToolForDrops(), UniformInt.of(6, 10)));
    public static final RegistryObject<Block> END_STONE_PRIMEVAL_STONE_ORE = registerBlock("end_stone_primeval_stone_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(6, 10)));

    // ========== FLOWERS ==========
    public static final RegistryObject<Block> MOON_ORCHID = registerBlock("moon_orchid", MoonOrchid::new);
    public static final RegistryObject<Block> POTTED_MOON_ORCHID = BLOCKS.register("potted_moon_orchid", PottedMoonOrchid::new);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) { BLOCKS.register(eventBus); }
}
