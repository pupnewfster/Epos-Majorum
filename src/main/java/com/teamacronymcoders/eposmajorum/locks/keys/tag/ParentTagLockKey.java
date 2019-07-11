package com.teamacronymcoders.eposmajorum.locks.keys.tag;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.locks.keys.IParentLockKey;
import com.teamacronymcoders.eposmajorum.api.requirements.IRequirement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

//TODO: Javadoc, this is basically a wrapper for TagLockKey to be able to get the requirements of all the different tags on an item/block/etc
public class ParentTagLockKey implements IParentLockKey {

    @Nonnull
    private final Collection<ResourceLocation> tags;
    @Nullable
    private final CompoundNBT nbt;

    public ParentTagLockKey(@Nonnull Collection<ResourceLocation> tags) {
        this(tags, null);
    }

    public ParentTagLockKey(@Nonnull Collection<ResourceLocation> tags, @Nullable CompoundNBT nbt) {
        this.tags = tags;
        this.nbt = nbt == null || nbt.isEmpty() ? null : nbt;
    }

    @Nullable
    public static ParentTagLockKey fromObject(@Nonnull Object object) {
        if (object instanceof ItemStack) {
            ItemStack stack = (ItemStack) object;
            if (stack.isEmpty()) {
                return null;
            }
            return fromItem(stack.getItem(), stack.getTag());
        } else if (object instanceof Item) {
            return fromItem((Item) object, null);
        } else if (object instanceof BlockState) {
            return fromBlock(((BlockState) object).getBlock());
        } else if (object instanceof Block) {
            return fromBlock((Block) object);
        } else if (object instanceof FluidStack) {
            return fromFluid(((FluidStack) object).getFluid());
        } else if (object instanceof Fluid) {
            return fromFluid((Fluid) object);
        }
        return null;
    }

    @Nullable
    private static ParentTagLockKey fromItem(@Nonnull Item item, @Nullable CompoundNBT nbt) {
        Collection<ResourceLocation> owningTags = ItemTags.getCollection().getOwningTags(item);
        return owningTags.isEmpty() ? null : new ParentTagLockKey(owningTags, nbt);
    }

    @Nullable
    private static ParentTagLockKey fromBlock(@Nonnull Block block) {
        Collection<ResourceLocation> owningTags = BlockTags.getCollection().getOwningTags(block);
        return owningTags.isEmpty() ? null : new ParentTagLockKey(owningTags);
    }

    @Nullable
    private static ParentTagLockKey fromFluid(@Nonnull Fluid fluid) {
        //TODO: When Forge has fluids again, uncomment/fix this
        /*Collection<ResourceLocation> owningTags = FluidTags.getCollection().getOwningTags(fluid);
        return owningTags.isEmpty() ? null : new ParentTagLockKey(owningTags);*/
        return null;
    }

    @Nonnull
    @Override
    public List<IRequirement> getSubRequirements() {
        List<IRequirement> requirements = new ArrayList<>();
        for (ResourceLocation location : tags) {
            requirements.addAll(EposAPI.LOCK_REGISTRY.getFuzzyRequirements(new TagLockKey(location, nbt)));
        }
        return requirements;
    }
}