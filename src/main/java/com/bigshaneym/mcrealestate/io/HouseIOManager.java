package com.bigshaneym.mcrealestate.io;

import com.bigshaneym.mcrealestate.Core;
import com.bigshaneym.mcrealestate.housing.House;
import com.bigshaneym.mcrealestate.housing.WorldAABB;
import org.bukkit.configuration.file.FileConfiguration;

public class HouseIOManager extends FileManager {

    private House house;
    public HouseIOManager(House house) {
        super(Core.getHousingPlugin(), house.getHouseName(), "HouseRecords");
        this.house = house;
    }

    public void setDefaultConfigData() {
        FileConfiguration cfg = this.getFileConfig();

        WorldAABB aabb = this.house.getHouseAABB();
        cfg.set("WorldAABB", aabb.getMinX() + "," + aabb.getMinY() + "," + aabb.getMinZ() + ":" + aabb.getMaxX() + "," + aabb.getMaxY() + "," + aabb.getMinZ());
        cfg.set("ListedPrice", house.getListedPrice());
        cfg.set("TotalChestAmount", house.getChestStorageAmount());
        cfg.set("HouseType", house.getType());
        cfg.set("HouseName", house.getHouseName());
    }
}
