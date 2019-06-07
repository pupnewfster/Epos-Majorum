package com.teamacronymcoders.eposmajorum.api.path;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class PathLevels implements INBTSerializable<NBTTagCompound> {
    private int currentLevel;
    private final Int2ObjectMap<IPath> pathLevels;

    public PathLevels() {
        pathLevels = new Int2ObjectOpenHashMap<>();
    }

    public boolean levelUp(EntityLivingBase entityLivingBase, IPath iPath) {
        boolean addedPath = false;
        int currentCheck = 0;
        do {
            currentCheck++;
            if (pathLevels.get(currentCheck) == null) {
                pathLevels.put(currentCheck, iPath);
                addedPath = true;
            }
        } while (!addedPath && currentCheck < currentLevel);

        int newClassTotal = 0;
        if (addedPath) {
            for (Int2ObjectMap.Entry<IPath> entry: pathLevels.int2ObjectEntrySet()) {
                if (entry.getValue() == iPath) {
                    newClassTotal++;
                }
            }
        }
        iPath.addLevel(entityLivingBase, newClassTotal);
        return addedPath;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        for (Int2ObjectMap.Entry<IPath> entry : pathLevels.int2ObjectEntrySet()) {
            nbt.putString(Integer.toString(entry.getIntKey()), entry.getValue().getRegistryName().toString());
        }
        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        for (String key: nbt.keySet()) {
            pathLevels.put(Integer.parseInt(key), EposAPI.PATH_REGISTRY.getEntry(nbt.getString(key)));
        }
    }
}
