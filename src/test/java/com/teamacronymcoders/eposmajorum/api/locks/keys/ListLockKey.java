package com.teamacronymcoders.eposmajorum.api.locks.keys;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.requirements.IRequirement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ListLockKey<TYPE> implements IParentLockKey {

    private final List<TYPE> values;

    private ListLockKey(List<TYPE> values) {
        this.values = values;
    }

    @Nullable
    public static ListLockKey<?> fromObject(@Nonnull Object object) {
        if (object instanceof List<?>) {
            return new ListLockKey<>((List<?>) object);
        } else if (object instanceof String) {
            return new ListLockKey<>(Arrays.asList(((String) object).split("\\s*,\\s*")));
        }
        return null;
    }

    @Nonnull
    @Override
    public List<IRequirement> getSubRequirements() {
        List<IRequirement> requirements = new ArrayList<>();
        for (TYPE value : values) {
            requirements.addAll(EposAPI.LOCK_REGISTRY.getLocks(value));
        }
        return requirements;
    }
}