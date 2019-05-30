package com.teamacronymcoders.eposmajorum.path;

import net.minecraft.resources.IResourceManager;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

public class PathRegistry implements ISelectiveResourceReloadListener {
    @Override
    @ParametersAreNonnullByDefault
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {

    }

    @Override
    public IResourceType getResourceType() {
        return PathResourceType.INSTANCE;
    }
}
