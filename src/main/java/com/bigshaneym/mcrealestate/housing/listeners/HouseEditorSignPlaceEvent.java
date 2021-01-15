package com.bigshaneym.mcrealestate.housing.listeners;

import com.bigshaneym.mcrealestate.housing.HouseEditStages;
import com.bigshaneym.mcrealestate.housing.HouseEditor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class HouseEditorSignPlaceEvent implements Listener {

    @EventHandler
    public void onSignPlacement(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.OAK_WALL_SIGN)
            return;
        Player player = event.getPlayer();
        if (HouseEditor.isEditing(player.getName())) {
            HouseEditor editor = HouseEditor.getHouseEditor(player.getName());
            if (editor.getEditorStage() == HouseEditStages.PLACE_DOOR_SIGNS) {
                ItemStack is = event.getItemInHand();
                if (is == null)
                    return;
                Sign sign = (Sign)event.getBlockPlaced().getState();
                if (is.getItemMeta().getDisplayName().contains("Buy Sign")) {
                    editor.genSign(true, sign);
                } else if (is.getItemMeta().getDisplayName().contains("Sell Sign")) {
                    editor.genSign(false, sign);
                }
                return;
            }
        }

    }
}
