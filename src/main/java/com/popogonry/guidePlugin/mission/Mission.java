package com.popogonry.guidePlugin.mission;

import java.util.List;

public class Mission {
    private final int id;
    private final String title;
    private final List<String> description;
    private final int clearState;

    public Mission(int id, String title, List<String> description, int clearState) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.clearState = clearState;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getDescription() {
        return description;
    }

    public int getClearState() {
        return clearState;
    }
}
