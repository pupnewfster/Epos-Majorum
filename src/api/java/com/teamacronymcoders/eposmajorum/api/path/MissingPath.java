package com.teamacronymcoders.eposmajorum.api.path;

import com.teamacronymcoders.eposmajorum.api.pathfeature.PathFeatures;
import com.teamacronymcoders.eposmajorum.api.registry.MissingRegistryEntry;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class MissingPath extends MissingRegistryEntry<IPath> implements IPath {
    public MissingPath(String registryName) {
        super(new ResourceLocation(registryName), "path");
    }

    @Override
    public PathFeatures getPathFeatures() {
        return new PathFeatures(0, new Int2ObjectOpenHashMap<>());
    }

    @Override
    public void addLevel(EntityLivingBase living, int newClassLevel) {

    }

    @Override
    public void removeLevel(EntityLivingBase living, int newClassLevel) {

    }
}
