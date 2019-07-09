package com.teamacronymcoders.eposmajorum.api.locks;

import com.teamacronymcoders.eposmajorum.api.locks.keys.ILockKey;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@FunctionalInterface
public interface ILockKeyCreator<KEY extends ILockKey> {

    //TODO: Would it make more sense to have this not be functional, have a forced supports method, and also have a method for getting the name of the creator

    /**
     * @return null if it cannot be used to create a type.
     *
     * @implNote IMPORTANT: fail fast where possible, and ensure it is actually the type going to be used
     */
    @Nullable
    KEY createFrom(@Nonnull Object o);
}