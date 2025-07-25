
package com.popogonry.guidePlugin;

import com.popogonry.guidePlugin.util.Config;

public class PluginDataConfig extends Config {
    public PluginDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
    }

    public PluginConfig loadPluginDataConfig() {
        return new PluginConfig(
                this.getConfig().getString("GUI-Name"),
                this.getConfig().getString("Item-Name"),
                this.getConfig().getStringList("Item-Lore")
        );
    }

    public void loadDefaults() {
    }

    public void applySettings() {
        this.getConfig().options().copyDefaults(true);
    }
}