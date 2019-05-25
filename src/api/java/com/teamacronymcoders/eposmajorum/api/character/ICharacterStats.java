package com.teamacronymcoders.eposmajorum.api.character;

import com.teamacronymcoders.eposmajorum.api.clazz.ClassLevels;
import com.teamacronymcoders.eposmajorum.api.feat.Feats;
import com.teamacronymcoders.eposmajorum.api.skill.Skills;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICharacterStats extends INBTSerializable<NBTTagCompound> {
    ClassLevels getClassLevels();

    Feats getFeats();

    Skills getSkills();
}
