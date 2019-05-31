package com.teamacronymcoders.eposmajorum.api;

import com.teamacronymcoders.eposmajorum.api.registry.Registry;
import com.teamacronymcoders.eposmajorum.api.skill.ISkill;

public class EposAPI {
    public static final String ID = "eposmajorum";
    public static final Registry<IPath> PATH_REGISTRY = new Registry<>();
    public static final Registry<Feat> FEAT_REGISTRY = new Registry<>();
    public static final Registry<ISkill> SKILL_REGISTRY = new Registry<>();
}
