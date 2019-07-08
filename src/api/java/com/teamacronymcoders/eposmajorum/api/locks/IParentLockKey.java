package com.teamacronymcoders.eposmajorum.api.locks;

public interface IParentLockKey extends ILockKey {
    /**
     * Retrieves any sub requirements this key may have.
     * This usually can be implemented by calling {@link LockRegistry#getLocks(Class, Object[])}
     *
     * @return A RequirementHolder of the sub requirements.
     */
    //List<RequirementHolder> getSubRequirements();
    //TODO: Make this return a list so that it can combine them on a higher level using whatever specific logic handler it wants to use
}