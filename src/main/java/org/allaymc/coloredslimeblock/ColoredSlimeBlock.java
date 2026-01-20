package org.allaymc.coloredslimeblock;

import org.allaymc.api.plugin.Plugin;

public class ColoredSlimeBlock extends Plugin {
    @Override
    public void onLoad() {
        this.pluginLogger.info("ColoredSlimeBlock is loaded!");
    }

    @Override
    public void onEnable() {
        this.pluginLogger.info("ColoredSlimeBlock is enabled!");
    }

    @Override
    public void onDisable() {
        this.pluginLogger.info("ColoredSlimeBlock is disabled!");
    }
}