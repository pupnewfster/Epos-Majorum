package com.teamacronymcoders.eposmajorum.api.feat;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.Event;

import java.util.Objects;
import java.util.function.Consumer;

public class FeatEventHandler<T extends Event> {
    public final ResourceLocation featId;
    public final Consumer<T> eventHandler;
    public final Class<T> eventClass;

    public FeatEventHandler(ResourceLocation featId, Consumer<T> eventHandler, Class<T> eventClass) {
        this.featId = Objects.requireNonNull(featId);
        this.eventHandler = Objects.requireNonNull(eventHandler);
        this.eventClass = Objects.requireNonNull(eventClass);
    }
}
