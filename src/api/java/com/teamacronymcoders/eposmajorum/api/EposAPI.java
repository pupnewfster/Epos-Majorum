package com.teamacronymcoders.eposmajorum.api;

import com.teamacronymcoders.eposmajorum.api.feat.IFeat;
import com.teamacronymcoders.eposmajorum.api.feat.MissingFeat;
import com.teamacronymcoders.eposmajorum.api.locks.LockRegistry;
import com.teamacronymcoders.eposmajorum.api.path.IPath;
import com.teamacronymcoders.eposmajorum.api.path.MissingPath;
import com.teamacronymcoders.eposmajorum.api.pathfeature.IPathFeatureProvider;
import com.teamacronymcoders.eposmajorum.api.pathfeature.MissingPathFeatureProvider;
import com.teamacronymcoders.eposmajorum.api.registry.Registry;
import com.teamacronymcoders.eposmajorum.api.skill.ISkill;
import com.teamacronymcoders.eposmajorum.api.skill.MissingSkill;

public class EposAPI {
    public static final String ID = "eposmajorum";

    public static final Registry<IPath> PATH_REGISTRY = new Registry<>(MissingPath::new);
    public static final Registry<IFeat> FEAT_REGISTRY = new Registry<>(MissingFeat::new);
    public static final Registry<ISkill> SKILL_REGISTRY = new Registry<>(MissingSkill::new);
    public static final Registry<IPathFeatureProvider> PATH_FEATURE_PROVIDER_REGISTRY =
            new Registry<>(MissingPathFeatureProvider::new);
    public static final LockRegistry LOCK_REGISTRY = new LockRegistry();
}
