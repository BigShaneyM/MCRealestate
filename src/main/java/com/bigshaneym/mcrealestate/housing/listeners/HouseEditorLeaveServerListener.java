package com.bigshaneym.mcrealestate.housing.listeners;

import com.bigshaneym.mcrealestate.housing.HouseEditor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class HouseEditorLeaveServerListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        HouseEditor.removeHouseEditor(player.getName());
    }
}
