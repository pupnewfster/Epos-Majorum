package com.teamacronymcoders.eposmajorum.api.feat;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;
import java.util.function.Consumer;

public class Feat implements IFeat {
    private final List<FeatEventHandler> eventHandlers;
    private final ResourceLocation name;

    public Feat(ResourceLocation name, List<FeatEventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
        this.name = name;
    }

    public <T extends Event> Feat(ResourceLocation name, Consumer<T> event, Class<T> tClass) {
        this(name, Lists.newArrayList(new FeatEventHandler<>(name, event, tClass)));
    }

    @Override
    public List<FeatEventHandler> getEventHandlers() {
        return eventHandlers;
    }

    @Override
    public ResourceLocation getName() {
        return name;
    }
}
