package com.teamacronymcoders.eposmajorum.api.pathfeature;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.EposCapabilities;
import com.teamacronymcoders.eposmajorum.api.feat.FeatSource;
import com.teamacronymcoders.eposmajorum.api.feat.IFeat;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class FeatFeature implements IPathFeature {
    public static final FeatSource FEATURE_SOURCE =
            new FeatSource(new ResourceLocation(EposAPI.ID, "feature"), false);

    private final IFeat feat;
    private final ITextComponent description;

    public FeatFeature(IFeat feat) {
        this.feat = feat;
        this.description = new TextComponentTranslation("pathfeature.eposmajorum.feat.description");
    }

    @Override
    public ITextComponent getDescription() {
        return null;
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
