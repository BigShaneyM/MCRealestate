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

    @Override
    public String toString() {
        return minX + "," + minY + "," + minZ + "," + maxX + "," + maxY + "," + maxZ + "," + worldName;
    }

    public static WorldAABB getFromString(String data) {
        String[] loc = data.split(",");
        int a = Integer.valueOf(loc[0]);
        int b = Integer.valueOf(loc[1]);
        int c = Integer.valueOf(loc[2]);
        int d = Integer.valueOf(loc[3]);
        int e = Integer.valueOf(loc[4]);
        int f = Integer.valueOf(loc[5]);

        return new WorldAABB(a, b, c, d, e, f, loc[6]);
    }
}
