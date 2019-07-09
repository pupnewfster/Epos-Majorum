package com.teamacronymcoders.eposmajorum.locks.keys;

import com.teamacronymcoders.eposmajorum.api.locks.GenericLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.IFuzzyLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.ILockKey;
import com.teamacronymcoders.eposmajorum.locks.FuzzyLockKeyTypes;
import java.util.Collection;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ArmorLockKey implements IFuzzyLockKey {

    private final double armor;

    public ArmorLockKey(double armor) {
        if (armor < 0) {
            //TODO: Add whatever information is needed here for a better error message
            throw new IllegalArgumentException();
        }
        this.armor = armor;
    }

    @Nullable
    public static ArmorLockKey fromItemStack(@Nonnull ItemStack stack) {
        if (stack.isEmpty()) {
            return null;
        }
        Item item = stack.getItem();
        if (item instanceof ArmorItem) {
            ArmorItem armorItem = (ArmorItem) item;
            double armor = armorItem.getDamageReduceAmount();
            Collection<AttributeModifier> protection = armorItem.getAttributeModifiers(armorItem.getEquipmentSlot(), stack).get(SharedMonsterAttributes.ARMOR.getName());
            if (!protection.isEmpty()) {
                armor += protection.stream().findFirst().map(AttributeModifier::getAmount).orElse(0D);
            }
            return new ArmorLockKey(armor);
        }
        return null;
    }

    @Override
    public boolean fuzzyEquals(@Nonnull IFuzzyLockKey o) {
        return o == this || o instanceof ArmorLockKey && armor >= ((ArmorLockKey) o).armor;
    }

    @Override
    public boolean isNotFuzzy() {
        return false;
    }

    @Override
    @Nonnull
    public ILockKey getNotFuzzy() {
        return new GenericLockKey(FuzzyLockKeyTypes.ARMOR);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof ArmorLockKey && armor == ((ArmorLockKey) o).armor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(armor);
    }
}