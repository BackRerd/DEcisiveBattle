package site.backrer.decisiveBattle.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import site.backrer.decisiveBattle.Entity.Vector3;

public class VectorUtils {
    public static Location toLocation(Vector3 vec) {
        return new Location(Bukkit.getWorld(vec.getWordName()), vec.getX(), vec.getY(), vec.getZ());
    }

    public static Vector3 fromLocation(Location loc) {
        return new Vector3(loc.getX(), loc.getY(), loc.getZ(), loc.getWorld().getName());
    }
}
