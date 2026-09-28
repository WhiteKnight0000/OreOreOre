package com.whiteknight.oreoreore.game.util;

public enum ModStructures {
    DIRT_WALL("dirt_wall");

    private final String path;

    ModStructures(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
