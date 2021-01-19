package com.bigshaneym.mcrealestate.housing;

public class WorldAABB {

    private int minX, minY, minZ;
    private int maxX, maxY, maxZ;
    private String worldName;

    public WorldAABB(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, String worldName) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        this.worldName = worldName;
    }

    public int getMinX() {
        return minX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMinZ() {
        return minZ;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getMaxZ() {
        return maxZ;
    }

    public String getWorldName() {
        return worldName;
    }
}
