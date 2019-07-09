package com.teamacronymcoders.eposmajorum.locks.keys;

import com.teamacronymcoders.eposmajorum.api.locks.keys.GenericLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.keys.IFuzzyLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.keys.ILockKey;
import com.teamacronymcoders.eposmajorum.locks.FuzzyLockKeyTypes;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HungerLockKey implements IFuzzyLockKey {

    private final int hunger;

    public HungerLockKey(int hunger) {
        if (hunger < 0) {
            //TODO: Add whatever information is needed here for a better error message
            throw new IllegalArgumentException();
        }
        this.hunger = hunger;
    }

    @Nullable
    public static HungerLockKey fromObject(@Nonnull Object object) {
        if (object instanceof ItemStack) {
            ItemStack stack = (ItemStack) object;
            if (stack.isEmpty()) {
                return null;
            }
            return fromItem(stack.getItem());
        } else if (object instanceof Item) {
            return fromItem((Item) object);
        } else if (object instanceof Food) {
            return fromFood((Food) object);
        }
        return null;
    }

    @Nullable
    private static HungerLockKey fromItem(@Nonnull Item item) {
        return item.isFood() ? fromFood(item.getFood()) : null;
    }

    @Nullable
    private static HungerLockKey fromFood(@Nullable Food food) {
        return food == null ? null : new HungerLockKey(food.getHealing());
    }

    @Override
    public boolean fuzzyEquals(@Nonnull IFuzzyLockKey o) {
        return o == this || o instanceof HungerLockKey && hunger >= ((HungerLockKey) o).hunger;
    }

    @Override
    public boolean isNotFuzzy() {
        return false;
    }

    @Override
    @Nonnull
    public ILockKey getNotFuzzy() {
        return new GenericLockKey(FuzzyLockKeyTypes.HUNGER);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hunger);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof HungerLockKey && hunger == ((HungerLockKey) o).hunger;
    }
}