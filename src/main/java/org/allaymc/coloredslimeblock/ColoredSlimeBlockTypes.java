package org.allaymc.coloredslimeblock;

import lombok.Getter;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;

/**
 * Enum containing all 16 colored slime block types with their properties.
 */
@Getter
public enum ColoredSlimeBlockTypes {
    RED("red", "Red Slime Block", ItemTypes.RED_DYE),
    ORANGE("orange", "Orange Slime Block", ItemTypes.ORANGE_DYE),
    YELLOW("yellow", "Yellow Slime Block", ItemTypes.YELLOW_DYE),
    LIME("lime", "Lime Slime Block", ItemTypes.LIME_DYE),
    GREEN("green", "Green Slime Block", ItemTypes.GREEN_DYE),
    LIGHT_BLUE("light_blue", "Light Blue Slime Block", ItemTypes.LIGHT_BLUE_DYE),
    BLUE("blue", "Blue Slime Block", ItemTypes.BLUE_DYE),
    CYAN("cyan", "Cyan Slime Block", ItemTypes.CYAN_DYE),
    PURPLE("purple", "Purple Slime Block", ItemTypes.PURPLE_DYE),
    MAGENTA("magenta", "Magenta Slime Block", ItemTypes.MAGENTA_DYE),
    PINK("pink", "Pink Slime Block", ItemTypes.PINK_DYE),
    BROWN("brown", "Brown Slime Block", ItemTypes.BROWN_DYE),
    WHITE("white", "White Slime Block", ItemTypes.WHITE_DYE),
    LIGHT_GRAY("light_gray", "Light Gray Slime Block", ItemTypes.LIGHT_GRAY_DYE),
    GRAY("gray", "Gray Slime Block", ItemTypes.GRAY_DYE),
    BLACK("black", "Black Slime Block", ItemTypes.BLACK_DYE);

    private final String colorId;
    private final String displayName;
    private final ItemType<?> dyeType;

    ColoredSlimeBlockTypes(String colorId, String displayName, ItemType<?> dyeType) {
        this.colorId = colorId;
        this.displayName = displayName;
        this.dyeType = dyeType;
    }

    /**
     * Get the block identifier (e.g., "coloredslime:red_slime_block")
     */
    public String getIdentifier() {
        return "coloredslime:" + colorId + "_slime_block";
    }

    /**
     * Get the texture name (e.g., "red_slime_block")
     */
    public String getTextureName() {
        return colorId + "_slime_block";
    }
}
