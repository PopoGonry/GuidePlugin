package com.popogonry.guidePlugin.evolutionaryBook;

import com.popogonry.guidePlugin.GuidePlugin;
import com.popogonry.guidePlugin.util.ItemStackDataConfig;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class EvolutionaryBookRepository {

    public static List<ItemStack> evolutionaryBookList = new ArrayList<>();


    private static final String FILE_NAME = "evolutionaryBook.yml";
    private final String configBasePath;

    private final ItemStackDataConfig itemStackDataConfig;


    public EvolutionaryBookRepository() {
        this.configBasePath = GuidePlugin.getServerInstance().getDataFolder().getAbsolutePath();
        this.itemStackDataConfig = new ItemStackDataConfig(configBasePath, FILE_NAME);
    }

    public void reloadConfig() {
        itemStackDataConfig.reload();
    }
    public void saveConfig() {
        itemStackDataConfig.store();
    }

    public void storeEvolutionaryBook() {
        itemStackDataConfig.storeItemStackList("evolutionaryBookList", evolutionaryBookList);
        evolutionaryBookList.clear();
    }

    public void saveEvolutionaryBook() {
        itemStackDataConfig.storeItemStackList("evolutionaryBookList", evolutionaryBookList);
    }

    public void loadEvolutionaryBook() {
        if(itemStackDataConfig.hasItemStackList("evolutionaryBookList")) {
            evolutionaryBookList = itemStackDataConfig.loadItemStackList("evolutionaryBookList");
        } else {
            evolutionaryBookList = new ArrayList<>();
        }
    }
}
