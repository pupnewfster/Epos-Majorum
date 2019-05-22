package com.teamacronymcoders.eposmajorum.api.clazz;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.EntityLivingBase;

public class ClassLevels {
    private int currentLevel;
    private final Int2ObjectMap<IClass> classLevels;

    public ClassLevels() {
        classLevels = new Int2ObjectOpenHashMap<>();
    }

    public boolean addClassLevel(EntityLivingBase entityLivingBase, IClass iClass) {
        boolean addedClass = false;
        int currentCheck = 0;
        do {
            currentCheck++;
            if (classLevels.get(currentCheck) == null) {
                classLevels.put(currentCheck, iClass);
                addedClass = true;
            }
        } while (!addedClass && currentCheck < currentLevel);

        int newClassTotal = 0;
        if (addedClass) {
            for (Int2ObjectMap.Entry<IClass> entry: classLevels.int2ObjectEntrySet()) {
                if (entry.getValue() == iClass) {
                    newClassTotal++;
                }
            }
        }
        iClass.addLevel(entityLivingBase, newClassTotal);
        return addedClass;
    }
}
