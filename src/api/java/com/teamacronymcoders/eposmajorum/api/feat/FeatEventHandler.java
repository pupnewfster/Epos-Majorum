package com.teamacronymcoders.eposmajorum.api.feat;

import com.teamacronymcoders.eposmajorum.api.characterstats.ICharacterStats;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.Event;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FeatEventHandler<T extends Event> {
    public final ResourceLocation featId;
    public final TriConsumer<T, EntityLivingBase, ICharacterStats> eventHandler;
    public final Class<T> eventClass;

    public FeatEventHandler(ResourceLocation featId, Class<T> eventClass,
                            TriConsumer<T, EntityLivingBase, ICharacterStats> eventHandler) {
        this.featId = Objects.requireNonNull(featId);
        this.eventHandler = Objects.requireNonNull(eventHandler);
        this.eventClass = Objects.requireNonNull(eventClass);
    }
}
