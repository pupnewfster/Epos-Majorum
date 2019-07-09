package com.teamacronymcoders.eposmajorum.locks;

import com.teamacronymcoders.eposmajorum.api.locks.IFuzzyLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.ILockKey;
import com.teamacronymcoders.eposmajorum.api.locks.ILockKeyCreator;
import com.teamacronymcoders.eposmajorum.api.locks.IParentLockKey;
import com.teamacronymcoders.eposmajorum.api.requirements.IRequirement;
import com.teamacronymcoders.eposmajorum.locks.keys.ArmorLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.ArmorToughnessLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.AttackDamageLockKey;
import com.teamacronymcoders.eposmajorum.locks.keys.DimensionTypeLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.GenericLockKey;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import net.minecraft.item.ItemStack;

//TODO: Put this in the proper place
public class LockRegistry {

    //TODO: Remove this, it is just temporary so that ParentTagLockKey can reference non static fields
    public static final LockRegistry INSTANCE = new LockRegistry();

    //TODO: Properly support fuzzyness
    private final List<ILockKeyCreator<? extends ILockKey>> keyCreators = new ArrayList<>();
    private final Map<ILockKey, List<IRequirement>> locks = new HashMap<>(); //This should stay private to ensure that it is added to correctly
    private final Map<ILockKey, Set<IFuzzyLockKey>> fuzzyLockInfo = new HashMap<>();
    private final List<IRequirement> EMPTY_REQUIREMENTS = Collections.emptyList();

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

    public void addLockByKey(@Nonnull ILockKey key, @Nonnull List<IRequirement> requirements) {
        if (key instanceof GenericLockKey || requirements.isEmpty()) {
            //Don't allow having locks on the generic key that is just for when there isn't enough information about a fuzzy lock key
            return;
        }
        locks.put(key, requirements);

        if (key instanceof IFuzzyLockKey) {
            IFuzzyLockKey fuzzy = (IFuzzyLockKey) key;
            if (!fuzzy.isNotFuzzy()) {
                //Store the fuzzy instance in a list for the specific item
                fuzzyLockInfo.computeIfAbsent(fuzzy.getNotFuzzy(), k -> new HashSet<>()).add(fuzzy);
            }
        }
    }

    public List<IRequirement> getRequirementsByKey(ILockKey key) {
        if (key instanceof IFuzzyLockKey) {
            return getFuzzyRequirements((IFuzzyLockKey) key);
        }
        return locks.getOrDefault(key, EMPTY_REQUIREMENTS);
    }

    public List<IRequirement> getFuzzyRequirements(IFuzzyLockKey key) {
        List<IRequirement> requirements = new ArrayList<>();
        if (!key.isNotFuzzy()) {
            ILockKey baseLock = key.getNotFuzzy();
            if (locks.containsKey(baseLock)) {
                //Add the base lock's requirements
                requirements.addAll(locks.get(baseLock));
            }
            Set<IFuzzyLockKey> fuzzyLookup = fuzzyLockInfo.get(baseLock);
            if (fuzzyLookup != null) {
                for (IFuzzyLockKey fuzzyLock : fuzzyLookup) {
                    if (key.fuzzyEquals(fuzzyLock) && locks.containsKey(fuzzyLock)) { //Build up the best match
                        //fuzzy is the given object and has all info and fuzzyLock is the partial information
                        requirements.addAll(locks.get(fuzzyLock));
                    }
                }
            }
        } else if (locks.containsKey(key)) {
            requirements.addAll(locks.get(key));
        }
        return requirements;
    }

    public <TYPE> List<IRequirement> getLocks(@Nonnull TYPE type) {
        List<IRequirement> requirements = new ArrayList<>();
        for (ILockKeyCreator<? extends ILockKey> keyCreator : keyCreators) {
            ILockKey lockKey = keyCreator.createFrom(type);
            if (lockKey == null) {
                //We failed to create a lock key with our object, continue to next type of lock key
                continue;
            }
            if (lockKey instanceof IFuzzyLockKey) {
                requirements.addAll(getFuzzyRequirements((IFuzzyLockKey) lockKey));
            } else if (locks.containsKey(lockKey)) {
                requirements.addAll(locks.get(lockKey));
            }
            if (lockKey instanceof IParentLockKey) {
                //Add all the sub requirements the lock key may have
                // (if there is one that matches the key itself it will have been caught above)
                requirements.addAll(((IParentLockKey) lockKey).getSubRequirements());
            }
        }
        return requirements;
    }
}