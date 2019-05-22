package com.teamacronymcoders.eposmajorum.api.classfeature;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.util.List;

public class ClassFeatures {
    private final Int2ObjectMap<List<IClassFeature>> featuresByLevel;
    private final int maxLevel;

    @SafeVarargs
    public ClassFeatures(List<IClassFeature>... featuresByLevel) {
        int level = 0;
        this.featuresByLevel = new Int2ObjectArrayMap<>();

        for (List<IClassFeature> levelFeatures : featuresByLevel) {
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

    public List<IClassFeature> getFeaturesForLevel(int level) {
        return this.featuresByLevel.get(level);
    }
}
