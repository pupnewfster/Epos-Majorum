package com.teamacronymcoders.eposmajorum.locks;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.locks.keys.ArmorLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.ArmorToughnessLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.AttackDamageLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.DimensionTypeLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.GenericNBTLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.HungerLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.ItemLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.ModLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.SaturationLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.entity.EntityDamageKey;
import com.teamacronymcoders.eposmajorum.locks.keys.entity.EntityMountKey;
import com.teamacronymcoders.eposmajorum.locks.keys.entity.EntityTameKey;
import com.teamacronymcoders.eposmajorum.locks.keys.harvest.BlockHarvestLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.harvest.ToolHarvestLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.tag.ParentTagLockKey;
import net.minecraft.item.ItemStack;

//TODO: Rename
public class LockHelper {

    //TODO: Change name or how this happens
    public void registerDefaultKeyLookups() {
        //ItemLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(object -> object instanceof ItemStack ? ItemLockKey.fromItemStack((ItemStack) object) : null);
        //ModLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(ModLockKey::fromObject);
        //Generic lock based entirely on NBT
        EposAPI.LOCK_REGISTRY.registerLockType(GenericNBTLockKey::fromObject);

        //Ones that probably will be in an addon but for now are from CompatSkills for more test cases of seeing how the system works

        //DimensionTypeLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(DimensionTypeLockKey::fromObject);
        //EntityDamageKey
        EposAPI.LOCK_REGISTRY.registerLockType(EntityDamageKey::fromObject);
        //EntityMountKey
        EposAPI.LOCK_REGISTRY.registerLockType(EntityMountKey::fromObject);
        //EntityTameKey
        EposAPI.LOCK_REGISTRY.registerLockType(EntityTameKey::fromObject);
        //ArmorLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(object -> object instanceof ItemStack ? ArmorLockKey.fromItemStack((ItemStack) object) : null);
        //ArmorToughnessKey
        EposAPI.LOCK_REGISTRY.registerLockType(object -> object instanceof ItemStack ? ArmorToughnessLockKey.fromItemStack((ItemStack) object) : null);
        //AttackDamageLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(object -> object instanceof ItemStack ? AttackDamageLockKey.fromItemStack((ItemStack) object) : null);
        //HungerLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(HungerLockKey::fromObject);
        //SaturationLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(SaturationLockKey::fromObject);
        //BlockHarvestLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(BlockHarvestLockKey::fromObject);
        //ToolHarvestLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(object -> object instanceof ItemStack ? ToolHarvestLockKey.fromItemStack((ItemStack) object) : null);
        //ParentTagLockKey
        EposAPI.LOCK_REGISTRY.registerLockType(ParentTagLockKey::fromObject);
    }
}