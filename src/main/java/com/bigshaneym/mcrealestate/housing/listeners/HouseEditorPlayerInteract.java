package com.bigshaneym.mcrealestate.housing.listeners;

import com.bigshaneym.mcrealestate.housing.HouseEditStages;
import com.bigshaneym.mcrealestate.housing.HouseEditor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class HouseEditorPlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (HouseEditor.isEditing(event.getPlayer().getName())) {
            HouseEditor editor = HouseEditor.getHouseEditor(event.getPlayer().getName());
            if (editor.getEditorStage() == HouseEditStages.CHOOSE_HOUSE_BOUNDS) {

            }
        }
    }
}
