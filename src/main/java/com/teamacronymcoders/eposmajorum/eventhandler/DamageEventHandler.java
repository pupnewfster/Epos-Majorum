package com.teamacronymcoders.eposmajorum.eventhandler;

import com.teamacronymcoders.eposmajorum.EposMajorum;
import com.teamacronymcoders.eposmajorum.api.EposCapabilities;
import com.teamacronymcoders.eposmajorum.api.characterstats.ICharacterStats;
import com.teamacronymcoders.eposmajorum.api.event.AltLivingDamageEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = EposMajorum.ID)
public class DamageEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onAttackedEntity(LivingDamageEvent livingDamageEvent) {
        if (livingDamageEvent.getSource().getTrueSource() != null) {
            LazyOptional<ICharacterStats> stats = livingDamageEvent.getSource().getTrueSource()
                    .getCapability(EposCapabilities.CHARACTER_STATS);
            stats.ifPresent(iCharacterStats -> iCharacterStats.getFeats()
                    .handleEvent(new AltLivingDamageEvent(livingDamageEvent)));
        }
    }
}
