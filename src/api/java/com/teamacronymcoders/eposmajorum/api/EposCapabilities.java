package com.teamacronymcoders.eposmajorum.api;

import com.teamacronymcoders.eposmajorum.api.characterstats.ICharacterStats;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class EposCapabilities {
    @CapabilityInject(ICharacterStats.class)
    public static Capability<ICharacterStats> CHARACTER_STATS;
}
