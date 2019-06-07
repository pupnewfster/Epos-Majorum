package com.teamacronymcoders.eposmajorum.pathfeature.feat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.pathfeature.IPathFeature;
import com.teamacronymcoders.eposmajorum.api.pathfeature.IPathFeatureProvider;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class FeatFeatureProvider implements IPathFeatureProvider {
    private final ResourceLocation registryName = new ResourceLocation(EposAPI.ID, "feat");

    @Override
    public IPathFeature provide(JsonObject jsonElement, JsonDeserializationContext jsonDeserializationContext) {

        return null;
    }

    @Nonnull
    @Override
    public ResourceLocation getRegistryName() {
        return this.registryName;
    }
}
