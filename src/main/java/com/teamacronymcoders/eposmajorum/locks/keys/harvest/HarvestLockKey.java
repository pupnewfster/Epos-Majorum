package com.teamacronymcoders.eposmajorum.locks.keys.harvest;

import com.teamacronymcoders.eposmajorum.api.locks.IFuzzyLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.ILockKey;
import javax.annotation.Nullable;

public abstract class HarvestLockKey implements IFuzzyLockKey {

    protected final int harvestLevel;

    protected HarvestLockKey(int harvestLevel) {
        if (harvestLevel < 0) {
            //TODO: Add whatever information is needed here for a better error message
            throw new IllegalArgumentException();
        }
        this.harvestLevel = harvestLevel;
    }

    @Override
    public boolean isNotFuzzy() {
        return false;
    }

    @Override
    @Nullable
    public ILockKey getNotFuzzy() {
        return null;
    }
}