package com.replaywho;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CommandReplayWho extends CommandBase {

    @Override
    public String getCommandName() {
        return "replaywho";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/replaywho";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft mc = Minecraft.getMinecraft();
        
        if (mc.getNetHandler() == null || mc.getNetHandler().getPlayerInfoMap() == null) {
            sendMessage("§b[§dM§fa§bo§d] §cNo players found in tablist!");
            return;
        }

        List<String> players = new ArrayList<>();

        for (NetworkPlayerInfo info : mc.getNetHandler().getPlayerInfoMap()) {
            String name;

            if (info.getDisplayName() != null) {
                name = info.getDisplayName().getFormattedText();
            } else {
                name = net.minecraft.scoreboard.ScorePlayerTeam.formatPlayerName(
                        info.getPlayerTeam(),
                        info.getGameProfile().getName()
                );
            }

            name = name.replaceAll("§.", "");

            if (name.contains("[") || name.contains("]")) {
                continue;
            }

            if (!name.isEmpty()) {
                players.add(name);
            }
        }
        if (players.isEmpty()) {
            sendMessage("§b[§dM§fa§bo§d] §cNo players found in tablist!");
            return;
        }

        players.sort(Comparator.comparing(String::toLowerCase));

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            sb.append(players.get(i));
            if (i < players.size() - 1) sb.append("§7, §f");
        }

        sendMessage("§a§lONLINE: §f" + sb.toString());
    }

    private void sendMessage(String text) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(text));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}