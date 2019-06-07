package com.teamacronymcoders.eposmajorum.api.pathfeature;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;

public interface IPathFeature {
    /**
     * @return The Name of the Entry.
     */
    ITextComponent getName();

    /**
     * @return The Description of the Entry.
     */
    ITextComponent getDescription();

    void applyTo(EntityLivingBase entityLivingBase);

    void removeFrom(EntityLivingBase entityLivingBase);
}
