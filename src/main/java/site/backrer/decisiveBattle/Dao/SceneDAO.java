package site.backrer.decisiveBattle.Dao;

import site.backrer.decisiveBattle.Entity.Scene;
import site.backrer.decisiveBattle.Utils.DBUtil;

import java.sql.*;

public class SceneDAO {
    public void insertScene(Scene scene) {
        String sql = "INSERT INTO scene (code, max_player_size, min_player_size, scene_name, pos1, pos2, world_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, scene.getCode());
            ps.setInt(2, scene.getMaxPlayerSize());
            ps.setInt(3, scene.getMinPlayerSize());
            ps.setString(4, scene.getSceneName());
            ps.setDouble(5, scene.getPos1());
            ps.setDouble(6, scene.getPos2());
            ps.setString(7, scene.getWorldName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Scene getSceneById(int id) {
        String sql = "SELECT * FROM scene WHERE id = ?";
        return getScene(id, sql);
    }
    public Scene getSceneByCode(int code) {
        String sql = "SELECT * FROM scene WHERE code = ?";
        return getScene(code, sql);
    }

    private Scene getScene(int code, String sql) {
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Scene(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getInt("max_player_size"),
                        rs.getInt("min_player_size"),
                        rs.getString("scene_name"),
                        rs.getDouble("pos1"),
                        rs.getDouble("pos2"),
                        rs.getString("world_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateScene(Scene scene) {
        String sql = "UPDATE scene SET code = ?, max_player_size = ?, min_player_size = ?, scene_name = ?, pos1 = ?, pos2 = ?, world_name = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, scene.getCode());
            ps.setInt(2, scene.getMaxPlayerSize());
            ps.setInt(3, scene.getMinPlayerSize());
            ps.setString(4, scene.getSceneName());
            ps.setDouble(5, scene.getPos1());
            ps.setDouble(6, scene.getPos2());
            ps.setString(7, scene.getWorldName());
            ps.setInt(8, scene.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteScene(int id) {
        String sql = "DELETE FROM scene WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

