package com.teamacronymcoders.eposmajorum.locks.keys;

import com.teamacronymcoders.eposmajorum.api.locks.ILockKey;
import com.teamacronymcoders.eposmajorum.api.locks.NBTLockKey;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class ModLockKey extends NBTLockKey {

    @Nonnull
    private final String modName;

    public ModLockKey(@Nonnull String modName) {
        this(modName, null);
    }

    public ModLockKey(@Nonnull String modName, @Nullable CompoundNBT nbt) {
        super(nbt);
        this.modName = modName.toLowerCase();
    }

    @Nullable
    public static ModLockKey fromObject(@Nonnull Object object) {
        if (object instanceof ItemStack) {
            ItemStack stack = (ItemStack) object;
            if (stack.isEmpty()) {
                return null;
            }
            return fromItem(stack.getItem(), stack.getTag());
        } else if (object instanceof Item) {
            return fromItem((Item) object, null);
        } else if (object instanceof BlockState) {
            return fromRegistryName(((BlockState) object).getBlock().getRegistryName(), null);
        } else if (object instanceof Block) {
            return fromRegistryName(((Block) object).getRegistryName(), null);
        }
        //TODO: support getting mod from fluids (let fluidstacks also give NBT)
        return null;
    }

    @Nullable
    private static ModLockKey fromItem(@Nonnull Item item, @Nullable CompoundNBT nbt) {
        return fromRegistryName(item.getRegistryName(), nbt);
    }

    @Nullable
    private static ModLockKey fromRegistryName(@Nullable ResourceLocation registryName, @Nullable CompoundNBT nbt) {
        //If the registry name is somehow null we can't instantiate a new mod lock
        // Should never happen but gets rid of the null pointer warning
        return registryName == null ? null : new ModLockKey(registryName.getNamespace(), nbt);
    }

    @Override
    @Nullable
    public ILockKey getNotFuzzy() {
        return isNotFuzzy() ? this : new ModLockKey(modName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ModLockKey) {
            ModLockKey other = (ModLockKey) o;
            return modName.equals(other.modName) && Objects.equals(nbt, other.nbt);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(modName, nbt);
    }
}