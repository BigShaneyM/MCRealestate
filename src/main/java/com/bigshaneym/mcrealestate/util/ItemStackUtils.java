package com.bigshaneym.mcrealestate.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public class ItemStackUtils {

    private static ItemStackUtils instance;
    private Inventory inventory;
    private String serializedContents;

    private ItemStackUtils() {
        inventory = Bukkit.createInventory(null, 54);
        inventory.addItem(new ItemStack(Material.ACACIA_LOG));
    }

    public static ItemStackUtils getInstance() {
        if (instance == null) {
            instance = new ItemStackUtils();
        }
        return instance;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void saveInventory(Inventory inventory) {
        serializedContents = itemStackArrayToBase64(inventory.getContents());
    }

    public Inventory loadInventory() {
        if (serializedContents == null)
            return getInventory();
        this.inventory.setContents(itemStackArrayFromBase64(serializedContents));
        return getInventory();
    }


    /**public String serializeItemStack(ItemStack itemStack) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        try {
            Object nbtTagCompoundObj = getNbtTagCompoundClass().getConstructor().newInstance();
            Object nmsItemStackObj = getAsNMSCopyInventoryCraftItemStack().invoke(null, itemStack);
            getSaveNMSItemStack().invoke(nmsItemStackObj, nbtTagCompoundObj);
            getA_NBTCompressedStreamToolsOutput().invoke(null, nbtTagCompoundObj, dos);


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new BigInteger(1, baos.toByteArray()).toString(32);
    }

    public ItemStack deserializeItemStack(String serializedStack) {
        ByteArrayInputStream bais = new ByteArrayInputStream(new BigInteger(serializedStack, 32).toByteArray());
        DataInputStream dis = new DataInputStream(bais);
        ItemStack itemStack = null;
        try {
            Object nbtTagCompoundObj = getA_NBTCompressedStreamToolsInput().invoke(null, dis);
            Object craftStack = getCreateStackNMSItemStack().invoke(null, nbtTagCompoundObj);
            itemStack = (ItemStack)getAsBukkitCopyInventoryCraftItemStack().invoke(null, craftStack);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return itemStack;
    }

    public Inventory fromBase64(String data) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64Coder.decode(data));
            BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);
            Inventory inventory = Bukkit.getServer().createInventory(null, bois.readInt());

            //read serialized inventory
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, (ItemStack)bois.readObject());
            }

            bois.close();
            return inventory;
        } catch (Exception e) {
            System.out.println("Could not decode inventory: " + e.getMessage());
        }
        return null;
    }

    public String inventoryToBase64(Inventory inventory) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BukkitObjectOutputStream boos = new BukkitObjectOutputStream(baos);

            //write the size of the inventory
            boos.writeInt(inventory.getSize());

            //save list elements to data stream
            for (int i = 0; i < inventory.getSize(); i++) {
                boos.writeObject(inventory.getItem(i));
            }

            //serialize the array
            boos.close();
            return Base64Coder.encodeLines(baos.toByteArray());

        } catch (Exception e) {
            System.out.println("Could not encode inventory: " + e.getMessage());
        }
        return null;
    }



    public Class<?> getNbtTagCompoundClass() {
        if (nbtTagCompoundClass == null) {
            nbtTagCompoundClass = ReflectionUtils.getInstance().getNMSClass("NBTTagCompound");
        }
        return nbtTagCompoundClass;
    }

    public Class<?> getNmsItemStack() {
        if (nmsItemStack == null) {
            nmsItemStack = ReflectionUtils.getInstance().getNMSClass("ItemStack");
        }
        return nmsItemStack;
    }

    public Class<?> getCraftItemStack() {
        if (craftItemStack == null) {
            craftItemStack = ReflectionUtils.getInstance().getCraftBukkitClass("inventory.CraftItemStack");
        }
        return craftItemStack;
    }

    public Method getA_NBTCompressedStreamToolsInput() throws NoSuchMethodException {
        if (a_nbtCompressedStreamTools_input == null) {
            a_nbtCompressedStreamTools_input = ReflectionUtils.getInstance().getNMSClass("NBTCompressedStreamTools").getMethod("a", DataInput.class);
        }
        return a_nbtCompressedStreamTools_input;
    }

    public Method getA_NBTCompressedStreamToolsOutput() throws NoSuchMethodException {
        if (a_nbtCompressedStreamTools_output == null)
            a_nbtCompressedStreamTools_output = ReflectionUtils.getInstance().getNMSClass("NBTCompressedStreamTools").getMethod("a", getNbtTagCompoundClass(), DataOutput.class);
        return a_nbtCompressedStreamTools_output;
    }

    public Method getCreateStackNMSItemStack() throws NoSuchMethodException {
        if (createStack_nmsItemStack == null) {
            createStack_nmsItemStack = getNmsItemStack().getMethod("a", getNbtTagCompoundClass());
        }
        return createStack_nmsItemStack;
    }

    public Method getAsNMSCopyInventoryCraftItemStack() throws NoSuchMethodException {
        if (asNMSCopy_inventory_craftItemStack == null) {
            asNMSCopy_inventory_craftItemStack = getCraftItemStack().getMethod("asNMSCopy", ItemStack.class);
        }
        return asNMSCopy_inventory_craftItemStack;
    }

    public Method getSaveNMSItemStack() throws NoSuchMethodException{
        if (save_nmsItemStack == null) {
            save_nmsItemStack = getNmsItemStack().getMethod("save", getNbtTagCompoundClass());
        }
        return save_nmsItemStack;
    }

    public Method getAsBukkitCopyInventoryCraftItemStack() throws NoSuchMethodException {
        if (asBukkitCopyInventoryCraftItemStack == null) {
            asBukkitCopyInventoryCraftItemStack = getCraftItemStack().getMethod("asBukkitCopy", getNmsItemStack());
        }
        return asBukkitCopyInventoryCraftItemStack;
    } */

    public String itemStackArrayToBase64(ItemStack[] itemStacks) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BukkitObjectOutputStream boos = new BukkitObjectOutputStream(baos);

            //write the size of the data to save
            boos.writeInt(itemStacks.length);

            //serialize each itemStack
            for (ItemStack itemStack : itemStacks) {
                if (itemStack != null) {
                    boos.writeObject(itemStack.serialize());
                } else {
                    boos.writeObject(null);
                }
            }

            //save data;
            boos.close();

            return Base64Coder.encodeLines(baos.toByteArray());
        } catch (Exception e) {
            System.out.println("[Error]Could not encode item-stack array: " + e.getMessage());
        }
        return null;
    }

    public ItemStack[] itemStackArrayFromBase64(String serializedArray) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(Base64Coder.decodeLines(serializedArray));
            BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);
            ItemStack[] itemStacks = new ItemStack[bois.readInt()];

            for (int i = 0; i < itemStacks.length; i++) {
                Map<String, Object> serializedMapping = (Map<String, Object>)bois.readObject();

                if (serializedMapping != null) {
                    itemStacks[i] = ItemStack.deserialize(serializedMapping);
                } else {
                    itemStacks[i] = null;
                }
            }

            bois.close();
            return itemStacks;
        } catch (Exception e) {
            System.out.println("[Error]Could not encode item-stack array: " + e.getMessage());
        }
        return null;
    }
}
