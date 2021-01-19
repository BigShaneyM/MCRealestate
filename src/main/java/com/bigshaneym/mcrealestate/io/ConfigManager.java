package com.bigshaneym.mcrealestate.io;

import com.bigshaneym.mcrealestate.Core;
import com.bigshaneym.mcrealestate.housing.House;
import com.bigshaneym.mcrealestate.housing.HouseConfigVars;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager extends FileManager {

    public ConfigManager() {
        super(Core.getHousingPlugin(), "config.yml");
    }

    public void setDefaultConfigData() {
        FileConfiguration cfg = this.getFileConfig();
        cfg.set("HouseConfiguration.sell_price_at_value_percentage", 0.5);
        cfg.set("HouseConfiguration.single_owner_guest_limit", 5);
        cfg.set("HouseConfiguration.max_owners_multi_house", 3);
        cfg.set("HouseConfiguration.rented_house_require_no_show_termination", true);
        cfg.set("HouseConfiguration.rented_house_no_show_termination_delay_ms", 604800000);
        cfg.set("HouseConfiguration.SQL.Use_mySQL_Database", false);
        cfg.set("HouseConfiguration.SQL.connection_port", 8081);
        cfg.set("HouseConfiguration.SQL.connection_ip", "192.168.1.105");
        cfg.set("HouseConfiguration.SQL.max_wait_response_seconds", 2);
    }

    public void loadConfig() {
        FileConfiguration cfg = this.getFileConfig();
        double decay_price = cfg.getDouble("HouseConfiguration.sell_price_at_value_percentage");
        HouseConfigVars.setSellPriceValueDecay(decay_price);

        int guest_limit = cfg.getInt("HouseConfiguration.single_owner_guest_limit");
        HouseConfigVars.setSingleOwnerGuestLimit(guest_limit);

        int multi_owner_limit = cfg.getInt("HouseConfiguration.max_owners_multi_house");
        HouseConfigVars.setMultiOwnerToHouseLimit(multi_owner_limit);

        boolean terminate_rentals = cfg.getBoolean("HouseConfiguration.rented_house_require_no_show_termination");
        HouseConfigVars.setShouldTerminateRental(terminate_rentals);

        long termination_time = cfg.getLong("HouseConfiguration.rented_house_no_show_termination_delay_ms");
        HouseConfigVars.setRentalNoShowLimitMs(termination_time);

        boolean usingSQL = cfg.getBoolean("HouseConfiguration.SQL.Use_mySQL_Database");
        if (usingSQL) {
            HouseConfigVars.setUseSql(usingSQL);
            //TODO SQL INFO SET HERE
        }
    }

}
