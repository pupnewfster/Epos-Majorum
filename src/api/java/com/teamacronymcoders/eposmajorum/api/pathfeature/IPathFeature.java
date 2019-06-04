package com.teamacronymcoders.eposmajorum.api.pathfeature;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;

public interface IPathFeature {
    ITextComponent getDescription();

    void applyTo(EntityLivingBase entityLivingBase);

    void removeFrom(EntityLivingBase entityLivingBase);
}
