package com.bigshaneym.mcrealestate.io;

import com.bigshaneym.mcrealestate.Core;
import com.bigshaneym.mcrealestate.housing.House;
import com.bigshaneym.mcrealestate.housing.HousingTypes;
import com.bigshaneym.mcrealestate.housing.WorldAABB;
import com.bigshaneym.mcrealestate.util.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class HouseIOManager extends FileManager {

    public HouseIOManager() {
        super(Core.getHousingPlugin(), "HouseRecords.yml");
    }

    public void setDefaultConfigData() {
    }

    public void saveHouseInfo(House house) {
        FileConfiguration cfg = this.getFileConfig();
        String k = house.getHouseName();
        cfg.set(k + ".WorldAABB", house.getHouseAABB().toString());
        cfg.set(k + ".ListedPrice", house.getListedPrice());
        cfg.set(k + ".ChestAmount", house.getChestStorageAmount());
        cfg.set(k + ".HouseType", house.getType().name());
        Location l = house.getTeleport();
        int x = l.getBlockX();
        int y = l.getBlockY();
        int z = l.getBlockZ();
        cfg.set(k + ".TeleportLocation", x + "," + y + "," + z);

        System.out.println("Saving house " + k + "!");
        this.saveConfig();
    }

    public void loadHouses() {
        FileConfiguration cfg = this.getFileConfig();

        for (String key : cfg.getKeys(false)) {
            WorldAABB aabb = WorldAABB.getFromString(cfg.getString(key + ".WorldAABB"));
            int listedPrice = cfg.getInt(key + ".ListedPrice");
            HousingTypes type = HousingTypes.valueOf(cfg.getString(key + ".HouseType"));
            Set<String> ownerUUIDs;
            Map<String, Set<String>> guestUUIDs;

            List<String> ownerList = cfg.getStringList(key + ".OwnerUUIDS");
            if (ownerList != null) {
                ownerUUIDs = new HashSet<String>();
                for (String o : ownerList) {
                    ownerUUIDs.add(o);
                }
            }

            ConfigurationSection cs = cfg.getConfigurationSection(key + ".GuestUUIDs");
            if (cs != null) {
                guestUUIDs = new HashMap<String, Set<String>>();
                for (String ownerUUID : cs.getKeys(false)) {
                    List<String> guestList = cfg.getStringList(ownerUUID);
                    guestUUIDs.put(ownerUUID, Utilities.getStringSetFromStringList(guestList));
                }
            }

            int chestAmount = cfg.getInt(key + ".ChestAmount");

            Location teleportLoc = Utilities.fromString(Bukkit.getWorld(aabb.getWorldName()), cfg.getString(key + ".TeleportLocation"));

        }

    }

}
