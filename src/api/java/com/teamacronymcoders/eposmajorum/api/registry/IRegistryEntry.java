package com.teamacronymcoders.eposmajorum.api.registry;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public interface IRegistryEntry {
    @Nonnull
    ResourceLocation getRegistryName();
}
