package com.teamacronymcoders.eposmajorum.api.characterstats;

import com.teamacronymcoders.eposmajorum.api.feat.Feats;
import com.teamacronymcoders.eposmajorum.api.path.PathLevels;
import com.teamacronymcoders.eposmajorum.api.skill.Skills;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface ICharacterStats extends INBTSerializable<NBTTagCompound> {
    PathLevels getPathLevels();

    Feats getFeats();

    Skills getSkills();
}
