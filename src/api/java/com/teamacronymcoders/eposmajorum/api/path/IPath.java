package com.teamacronymcoders.eposmajorum.api.path;

import com.teamacronymcoders.eposmajorum.api.pathfeature.PathFeatures;
import com.teamacronymcoders.eposmajorum.api.registry.INamedRegistryEntry;
import net.minecraft.entity.EntityLivingBase;

public interface IPath extends INamedRegistryEntry<IPath> {
    /**
     * @return An Immutable Object Containing All the Feats, Stat Increases, and Other Features
     * that a character will receive as they level up.
     */
    PathFeatures getPathFeatures();

    void addLevel(EntityLivingBase living, int newClassLevel);

    void removeLevel(EntityLivingBase living, int newClassLevel);
}
