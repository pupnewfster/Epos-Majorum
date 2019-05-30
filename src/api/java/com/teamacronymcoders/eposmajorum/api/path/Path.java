package com.teamacronymcoders.eposmajorum.api.path;

import com.teamacronymcoders.eposmajorum.api.pathfeature.PathFeatures;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;

public class Path implements IPath {
    private final ITextComponent name;
    private final ITextComponent description;
    private final PathFeatures pathFeatures;

    public Path(ITextComponent name, ITextComponent description, PathFeatures pathFeatures) {
        this.name = name;
        this.description = description;
        this.pathFeatures = pathFeatures;
    }

    public ITextComponent getName() {
        return name;
    }

    public ITextComponent getDescription() {
        return description;
    }

    @Override
    public PathFeatures getPathFeatures() {
        return pathFeatures;
    }

    @Override
    public void addLevel(EntityLivingBase living, int newClassLevel) {
        this.getPathFeatures().getFeaturesForLevel(newClassLevel)
                .forEach(iClassFeature -> iClassFeature.applyTo(living));
    }

    @Override
    public void removeLevel(EntityLivingBase living, int newClassLevel) {
        this.getPathFeatures().getFeaturesForLevel(newClassLevel + 1)
                .forEach(iClassFeature -> iClassFeature.removeFrom(living));
    }


}
