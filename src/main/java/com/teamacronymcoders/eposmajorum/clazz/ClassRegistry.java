package com.teamacronymcoders.eposmajorum.clazz;

import net.minecraft.resources.IResourceManager;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

public class ClassRegistry implements ISelectiveResourceReloadListener {
    @Override
    @ParametersAreNonnullByDefault
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {

    }

    @Override
    public IResourceType getResourceType() {
        return ClassResourceType.INSTANCE;
    }
}
