package com.teamacronymcoders.eposmajorum.api.skill;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;

public class SkillInfo implements INBTSerializable<NBTTagCompound>, Comparable<SkillInfo> {
    private String registryName;
    private ISkill skill;
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
        return level;
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
        nbt.putInt("experience", this.experience);
        nbt.putInt("level", this.level);
        nbt.putBoolean("active", this.active);
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.skill = EposAPI.SKILL_REGISTRY.getEntry(nbt.getString("skill"));
        this.registryName = skill.getRegistryName().toString();
        this.experience = nbt.getInt("experience");
        this.level = nbt.getInt("level");
        this.active = nbt.getBoolean("active") && this.skill.isFound();
    }

    @Override
    public int compareTo(@Nonnull SkillInfo o) {
        return this.getSkill().compareTo(o.getSkill());
    }
}
