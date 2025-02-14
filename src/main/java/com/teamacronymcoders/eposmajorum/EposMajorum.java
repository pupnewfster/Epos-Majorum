package com.teamacronymcoders.eposmajorum;

import com.teamacronymcoders.eposmajorum.api.EposAPI;
import com.teamacronymcoders.eposmajorum.api.EposResourceType;
import com.teamacronymcoders.eposmajorum.api.characterstats.ICharacterStats;
import com.teamacronymcoders.eposmajorum.api.feat.IFeat;
import com.teamacronymcoders.eposmajorum.api.path.IPath;
import com.teamacronymcoders.eposmajorum.api.registry.RegistryEvent;
import com.teamacronymcoders.eposmajorum.api.skill.ISkill;
import com.teamacronymcoders.eposmajorum.characterstats.CharacterStats;
import com.teamacronymcoders.eposmajorum.json.JsonLoader;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

import static com.teamacronymcoders.eposmajorum.api.EposAPI.ID;
import static com.teamacronymcoders.eposmajorum.api.EposAPI.PATH_REGISTRY;

@Mod(value = ID)
public class EposMajorum {
    public static final Logger LOGGER = LogManager.getLogger(ID);
    private final JsonLoader<IPath> pathLoader = new JsonLoader<>("path", EposResourceType.PATH, IPath.class, PATH_REGISTRY);

    public EposMajorum() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverStart);
    }

    @SuppressWarnings("unused")
    private void setup(FMLCommonSetupEvent event) {
        CapabilityManager.INSTANCE.register(ICharacterStats.class, new Capability.IStorage<ICharacterStats>() {
            @Nullable
            @Override
            public INBT writeNBT(Capability<ICharacterStats> capability, ICharacterStats instance, Direction side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<ICharacterStats> capability, ICharacterStats instance, Direction side, INBT nbt) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }, CharacterStats::new);

        DeferredWorkQueue.runLater(() -> {
            MinecraftForge.EVENT_BUS.post(new RegistryEvent<>(ISkill.class, EposAPI.SKILL_REGISTRY));
            MinecraftForge.EVENT_BUS.post(new RegistryEvent<>(IFeat.class, EposAPI.FEAT_REGISTRY));
        });
    }

    private void serverStart(FMLServerAboutToStartEvent event) {
        event.getServer().getResourceManager().func_219534_a(pathLoader);
    }
}
