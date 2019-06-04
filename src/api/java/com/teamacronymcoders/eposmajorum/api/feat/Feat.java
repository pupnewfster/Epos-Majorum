package com.teamacronymcoders.eposmajorum.api.feat;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.Consumer;

public class Feat implements IFeat {
    private final List<FeatEventHandler> eventHandlers;
    private final ResourceLocation registryName;
    private final ITextComponent name;
    private final ITextComponent description;

    public Feat(ResourceLocation registryName, ITextComponent name, ITextComponent description,
                List<FeatEventHandler> eventHandlers) {
        this.name = name;
        this.description = description;
        this.eventHandlers = eventHandlers;
        this.registryName = registryName;
    }

    public <T extends Event> Feat(ResourceLocation registryName, Consumer<T> event, Class<T> tClass) {
        this(registryName, nameToComponent(registryName, "name"), nameToComponent(registryName, "description"),
                Lists.newArrayList(new FeatEventHandler<>(registryName, event, tClass)));
    }

    private static ITextComponent nameToComponent(ResourceLocation registryName, String append) {
        return new TextComponentTranslation("feat." + registryName.getNamespace() +
                "." + registryName.getPath() + "." + append);
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

    @Override
    public ITextComponent getName() {
        return this.name;
    }

    @Override
    public ITextComponent getDescription() {
        return this.description;
    }
}
