package com.teamacronymcoders.eposmajorum.locks.keys;

import com.google.common.collect.Multimap;
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
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AttackDamageLockKey implements IFuzzyLockKey {

    private final double attackDamage;

    public AttackDamageLockKey(double attackDamage) {
        if (attackDamage < 0) {
            //TODO: Add whatever information is needed here for a better error message
            throw new IllegalArgumentException();
        }
        this.attackDamage = attackDamage;
    }

    @Nullable
    public static AttackDamageLockKey fromItemStack(@Nonnull ItemStack stack) {
        if (stack.isEmpty()) {
            return null;
        }
        Item item = stack.getItem();
        //TODO: Decide if we should also check offhand
        Multimap<String, AttributeModifier> attributeModifiers = item.getAttributeModifiers(EquipmentSlotType.MAINHAND, stack);
        Collection<AttributeModifier> damage = attributeModifiers.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
        /*
        //TODO: This is for compat with TConstruct's ranged weapons probably can just be deleted or something
        if (damage.isEmpty() && TinkersCompatHandler.ENABLED) {//For ranged tinker's weapons like the shuriken
            if (item instanceof slimeknights.tconstruct.library.tools.ranged.IProjectile) {
                attributeModifiers = ((slimeknights.tconstruct.library.tools.ranged.IProjectile) item).getProjectileAttributeModifier(stack);
                damage = attributeModifiers.get(SharedMonsterAttributes.ATTACK_DAMAGE.getName());
            }
        }*/
        return damage.isEmpty() ? null : new AttackDamageLockKey(damage.stream().findFirst().map(AttributeModifier::getAmount).orElse(0D));
    }

    @Override
    public boolean fuzzyEquals(@Nonnull IFuzzyLockKey o) {
        return o == this || o instanceof AttackDamageLockKey && attackDamage >= ((AttackDamageLockKey) o).attackDamage;
    }

    @Override
    public boolean isNotFuzzy() {
        return false;
    }

    @Override
    @Nonnull
    public ILockKey getNotFuzzy() {
        return new GenericLockKey(FuzzyLockKeyTypes.ATTACK_DAMAGE);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof AttackDamageLockKey && attackDamage == ((AttackDamageLockKey) o).attackDamage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(attackDamage);
    }
}