package com.bigshaneym.mcrealestate.housing.listeners;

import com.bigshaneym.mcrealestate.housing.HouseEditStages;
import com.bigshaneym.mcrealestate.housing.HouseEditor;
import com.bigshaneym.mcrealestate.util.Utilities;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class HouseEditorPlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Action a = event.getAction();
        Player player = event.getPlayer();
        if (a != Action.LEFT_CLICK_BLOCK && a != Action.RIGHT_CLICK_BLOCK)
            return;
        if (HouseEditor.isEditing(player.getName())) {
            HouseEditor editor = HouseEditor.getHouseEditor(player.getName());
            if (editor.getEditorStage() == HouseEditStages.CHOOSE_HOUSE_BOUNDS) {
                if (event.getItem() == null || event.getItem().getType() != Material.STICK)
                    return;
                Location location = event.getClickedBlock().getLocation();
                player.sendMessage("");
                player.sendMessage("");
                player.sendMessage("");
                switch (a) {
                    case LEFT_CLICK_BLOCK:
                        editor.setLocationOne(location);
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rFirst location set at " + Utilities.getLocationString(location) + "!"));
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen both locations are set, type '&cREADY&r'"));
                        break;
                    case RIGHT_CLICK_BLOCK:
                        editor.setLocationTwo(location);
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rSecond location set at " + Utilities.getLocationString(location) + "!"));
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen both locations are set, type '&cREADY&r'"));
                        break;
                }
                event.setCancelled(true);
            }
        }
    }

}
