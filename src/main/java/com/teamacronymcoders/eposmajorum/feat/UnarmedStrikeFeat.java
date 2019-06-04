package com.teamacronymcoders.eposmajorum.feat;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.event.AltLivingDamageEvent;
import com.teamacronymcoders.eposmajorum.api.feat.Feat;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;

public class UnarmedStrikeFeat extends Feat {
    public UnarmedStrikeFeat() {
        super(new ResourceLocation(EposAPI.ID, "unarmed_strike"),
                livingDamageEvent -> {
                    if (livingDamageEvent.getSource() instanceof EntityDamageSource &&
                            livingDamageEvent.getSource().getTrueSource() instanceof EntityLiving) {
                        if (((EntityLiving)livingDamageEvent.getSource().getTrueSource())
                                .getActiveItemStack().isEmpty()) {
                            livingDamageEvent.setAmount(livingDamageEvent.getAmount() * 1.25F);
                        }
                    }
                }, AltLivingDamageEvent.class);
    }
}
