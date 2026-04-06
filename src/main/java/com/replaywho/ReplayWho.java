package com.replaywho;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "replaywho", version = "1.0", clientSideOnly = true, acceptedMinecraftVersions = "1.8.9")
public class ReplayWho {

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandReplayWho());
        System.out.println("[ReplayWho] Mod loaded - /replaywho ready");
    }
}