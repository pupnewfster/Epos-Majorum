package com.teamacronymcoders.eposmajorum.api.classfeature;

import net.minecraft.entity.EntityLivingBase;

public interface IClassFeature {
    void applyTo(EntityLivingBase entityLivingBase);

    void removeFrom(EntityLivingBase entityLivingBase);
}
