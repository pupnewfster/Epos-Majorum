package com.teamacronymcoders.eposmajorum.api.characterstats;

import com.teamacronymcoders.eposmajorum.api.feat.Feats;
import com.teamacronymcoders.eposmajorum.api.path.PathLevels;
import com.teamacronymcoders.eposmajorum.api.skill.Skills;
import net.minecraft.nbt.NBTTagCompound;

public class CharacterStats implements ICharacterStats {
    private final PathLevels pathLevels;
    private final Feats feats;
    private final Skills skills;

    public CharacterStats() {
        this(new PathLevels(), new Feats(), new Skills());
    }

    public CharacterStats(PathLevels pathLevels, Feats feats, Skills skills) {
        this.pathLevels = pathLevels;
        this.feats = feats;
        this.skills = skills;
    }

    @Override
    public PathLevels getPathLevels() {
        return pathLevels;
    }

    @Override
    public Feats getFeats() {
        return feats;
    }

    @Override
    public Skills getSkills() {
        return skills;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.put("path_levels", this.getPathLevels().serializeNBT());
        nbtTagCompound.put("feats", this.getFeats().serializeNBT());
        nbtTagCompound.put("skills", this.getSkills().serializeNBT());
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.getPathLevels().deserializeNBT(nbt.getCompound("path_levels"));
        this.getFeats().deserializeNBT(nbt.getCompound("feats"));
        this.getSkills().deserializeNBT(nbt.getCompound("skills"));
    }
}
