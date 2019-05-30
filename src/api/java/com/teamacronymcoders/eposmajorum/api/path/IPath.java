package com.teamacronymcoders.eposmajorum.api.path;

import com.teamacronymcoders.eposmajorum.api.pathfeature.PathFeatures;
import com.teamacronymcoders.eposmajorum.api.registry.IRegistryEntry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;

public interface IPath extends IRegistryEntry {

    /**
     * @return The Name of the Class.
     */
    ITextComponent getName();

    /**
     * @return The Description of the Class. Class Feature descriptions are handled separately
     */
    ITextComponent getDescription();

    /**
     * @return An Immutable Object Containing All the Feats, Stat Increases, and Other Features
     * that a character will receive as they level up.
     */
    PathFeatures getPathFeatures();

    void addLevel(EntityLivingBase living, int newClassLevel);

    void removeLevel(EntityLivingBase living, int newClassLevel);
}
