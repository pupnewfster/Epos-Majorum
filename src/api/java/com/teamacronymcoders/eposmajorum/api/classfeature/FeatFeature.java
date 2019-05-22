package com.teamacronymcoders.eposmajorum.api.classfeature;

import com.teamacronymcoders.eposmajorum.api.feat.IFeat;
import net.minecraft.entity.EntityLivingBase;

public class FeatFeature implements IClassFeature {
    private final IFeat feat;

    public FeatFeature(IFeat feat) {
        this.feat = feat;
    }

    @Override
    public void applyTo(EntityLivingBase entityLivingBase) {

    }

    @Override
    public void removeFrom(EntityLivingBase entityLivingBase) {

    }
}
