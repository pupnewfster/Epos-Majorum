package com.teamacronymcoders.eposmajorum.api.pathfeature;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.EposCapabilities;
import com.teamacronymcoders.eposmajorum.api.feat.FeatSource;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class FeatFeature implements IPathFeature {
    public static final FeatSource FEATURE_SOURCE =
            new FeatSource(new ResourceLocation(EposAPI.ID, "feature"), false);

    private final Feat feat;

    public FeatFeature(Feat feat) {
        this.feat = feat;
    }

    @Override
    public void applyTo(EntityLivingBase entityLivingBase) {
        entityLivingBase.getCapability(EposCapabilities.CHARACTER_STATS)
                .ifPresent(iCharacterStats -> iCharacterStats
                        .getFeats()
                        .addFeat(feat, FEATURE_SOURCE));
    }

    @Override
    public void removeFrom(EntityLivingBase entityLivingBase) {
        entityLivingBase.getCapability(EposCapabilities.CHARACTER_STATS)
                .ifPresent(iCharacterStats -> iCharacterStats
                        .getFeats()
                        .removeFeat(feat));
    }
}
