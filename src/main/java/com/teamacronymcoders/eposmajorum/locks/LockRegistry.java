package com.teamacronymcoders.eposmajorum.locks;

import com.teamacronymcoders.eposmajorum.api.locks.ILockKey;
import com.teamacronymcoders.eposmajorum.api.locks.ILockKeyCreator;
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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;

//TODO: Put this in the proper place
public class LockRegistry {

    //TODO: Properly support fuzzyness
    private List<ILockKeyCreator<? extends ILockKey>> keyCreators = new ArrayList<>();

    //TODO: Change name or how this happens
    public void registerDefaultKeyLookups() {
        //ItemLockKey
        registerLockType(object -> object instanceof ItemStack ? ItemLockKey.fromItemStack((ItemStack) object) : null);
        //ModLockKey
        registerLockType(ModLockKey::fromObject);
        //Generic lock based entirely on NBT
        registerLockType(GenericNBTLockKey::fromObject);

        //Ones that probably will be in an addon but for now are from CompatSkills for more test cases of seeing how the system works

        //DimensionTypeLockKey
        registerLockType(DimensionTypeLockKey::fromObject);
        //EntityDamageKey
        registerLockType(EntityDamageKey::fromObject);
        //EntityMountKey
        registerLockType(EntityMountKey::fromObject);
        //EntityTameKey
        registerLockType(EntityTameKey::fromObject);
        //ArmorLockKey
        registerLockType(object -> object instanceof ItemStack ? ArmorLockKey.fromItemStack((ItemStack) object) : null);
        //ArmorToughnessKey
        registerLockType(object -> object instanceof ItemStack ? ArmorToughnessLockKey.fromItemStack((ItemStack) object) : null);
        //AttackDamageLockKey
        registerLockType(object -> object instanceof ItemStack ? AttackDamageLockKey.fromItemStack((ItemStack) object) : null);
        //HungerLockKey
        registerLockType(HungerLockKey::fromObject);
        //SaturationLockKey
        registerLockType(SaturationLockKey::fromObject);
        //BlockHarvestLockKey
        registerLockType(BlockHarvestLockKey::fromObject);
        //ToolHarvestLockKey
        registerLockType(object -> object instanceof ItemStack ? ToolHarvestLockKey.fromItemStack((ItemStack) object) : null);
        //ParentTagLockKey
        registerLockType(ParentTagLockKey::fromObject);
    }

    public <KEY extends ILockKey> void registerLockType(ILockKeyCreator<KEY> creator) {
        keyCreators.add(creator);
        //Unlike the 1.12 system this has it so that each lock key has one registration for each type it can be created from
        //Each one has to be checked when getting by type rather than knowing it "should" be good for that type already
        //Does this have any negative performance impact
    }

    public void getLocks() {
        //TODO: Helper method for combining the locks of multiple same type objects (Maybe even allow of different type objects)
        // If it doesn't support different type objects should there be some optimization so it doesn't recheck all the types
    }

    public <TYPE> void getLocks(@Nonnull TYPE type) {
        for (ILockKeyCreator<? extends ILockKey> keyCreator : keyCreators) {
            ILockKey lockKey = keyCreator.createFrom(type);
            if (lockKey != null) {
                //We successfully created a lock key from our given object
            }
        }
    }
}