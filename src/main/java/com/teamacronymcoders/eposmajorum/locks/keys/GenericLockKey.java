package com.teamacronymcoders.eposmajorum.locks.keys;

import com.teamacronymcoders.eposmajorum.api.locks.ILockKey;
import javax.annotation.Nonnull;

//TODO: Can this be removed, porting it for now to not forget about it (Also it should follow the registry to wherever it is)
// From a quick glance I believe the use of this class was primarily for handling having a way to get all the different
// types of a fuzzy lock without worrying about if there is no "non fuzzy" type for that lock (for example a generic NBT lock)

//TODO: This definitely needs to be looked at/reworked as we do not want to keep the class information and we want
// a good way to pass on to the creator methods the "type"
public final class GenericLockKey implements ILockKey {

    @Nonnull
    private Class<? extends ILockKey> type;

    public GenericLockKey(@Nonnull Class<? extends ILockKey> type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof GenericLockKey && type.equals(((GenericLockKey) o).type);
    }

    @Override
    public int hashCode() {
        return type.hashCode();
    }
}