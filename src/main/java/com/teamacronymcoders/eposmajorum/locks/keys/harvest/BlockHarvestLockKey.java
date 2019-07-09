package com.teamacronymcoders.eposmajorum.locks.keys.harvest;

import com.teamacronymcoders.eposmajorum.api.locks.keys.GenericLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.keys.IFuzzyLockKey;
import com.teamacronymcoders.eposmajorum.api.locks.keys.ILockKey;
import com.teamacronymcoders.eposmajorum.locks.FuzzyLockKeyTypes;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

public class BlockHarvestLockKey extends HarvestLockKey {

    public BlockHarvestLockKey(int harvestLevel) {
        super(harvestLevel);
    }

    @Nullable
    public static BlockHarvestLockKey fromObject(@Nonnull Object object) {
        if (object instanceof ItemStack) {
            ItemStack stack = (ItemStack) object;
            if (stack.isEmpty()) {
                return null;
            }
            Block block = Block.getBlockFromItem(stack.getItem());
            return block == Blocks.AIR ? null : new BlockHarvestLockKey(block.getHarvestLevel(block.getDefaultState()));
        } else if (object instanceof BlockState) {
            return new BlockHarvestLockKey(((BlockState) object).getHarvestLevel());
        } else if (object instanceof Block) {
            Block block = (Block) object;
            return new BlockHarvestLockKey(block.getHarvestLevel(block.getDefaultState()));
        }
        return null;
    }

    @Override
    public boolean fuzzyEquals(@Nonnull IFuzzyLockKey o) {
        return o == this || o instanceof BlockHarvestLockKey && harvestLevel >= ((BlockHarvestLockKey) o).harvestLevel;
    }

    @Override
    @Nonnull
    public ILockKey getNotFuzzy() {
        return new GenericLockKey(FuzzyLockKeyTypes.BLOCK_HARVEST);
    }

    @Override
    public boolean equals(Object o) {
        return o == this || o instanceof BlockHarvestLockKey && harvestLevel == ((BlockHarvestLockKey) o).harvestLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(harvestLevel);
    }
}