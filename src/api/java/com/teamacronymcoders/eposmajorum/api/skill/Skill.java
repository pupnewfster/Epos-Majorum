package com.teamacronymcoders.eposmajorum.api.skill;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nonnull;

public class Skill implements ISkill {
    private final ResourceLocation registryName;
    private final ITextComponent name;
    private final ITextComponent description;

    public Skill(ResourceLocation registryName, ITextComponent name, ITextComponent description) {
        this.registryName = registryName;
        this.name = name;
        this.description = description;
    }

    @Override
    public ITextComponent getName() {
        return this.name;
    }

    @Override
    public ITextComponent getDescription() {
        return this.description;
    }

    @Nonnull
    @Override
    public ResourceLocation getRegistryName() {
        return this.registryName;
    }

    @Override
    public int compareTo(ISkill o) {
        return this.getName().getString().compareTo(o.getName().getString());
    }
}
