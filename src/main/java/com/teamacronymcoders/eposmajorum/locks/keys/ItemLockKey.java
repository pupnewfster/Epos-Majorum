package com.teamacronymcoders.eposmajorum.locks.keys;

import com.teamacronymcoders.eposmajorum.api.locks.ILockKey;
import com.teamacronymcoders.eposmajorum.api.locks.NBTLockKey;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemLockKey extends NBTLockKey {

    @Nonnull
    private Item item;

    public ItemLockKey(@Nonnull Item item) {
        this(item, null);
    }

    public ItemLockKey(@Nonnull Item item, @Nullable CompoundNBT nbt) {
        super(nbt);
        this.item = item;
    }

    @Nullable
    public static ItemLockKey fromItemStack(@Nonnull ItemStack stack) {
        return stack.isEmpty() ? null : new ItemLockKey(stack.getItem(), stack.getTag());

        //TODO: Add case for block and for blockstate (or maybe just add a BlockStateLockKey)
    }

    @Override
    @Nullable
    public ILockKey getNotFuzzy() {
        return isNotFuzzy() ? this : new ItemLockKey(item);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ItemLockKey)) {
            return false;
        }
        ItemLockKey other = (ItemLockKey) o;
        return item == other.item && Objects.equals(nbt, other.nbt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, nbt);
    }
}