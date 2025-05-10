package site.backrer.decisiveBattle.Dao;

import site.backrer.decisiveBattle.Entity.Vector3;
import site.backrer.decisiveBattle.Utils.DBUtil;

import java.sql.*;
import java.util.*;

public class SpawnDAO {
    public void insertSpawn(int sceneId, Vector3 vec) {
        String sql = "INSERT INTO spawn (scene_id, x, y, z, world_name) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sceneId);
            ps.setDouble(2, vec.getX());
            ps.setDouble(3, vec.getY());
            ps.setDouble(4, vec.getZ());
            ps.setString(5, vec.getWordName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vector3> getSpawnsBySceneId(int sceneId) {
        List<Vector3> list = new ArrayList<>();
        String sql = "SELECT * FROM spawn WHERE scene_id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sceneId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Vector3(
                        rs.getDouble("x"),
                        rs.getDouble("y"),
                        rs.getDouble("z"),
                        rs.getString("world_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void updateSpawn(int id, Vector3 vec) {
        String sql = "UPDATE spawn SET x = ?, y = ?, z = ?, world_name = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, vec.getX());
            ps.setDouble(2, vec.getY());
            ps.setDouble(3, vec.getZ());
            ps.setString(4, vec.getWordName());
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteSpawnsBySceneId(int sceneId) {
        String sql = "DELETE FROM spawn WHERE scene_id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sceneId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

