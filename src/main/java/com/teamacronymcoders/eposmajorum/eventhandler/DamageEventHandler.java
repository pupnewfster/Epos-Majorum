package com.teamacronymcoders.eposmajorum.eventhandler;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.EposCapabilities;
import com.teamacronymcoders.eposmajorum.api.characterstats.ICharacterStats;
import com.teamacronymcoders.eposmajorum.api.event.AltLivingDamageEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = EposAPI.ID)
public class DamageEventHandler {

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onAttackedEntity(LivingDamageEvent livingDamageEvent) {
        if (livingDamageEvent.getSource().getTrueSource() instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) livingDamageEvent.getSource().getTrueSource();
            LazyOptional<ICharacterStats> stats = entityLivingBase
                    .getCapability(EposCapabilities.CHARACTER_STATS);
            stats.ifPresent(iCharacterStats -> iCharacterStats.getFeats()
                    .handleEvent(new AltLivingDamageEvent(livingDamageEvent), entityLivingBase, iCharacterStats));
        }
    }
}
