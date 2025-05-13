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
    //是否在两个位置形成的正方形
    public static boolean isWithinBounds(Vector3 point, Vector3 vc1, Vector3 vc2) {
        double minX = Math.min(vc1.getX(), vc2.getX());
        double maxX = Math.max(vc1.getX(), vc2.getX());

        double minY = Math.min(vc1.getY(), vc2.getY());
        double maxY = Math.max(vc1.getY(), vc2.getY());

        double minZ = Math.min(vc1.getZ(), vc2.getZ());
        double maxZ = Math.max(vc1.getZ(), vc2.getZ());

        return (point.getX() >= minX && point.getX() <= maxX) &&
                (point.getY() >= minY && point.getY() <= maxY) &&
                (point.getZ() >= minZ && point.getZ() <= maxZ);
    }

}
