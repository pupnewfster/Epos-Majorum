package com.teamacronymcoders.eposmajorum.api.pathfeature;

import com.teamacronymcoders.eposmajorum.api.characterstats.ICharacterStats;
import net.minecraft.entity.LivingEntity;
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

    void applyTo(LivingEntity character, ICharacterStats characterStats);

    void removeFrom(LivingEntity character, ICharacterStats characterStats);
}
