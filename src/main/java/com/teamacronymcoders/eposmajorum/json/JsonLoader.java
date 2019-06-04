package com.teamacronymcoders.eposmajorum.json;

import net.minecraft.resources.IResourceManager;
import net.minecraftforge.resource.IResourceType;
import net.minecraftforge.resource.ISelectiveResourceReloadListener;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Predicate;

@ParametersAreNonnullByDefault
public class JsonLoader implements ISelectiveResourceReloadListener {
    private final IResourceType resourceType;

    public JsonLoader(IResourceType resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager, Predicate<IResourceType> resourcePredicate) {
        if (resourcePredicate.test(this.getResourceType())) {

        }
    }

    @Nullable
    @Override
    public IResourceType getResourceType() {
         return resourceType;
    }
}
