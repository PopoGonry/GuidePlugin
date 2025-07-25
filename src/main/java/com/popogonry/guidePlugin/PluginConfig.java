
package com.popogonry.guidePlugin;

import java.util.List;

public class PluginConfig {
    private final String guiName;
    private final String itemName;
    private final List<String> itemLore;

    public PluginConfig(String guiName, String itemName, List<String> itemLore) {
        this.guiName = guiName;
        this.itemName = itemName;
        this.itemLore = itemLore;
    }

    @Override
    public String toString() {
        return "PluginConfig{" +
                "guiName='" + guiName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemLore=" + itemLore +
                '}';
    }

    public String getGuiName() {
        return guiName;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getItemLore() {
        return itemLore;
    }
}
