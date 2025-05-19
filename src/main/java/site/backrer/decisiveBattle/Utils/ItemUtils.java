package site.backrer.decisiveBattle.Utils;

import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ItemUtils {
    public static void setNBTTag(ItemStack item, String key, String value) {
        NBT.modify(item, nbt -> {
            nbt.setString(key, value);
        });
    }

    public static String getNBTTag(ItemStack item, String key) {
        if (item == null || item.getType() == Material.AIR || item.getAmount() <= 0) {
            return null; // 或者抛出你自定义的异常，或返回 Optional.empty()
        }
        return NBT.get(item, nbt-> {
            return nbt.getString(key);
        });
    }
    public static String itemStackToBase64(ItemStack item){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = null;
        try {
            dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeObject(item);
            dataOutput.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
    public static ItemStack itemStackFromBase64(String base64) throws IOException, ClassNotFoundException {
        byte[] bytes = Base64.getDecoder().decode(base64);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);

        ItemStack item = (ItemStack) dataInput.readObject();
        dataInput.close();
        return item;
    }
    //概率检测0.1-1.0
    public static boolean checkProbability(double probability) {
        if (probability <= 0) return false;
        if (probability >= 1) return true;
        return Math.random() < probability;
    }

}
