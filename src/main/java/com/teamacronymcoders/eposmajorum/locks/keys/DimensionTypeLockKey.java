package com.teamacronymcoders.eposmajorum.locks.keys;

import com.teamacronymcoders.eposmajorum.api.locks.keys.ILockKey;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

public class DimensionTypeLockKey implements ILockKey {

    @Nonnull
    private final DimensionType dimension;

    public DimensionTypeLockKey(@Nonnull DimensionType dimension) {
        this.dimension = dimension;
    }

    @Nullable
    public static DimensionTypeLockKey fromObject(@Nonnull Object object) {
        if (object instanceof DimensionType) {
            return new DimensionTypeLockKey((DimensionType) object);
        } else if (object instanceof Dimension) {
            return new DimensionTypeLockKey(((Dimension) object).getType());
        } else if (object instanceof World) {
            return new DimensionTypeLockKey(((World) object).getDimension().getType());
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj instanceof DimensionTypeLockKey && dimension.equals(((DimensionTypeLockKey) obj).dimension);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dimension);
    }
}