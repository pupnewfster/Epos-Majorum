package com.teamacronymcoders.eposmajorum.api.feat;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class Feat implements IFeat {
    private final List<FeatEventHandler> eventHandlers;
    private final ResourceLocation registryName;

    public Feat(ResourceLocation registryName, List<FeatEventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
        this.registryName = registryName;
    }

    public <T extends Event> Feat(ResourceLocation registryName, Consumer<T> event, Class<T> tClass) {
        this(registryName, Lists.newArrayList(new FeatEventHandler<>(registryName, event, tClass)));
    }

    @Override
    public List<FeatEventHandler> getEventHandlers() {
        return eventHandlers;
    }

    @Override
    @Nonnull
    public ResourceLocation getRegistryName() {
        return registryName;
    }
}
