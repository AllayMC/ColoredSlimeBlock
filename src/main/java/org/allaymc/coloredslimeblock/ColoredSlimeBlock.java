package org.allaymc.coloredslimeblock;

import org.allaymc.api.block.data.BlockStateData;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.recipe.ShapelessRecipe;
import org.allaymc.api.item.recipe.descriptor.ItemDescriptor;
import org.allaymc.api.item.recipe.descriptor.ItemTypeDescriptor;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.api.item.creative.CreativeItemCategory;
import org.allaymc.server.block.component.BlockSlimeBaseComponentImpl;
import org.allaymc.server.block.component.BlockStateDataComponentImpl;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.block.type.AllayBlockType;
import org.allaymc.server.block.type.BlockStateDefinition;
import org.allaymc.server.block.type.CustomBlockDefinitionGenerator;

import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class ColoredSlimeBlock extends Plugin {

    private final Map<ColoredSlimeBlockTypes, BlockType<?>> blockTypes = new EnumMap<>(ColoredSlimeBlockTypes.class);

    @Override
    public void onLoad() {
        this.pluginLogger.info("ColoredSlimeBlock is loading...");
        registerBlockTypes();
        this.pluginLogger.info("ColoredSlimeBlock loaded! Registered " + blockTypes.size() + " colored slime blocks.");
    }

    @Override
    public void onEnable() {
        this.pluginLogger.info("ColoredSlimeBlock is enabling...");
        registerCraftingRecipes();
        registerCreativeItems();
        this.pluginLogger.info("ColoredSlimeBlock enabled!");
    }

    @Override
    public void onDisable() {
        this.pluginLogger.info("ColoredSlimeBlock is disabled!");
    }

    private void registerBlockTypes() {
        for (ColoredSlimeBlockTypes colorType : ColoredSlimeBlockTypes.values()) {
            BlockType<?> blockType = createBlockType(colorType);
            blockTypes.put(colorType, blockType);
        }
    }

    private BlockType<?> createBlockType(ColoredSlimeBlockTypes colorType) {
        return AllayBlockType.builder(BlockBehaviorImpl.class)
                .identifier(new Identifier(colorType.getIdentifier()))
                .addComponent(BlockStateDataComponentImpl.ofGlobalStatic(
                        BlockStateData.builder()
                                .friction(0.8f)           // Slippery like slime
                                .lightDampening(0)        // Transparent
                                .hardness(0.0f)           // Instant break like slime
                                .explosionResistance(0.0f)
                                .build()
                ))
                .setBaseComponentSupplier(BlockSlimeBaseComponentImpl::new)
                .setBlockTags(Set.of(BlockTags.CAN_STICK_BLOCKS))
                .blockDefinitionGenerator(CustomBlockDefinitionGenerator.ofConstant(
                        BlockStateDefinition.builder()
                                .displayName(colorType.getDisplayName())
                                .materials(BlockStateDefinition.Materials.builder()
                                        .any(BlockStateDefinition.MaterialInstance.of(
                                                colorType.getTextureName(),
                                                BlockStateDefinition.RenderMethod.BLEND
                                        ))
                                )
                                .build()
                ))
                .build();
    }

    private void registerCraftingRecipes() {
        for (ColoredSlimeBlockTypes colorType : ColoredSlimeBlockTypes.values()) {
            BlockType<?> blockType = blockTypes.get(colorType);
            if (blockType == null) continue;

            Identifier recipeId = new Identifier("coloredslime", colorType.getColorId() + "_slime_block_recipe");

            ShapelessRecipe recipe = new ShapelessRecipe(
                    recipeId,
                    new ItemStack[]{
                            blockType.getItemType().createItemStack(1)
                    },
                    0, // priority
                    new ItemDescriptor[]{
                            new ItemTypeDescriptor(ItemTypes.SLIME),
                            new ItemTypeDescriptor(colorType.getDyeType())
                    },
                    ShapelessRecipe.Type.CRAFTING
            );

            Registries.RECIPES.register(recipeId, recipe);
            pluginLogger.debug("Registered recipe for " + colorType.getDisplayName());
        }
    }

    private void registerCreativeItems() {
        var natureCategory = Registries.CREATIVE_ITEMS.getCategory(CreativeItemCategory.Type.NATURE);

        // Get the first block's item stack for the group icon
        BlockType<?> firstBlockType = blockTypes.get(ColoredSlimeBlockTypes.CYAN);
        if (firstBlockType == null) return;

        var group = natureCategory.registerGroup(
                "itemGroup.name.coloredslimeblock",
                firstBlockType.getItemType().createItemStack()
        );

        // Add all colored slime blocks to the group
        for (ColoredSlimeBlockTypes colorType : ColoredSlimeBlockTypes.values()) {
            BlockType<?> blockType = blockTypes.get(colorType);
            if (blockType != null) {
                group.registerItem(blockType.getItemType().createItemStack());
            }
        }

        pluginLogger.info("Registered " + blockTypes.size() + " colored slime blocks to creative inventory.");
    }
}
