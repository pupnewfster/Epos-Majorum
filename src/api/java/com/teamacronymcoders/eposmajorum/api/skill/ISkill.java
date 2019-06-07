package com.teamacronymcoders.eposmajorum.api.skill;

import com.teamacronymcoders.eposmajorum.api.registry.INamedRegistryEntry;

public interface ISkill extends INamedRegistryEntry<ISkill> {
    SkillInfo createSkillInfo();

    boolean isHidden();

    int getMaxLevel();
}
