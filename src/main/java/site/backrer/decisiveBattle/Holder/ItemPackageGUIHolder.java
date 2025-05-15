package site.backrer.decisiveBattle.Holder;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ItemPackageGUIHolder implements InventoryHolder {
    private final String packageCode;
    private int page;

    public ItemPackageGUIHolder(String packageCode, int page) {
        this.packageCode = packageCode;
        this.page = page;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public int getPage() {
        return page;
    }

    @Override
    public Inventory getInventory() {
        return null; // Bukkit 会设置实际 Inventory
    }
}

