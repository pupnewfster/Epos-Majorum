package com.teamacronymcoders.eposmajorum.api;

import com.teamacronymcoders.eposmajorum.api.feat.IFeat;
import com.teamacronymcoders.eposmajorum.api.path.IPath;
import com.teamacronymcoders.eposmajorum.api.registry.Registry;
import com.teamacronymcoders.eposmajorum.api.skill.ISkill;

public class EposRegistries {
    public static final Registry<IPath> PATHS = new Registry<>();
    public static final Registry<IFeat> FEATS = new Registry<>();
    public static final Registry<ISkill> SKILLS = new Registry<>();
}
