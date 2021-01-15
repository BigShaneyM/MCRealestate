package com.bigshaneym.mcrealestate.housing.listeners;

import com.bigshaneym.mcrealestate.Core;
import com.bigshaneym.mcrealestate.housing.HouseEditor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class HouseEditorChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String player_name = event.getPlayer().getName();
        final String message = event.getMessage();
        if (HouseEditor.isEditing(player_name)) {
            event.setCancelled(true);
            Bukkit.getServer().getScheduler().runTaskLater(Core.getHousingPlugin(), new Runnable() {
                public void run() {
                    HouseEditor houseEditor = HouseEditor.getHouseEditor(player_name);
                    houseEditor.processEditor(player, message);
                }
            }, 1L);

        }
    }

}
