package com.teamacronymcoders.eposmajorum.eventhandler;

import com.google.common.collect.Lists;
import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.feat.IFeat;
import com.teamacronymcoders.eposmajorum.api.path.IPath;
import com.teamacronymcoders.eposmajorum.api.pathfeature.IPathFeatureProvider;
import com.teamacronymcoders.eposmajorum.api.registry.RegistryEvent;
import com.teamacronymcoders.eposmajorum.content.*;
import com.teamacronymcoders.eposmajorum.pathfeature.feat.FeatFeatureProvider;
import com.teamacronymcoders.eposmajorum.pathfeature.skillxp.SkillXPFeatureProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = EposAPI.ID)
public class RegistrationEventHandler {
    @SubscribeEvent
    public static void registerFeats(RegistryEvent<IFeat> featRegistryEvent) {
        featRegistryEvent.register(Lists.newArrayList(
                UnarmedStrike.FEAT,
                SpiritOfBattle.FEAT,
                LuckyLure.FEAT,
                EnderResistance.FEAT,
                ObsidianSmasher.FEAT
        ));
    }

    @SubscribeEvent
    public static void registerPathFeatureProviders(
            RegistryEvent<IPathFeatureProvider> pathFeatureProviderRegistryEvent) {
        pathFeatureProviderRegistryEvent.register(Lists.newArrayList(
                new FeatFeatureProvider(),
                new SkillXPFeatureProvider()
        ));
    }
}
