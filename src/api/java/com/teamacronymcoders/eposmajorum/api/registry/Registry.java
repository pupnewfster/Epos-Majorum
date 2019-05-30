package com.teamacronymcoders.eposmajorum.api.registry;

import com.google.common.collect.Maps;
import net.minecraft.util.ResourceLocation;

import java.util.Map;

public class Registry<T extends IRegistryEntry> {
    private final Map<String, T> entries;

    public Registry() {
        entries = Maps.newHashMap();
    }

    public void addEntry(T entry) {
        this.entries.put(entry.getRegistryName().toString(), entry);
    }

    public T getEntry(String string) {
        return entries.get(string);
    }

    public T getEntry(ResourceLocation resourceLocation) {
        return entries.get(resourceLocation.toString());
    }
}
