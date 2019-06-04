package com.teamacronymcoders.eposmajorum.api.skill;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class Skills implements INBTSerializable<NBTTagCompound> {
    private final Map<String, SkillInfo> skillInfoMap;

    public Skills() {
        skillInfoMap = new HashMap<>();
    }

    @Nullable
    public SkillInfo get(String name) {
        return this.skillInfoMap.get(name);
    }

    @Nonnull
    public SkillInfo getOrCreate(String name) {
        SkillInfo skillInfo = this.skillInfoMap.get(name);
        if (skillInfo == null) {
            skillInfo = new SkillInfo(EposAPI.SKILL_REGISTRY.getEntryOrMissing(name));
            this.putSkillInfo(skillInfo);
        }
        return skillInfo;
    }

    public void putSkill(ISkill skill) {
        this.putSkillInfo(skill.createSkillInfo());
    }

    public void putSkillInfo(SkillInfo skillInfo) {
        this.skillInfoMap.put(skillInfo.getRegistryName(), skillInfo);
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.skillInfoMap.values().forEach(skillInfo -> nbt.put(skillInfo.getRegistryName(), skillInfo.serializeNBT()));
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        this.skillInfoMap.clear();
        nbt.keySet().stream()
                .map(key -> Pair.of(key, nbt.getCompound(key)))
                .map(compound -> {
                    ISkill skill = EposAPI.SKILL_REGISTRY.getEntryOrMissing(compound.getKey());
                    SkillInfo skillInfo = skill.createSkillInfo();
                    skillInfo.deserializeNBT(compound.getValue());
                    return skillInfo;
                }).forEach(skillInfo -> skillInfoMap.put(skillInfo.getRegistryName(), skillInfo));
    }
}
