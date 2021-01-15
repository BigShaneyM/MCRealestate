package com.bigshaneym.mcrealestate.io;

import com.bigshaneym.mcrealestate.Core;

public class ConfigManager extends FileManager {


    public ConfigManager() {
        super(Core.getHousingPlugin(), "config.yml");
    }

    public void setDefaultConfigData() {
        
    }
}
