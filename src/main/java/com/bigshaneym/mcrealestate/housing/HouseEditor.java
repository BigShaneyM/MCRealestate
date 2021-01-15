package com.bigshaneym.mcrealestate.housing;

import com.bigshaneym.mcrealestate.Utilities;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

public class HouseEditor {

    private static HashMap<String, HouseEditor> editors_map;
    private HouseEditStages stage = HouseEditStages.START;

    private Location l0 = null;
    private Location l = null;
    private String house_name;
    private HousingTypes houseType = HousingTypes.NULL_TYPE;
    private int buy_price, sell_price;



    public boolean buy_sign_placed, sell_sign_placed;

    public HouseEditor() {
        this(HouseEditStages.START);
    }

    public HouseEditor(HouseEditStages stage) {
        this.stage = stage;
        createMap();
    }

    public static void clearEditors() {
        editors_map.clear();
    }

    public static boolean isEditing(String player_name) {
        return editors_map.containsKey(player_name);
    }

    public static void createMap() {
        if (editors_map != null)
            return;
        editors_map = new HashMap<String, HouseEditor>();
    }

    public static HouseEditor getHouseEditor(String player_name) {
        if (isEditing(player_name))
            return editors_map.get(player_name);
        return null;
    }

    public static void removeHouseEditor(String player_name) {
     if (isEditing(player_name))
         editors_map.remove(player_name);
    }

    public static void addPlayerEditor(Player player, HouseEditStages stage) {
     if (!isEditing(player.getName()))
         editors_map.put(player.getName(), new HouseEditor(stage));
    }

    private void addChatLineGap(Player player) {
        player.sendMessage("\n");
    }

