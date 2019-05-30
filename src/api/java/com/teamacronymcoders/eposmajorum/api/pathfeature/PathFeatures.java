package com.teamacronymcoders.eposmajorum.api.pathfeature;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.List;

public class PathFeatures {
    private final Int2ObjectMap<List<IPathFeature>> featuresByLevel;
    private final int maxLevel;

    @SafeVarargs
    public PathFeatures(List<IPathFeature>... featuresByLevel) {
        int level = 0;
        this.featuresByLevel = new Int2ObjectArrayMap<>();

        for (List<IPathFeature> levelFeatures : featuresByLevel) {
            level++;
            if (levelFeatures != null && !levelFeatures.isEmpty()) {
                this.featuresByLevel.put(level, levelFeatures);
            }
        }
        maxLevel = level;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public List<IPathFeature> getFeaturesForLevel(int level) {
        return this.featuresByLevel.get(level);
    }
}
