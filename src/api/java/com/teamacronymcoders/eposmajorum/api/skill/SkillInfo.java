package com.teamacronymcoders.eposmajorum.api.skill;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;

public class SkillInfo implements INBTSerializable<NBTTagCompound>, Comparable<SkillInfo> {
    private final String registryName;
    private final ISkill skill;
    private int experience;
    private int level;
    private boolean active;

    public SkillInfo(ISkill skill) {
        this.skill = skill;
        this.registryName = skill.getRegistryName().toString();
    }

    public String getRegistryName() {
        return this.registryName;
    }

    public ISkill getSkill() {
        return skill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return this.isActive() ? level : 0;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.putInt("experience", this.getExperience());
        nbt.putInt("level", this.getLevel());
        nbt.putBoolean("active", this.isActive());
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.setExperience(nbt.getInt("experience"));
        this.setLevel(nbt.getInt("level"));
        this.setActive(nbt.getBoolean("active") && this.skill.isFound());
    }

    @Override
    public int compareTo(@Nonnull SkillInfo o) {
        return this.getSkill().compareTo(o.getSkill());
    }
}
