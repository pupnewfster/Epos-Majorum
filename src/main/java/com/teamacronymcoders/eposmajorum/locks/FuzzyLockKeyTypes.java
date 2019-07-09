package com.teamacronymcoders.eposmajorum.locks;

import com.teamacronymcoders.eposmajorum.api.locks.IGenericFuzzyLockType;

public enum FuzzyLockKeyTypes implements IGenericFuzzyLockType {
    ARMOR,
    ARMOR_TOUGHNESS,
    ATTACK_DAMAGE,
    BLOCK_HARVEST,
    GENERIC_NBT,
    HUNGER,
    SATURATION,
    TOOL_HARVEST
}