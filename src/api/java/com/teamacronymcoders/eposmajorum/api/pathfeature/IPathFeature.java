package com.teamacronymcoders.eposmajorum.api.pathfeature;

import net.minecraft.entity.EntityLivingBase;

public interface IPathFeature {
    void applyTo(EntityLivingBase entityLivingBase);

    void removeFrom(EntityLivingBase entityLivingBase);
}
