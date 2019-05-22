package com.teamacronymcoders.eposmajorum.api.clazz;

import com.teamacronymcoders.eposmajorum.api.classfeature.ClassFeatures;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.ITextComponent;

public class Class implements IClass {
    private final ITextComponent name;
    private final ITextComponent description;
    private final ClassFeatures classFeatures;

    public Class(ITextComponent name, ITextComponent description, ClassFeatures classFeatures) {
        this.name = name;
        this.description = description;
        this.classFeatures = classFeatures;
    }

    public ITextComponent getName() {
        return name;
    }

    public ITextComponent getDescription() {
        return description;
    }

    @Override
    public ClassFeatures getClassFeatures() {
        return classFeatures;
    }

    @Override
    public void addLevel(EntityLivingBase living, int newClassLevel) {
        this.getClassFeatures().getFeaturesForLevel(newClassLevel)
                .forEach(iClassFeature -> iClassFeature.applyTo(living));
    }

    @Override
    public void removeLevel(EntityLivingBase living, int newClassLevel) {
        this.getClassFeatures().getFeaturesForLevel(newClassLevel + 1)
                .forEach(iClassFeature -> iClassFeature.removeFrom(living));
    }


}