    public void processEditor(Player player, String message) {
        if (message == null)
            return;
        message = message.toUpperCase();
        addChatLineGap(player);
        addChatLineGap(player);
        addChatLineGap(player);
        addChatLineGap(player);
        addChatLineGap(player);
        addChatLineGap(player);
        if (stage == HouseEditStages.START) {
            player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWelcome to the Housing Editor. To start, left click the block at the lowest corner of the house using the stick."));
            player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you have the position, please type in chat: '&cREADY&r'"));
            player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rAfter that, choose the next highest corner opposite to the first corner in a diagonal. " +
                    "Right click the block with the stick."));
            player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf you need to exit the house editor, please type '&cEXIT&r'"));
            stage = HouseEditStages.CHOOSE_HOUSE_BOUNDS;
            player.setGameMode(GameMode.CREATIVE);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 10));
            player.getInventory().clear();
            ItemStack is = new ItemStack(Material.DEBUG_STICK);
            ItemMeta is_im = is.getItemMeta();
            is_im.setDisplayName(Utilities.toColor("&cHouse Location Recorder"));
            is.setItemMeta(is_im);
            player.getInventory().addItem(is);
            return;
        }

        if (message.equals("EXIT")) {
            player.setGameMode(GameMode.SURVIVAL);
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            removeHouseEditor(player.getName());
            return;
        }

        switch (stage) {
            case CHOOSE_HOUSE_BOUNDS:
                if (message.equals("READY") && !(l == null || l0 == null)) {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rSecond location recorded!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rNow to create a name for this 'house' boundary. Please type in a name, only one word!"));
                    stage = HouseEditStages.SET_HOUSE_NAME;
                    player.getInventory().clear();
                } else {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWelcome to the Housing Editor. To start, left click the block at the lowest corner of the house using the stick."));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you have the position, please type in chat: '&cREADY&r'"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rAfter that, choose the next highest corner opposite to the first corner in a diagonal. " +
                            "Right click the block with the stick."));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf you need to exit the house editor, please type '&cEXIT&r'"));
                }
                break;
            /**case CHOOSE_FIRST_COORDS:
                if (message.equals("READY")) {
                    this.l0 = player.getLocation();
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rFirst location recorded!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rNow, choose the next highest corner opposite to the first corner in a diagonal. " +
                            "Right click the block with the stick."));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are have the position, please type in chat: '&cREADY&r'"));
                    stage = HouseEditStages.CHOOSE_SECOND_COORDS;
                } else {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWelcome to the Housing Editor. To start, left click a block at the lowest corner of the house using the stick."));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you have the position, please type in chat: '&cREADY&r'"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf you need to exit the house editor, please type '&cEXIT&r'"));
                }
                break;
            case CHOOSE_SECOND_COORDS:
                if (message.equals("READY")) {
                    this.l = player.getLocation();
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rSecond location recorded!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rNow to create a name for this 'house' boundary. Please type in a name, only one word!"));
                    stage = HouseEditStages.SET_HOUSE_NAME;
                    player.getInventory().clear();
                } else {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rChoose the next highest corner opposite to the first corner in a diagonal." +
                            "Right click the block with the stick."));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are have the position, please type in chat: '&cREADY&r'"));
                }
                break;*/
            case SET_HOUSE_NAME:
                if (house_name == null && message.equals("READY")) {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rCreate a name for this 'house' boundary. Please type in a name, only one word!"));
                } else if (message.equals("READY")) {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rYou have chosen the name of the house to be:" + house_name + "!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rNow comes chest placement. Each chest will be named after the name of the house. Please place accordingly to price and design!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rType '&cREADY&r' when you are finished with all chest placement!"));
                    stage = HouseEditStages.CHEST_PLACEMENT;
                    ItemStack house_chest = new ItemStack(Material.CHEST);
                    ItemMeta chest_meta = house_chest.getItemMeta();
                    chest_meta.setDisplayName(Utilities.toColor("&c" + house_name + " Storage"));
                    house_chest.setItemMeta(chest_meta);
                    player.getInventory().setItem(0, house_chest);

                } else if (message.contains(" ")){
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rCreate a name for this 'house' boundary. Please type in a name, only one word!"));
                } else {
                    house_name = message;
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rYou have chosen the name of the house to be:" + house_name + "!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf this is not the name you want, please type in a new name..."));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf this is the house name you do want, please type in '&cREADY&r'"));
                }
                break;
            case CHEST_PLACEMENT:
                if (message.equals("READY")) {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&r Now to place the doors of the house. Choose locations where doors will be " +
                            "placed and place the iron door facing outward."));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are done with door placement, please type in '&cREADY&r'"));
                    stage = HouseEditStages.PLACE_DOORS;
                    player.getInventory().clear();
                    ItemStack house_door = new ItemStack(Material.IRON_DOOR);
                    ItemMeta door_meta = house_door.getItemMeta();
                    door_meta.setDisplayName(Utilities.toColor("&c" + house_name + " Door"));
                    house_door.setItemMeta(door_meta);
                    player.getInventory().addItem(house_door);
                } else {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are done with chest placement, please type in '&cREADY&r'"));
                }
                break;
            case PLACE_DOORS:
                if (message.equals("READY")) {
                    stage = HouseEditStages.CHOOSE_HOUSE_TYPE;
                    player.getInventory().clear();
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rNow enter in a house type into chat."));
                    player.sendMessage(Utilities.toColor(""));
                    String toSend = "&2[MCRealEstate]:&rHouse types are {" + getHouseTypesList() + "}";
                    player.sendMessage(Utilities.toColor(toSend));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you have entered a house type, please type '&cREADY&r'"));
                } else {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are done with door placement, please type in '&cREADY&r'"));
                }
                break;
            case CHOOSE_HOUSE_TYPE:
                if (houseType == HousingTypes.NULL_TYPE && message.equals("READY")) {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&r You have to choose a valid house type!"));
                    String toSend = "&2[MCRealEstate]:&rHouse types are {" + getHouseTypesList() + "}";
                    player.sendMessage(Utilities.toColor(toSend));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are done with deciding a house type, please type in '&cREADY&r'"));
                } else if (message.equals("READY")) {
                    stage = HouseEditStages.SET_BUY_SIGNS;
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rNow please enter a number for the price of the house to buy. Take into account the number of chests within the house too!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a buying price, please type '&cREADY&r'"));

                } else {
                    if (HousingTypes.valueOf(message) == null) {
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&r You have to choose a valid house type!"));
                        String toSend = "&2[MCRealEstate]:&rHouse types are {" + getHouseTypesList() + "}";
                        player.sendMessage(Utilities.toColor(toSend));
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are done with deciding a house type, please type in '&cREADY&r'"));
                    } else {
                        houseType = HousingTypes.valueOf(message);
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&r You have chosen the house type to be " + houseType.name()));
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are done with deciding a house type, please type in '&cREADY&r'"));
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf this is incorrect, please type in a valid house type..."));
                    }
                }
                break;
            case SET_BUY_SIGNS:
                if (buy_price == 0 && message.equals("READY")) {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&cInvalid buy price number! Please enter a valid integer!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a buying price, please type '&cREADY&r'"));
                } else if (message.equals("READY")) {
                    stage = HouseEditStages.SET_SELL_SIGNS;
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rNow please enter a number for the price of the house to sell. This # should be at least 25% smaller than the buy price!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a selling price, please type '&cREADY&r'"));
                } else {
                    try {
                        buy_price = Integer.valueOf(message);
                    } catch (NumberFormatException e) {
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&cInvalid buy price number! Please enter a valid integer!"));
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a buying price, please type '&cREADY&r'"));
                        return;
                    }
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rYou have chosen the buying price of this house to be $" + buy_price));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf this is wrong, please enter another valid integer!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a buying price, please type '&cREADY&r'"));
                }
                break;
            case SET_SELL_SIGNS:
                if (sell_price == 0 && message.equals("READY")) {
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&cInvalid sell price number! Please enter a valid integer!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a selling price, please type '&cREADY&r'"));
                } else if (message.equals("READY")) {
                    stage = HouseEditStages.PLACE_DOOR_SIGNS;
                    player.getInventory().clear();
                    ItemStack buy_sign = new ItemStack(Material.OAK_WALL_SIGN);
                    ItemStack sell_sign = new ItemStack(Material.OAK_WALL_SIGN);
                    ItemMeta b_sign_meta = buy_sign.getItemMeta();
                    ItemMeta s_sign_meta = sell_sign.getItemMeta();
                    b_sign_meta.setDisplayName(Utilities.toColor("&c" + house_name + " Buy Sign"));
                    s_sign_meta.setDisplayName(Utilities.toColor("&c" + house_name + " Sell Sign"));
                    buy_sign.setItemMeta(b_sign_meta);
                    sell_sign.setItemMeta(s_sign_meta);
                    player.sendMessage("&2[MCRealEstate]:&rNow go out to the front of the house where the door is placed. Find two blocks on opposite sides of the door to place these signs.");
                    player.sendMessage("&2[MCRealEstate]:&rThe left side of the door will have the buy sign, the sell sign will be on the right.");
                    player.sendMessage("&2[MCRealEstate]:&rWhen you are done with the two sign placements, type '&cREADY&r'");
                } else {
                    try {
                        sell_price = Integer.valueOf(message);
                    } catch (NumberFormatException e) {
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&cInvalid sell price number! Please enter a valid integer!"));
                        player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a selling price, please type '&cREADY&r'"));
                        return;
                    }
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rYou have chosen the selling price of this house to be $" + sell_price));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rIf this is wrong, please enter another valid integer!"));
                    player.sendMessage(Utilities.toColor("&2[MCRealEstate]:&rWhen you are finished with a selling price, please type '&cREADY&r'"));
                }
                break;
            case PLACE_DOOR_SIGNS:
            case CHOOSE_TP_LOCATION:
            default:
                break;
        }
        addChatLineGap(player);
    }

    private static String getHouseTypesList() {
        String types = "";
        for (HousingTypes t : HousingTypes.values()) {
            types += t.name() + ", ";
        }
        types = types.substring(0, types.length() - 3);
        return types;
    }

    public HouseEditStages getEditorStage() {
        return stage;
    }

    public void setLocationOne(Location loc) {
        this.l0 = loc;
    }

    public void setLocationTwo(Location loc) {
        this.l = loc;
    }


}
