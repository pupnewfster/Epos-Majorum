package com.teamacronymcoders.eposmajorum.api.pathfeature;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.teamacronymcoders.eposmajorum.api.registry.IRegistryEntry;

public interface IPathFeatureProvider extends IRegistryEntry {
    IPathFeature provide(JsonObject jsonElement, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException;
}
