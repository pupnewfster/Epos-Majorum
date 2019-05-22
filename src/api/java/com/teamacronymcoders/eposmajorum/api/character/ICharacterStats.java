package com.teamacronymcoders.eposmajorum.api.character;

import com.teamacronymcoders.eposmajorum.api.clazz.ClassLevels;
import com.teamacronymcoders.eposmajorum.api.feat.Feats;
import com.teamacronymcoders.eposmajorum.api.skill.Skills;

public interface ICharacterStats {
    ClassLevels getClassLevels();

    Feats getFeats();

    Skills getSkills();
}
