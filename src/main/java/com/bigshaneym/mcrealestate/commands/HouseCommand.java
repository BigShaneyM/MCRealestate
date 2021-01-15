package com.bigshaneym.mcrealestate.commands;

import com.bigshaneym.mcrealestate.housing.HouseEditStages;
import com.bigshaneym.mcrealestate.housing.HouseEditor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HouseCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player))
            return false;

        boolean isAdmin = sender.hasPermission("house.admin");

        switch (args.length) {
            case 2:
            {
                String a = args[0].toUpperCase();
                if (a.equals("DEL") && isAdmin) {

                } else if (a.equals("VIEW") && isAdmin) {

                } else if (a.equals("ADD_OWNER")) {

                } else if (a.equals("DEL_OWNER")) {

                } else if (a.equals("EDIT") && isAdmin) {

                } else if (a.equals("BUY")) {

                } else if (a.equals("SELL")) {

                }else {
                    if (isAdmin) {
                        sender.sendMessage("/house <NEW|DEL|BUY|SELL|TP|VIEW|ADD_OWNER|DEL_OWNER|EDIT> <HOUSE-NUM/HOUSE-TYPE/PLAYER-NAME>");
                        sender.sendMessage("House Types: { House | Rental House }");
                    } else {
                        sender.sendMessage("/house <BUY|SELL|TP|ADD_OWNER|DEL_OWNER|> <PLAYER-NAME>");
                    }
                }
                break;
            }
            case 1:
            {
                String a = args[0].toUpperCase();
                if (a.equals("NEW") && isAdmin) {
                    HouseEditor.addPlayerEditor((Player)sender, HouseEditStages.START);
                    HouseEditor editor = HouseEditor.getHouseEditor(((Player)sender).getName());
                    editor.processEditor((Player)sender, "-n");
                } else if (a.equals("TP")) {
                    //FIND IF PLAYER HAS HOUSE
                    //GRAB HOUSE TP COORDS
                    //TP PLAYER TO HOUSE
                } else {
                    sender.sendMessage("/house <NEW|DEL|VIEW|ADD_OWNER|DEL_OWNER|EDIT> <HOUSE-NUM/PLAYER-NAME>");
                }
                break;
            }
            default:
                sender.sendMessage("/house <NEW|DEL|VIEW|ADD_OWNER|DEL_OWNER|EDIT> <HOUSE-NUM/PLAYER-NAME>");
                break;

        }
        return true;
    }
}
