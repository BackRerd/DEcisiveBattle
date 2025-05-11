package site.backrer.decisiveBattle.Dao;

import site.backrer.decisiveBattle.Entity.Scene;
import site.backrer.decisiveBattle.Utils.DBUtil;

import java.sql.*;

public class SceneDAO {

    // 插入场景到数据库
    public void insertScene(Scene scene) {
        String sql = "INSERT INTO scene (code, max_player_size, min_player_size, scene_name, pos1_x, pos1_y, pos1_z, pos2_x, pos2_y, pos2_z, world_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, scene.getCode());
            ps.setInt(2, scene.getMaxPlayerSize());
            ps.setInt(3, scene.getMinPlayerSize());
            ps.setString(4, scene.getSceneName());
            ps.setDouble(5, scene.getPos1_x());
            ps.setDouble(6, scene.getPos1_y());
            ps.setDouble(7, scene.getPos1_z());
            ps.setDouble(8, scene.getPos2_x());
            ps.setDouble(9, scene.getPos2_y());
            ps.setDouble(10, scene.getPos2_z());
            ps.setString(11, scene.getWorldName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据ID获取场景
    public Scene getSceneById(int id) {
        String sql = "SELECT * FROM scene WHERE id = ?";
        return getScene(id, sql);
    }

    // 根据场景代号获取场景
    public Scene getSceneByCode(String code) {
        String sql = "SELECT * FROM scene WHERE code = ?";
        return getScene(code, sql);
    }

    // 从数据库中获取场景
    private Scene getScene(Object param, String sql) {
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (param instanceof Integer) {
                ps.setInt(1, (Integer) param);
            } else if (param instanceof String) {
                ps.setString(1, (String) param);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Scene(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getInt("max_player_size"),
                        rs.getInt("min_player_size"),
                        rs.getString("scene_name"),
                        rs.getDouble("pos1_x"), rs.getDouble("pos1_y"), rs.getDouble("pos1_z"),
                        rs.getDouble("pos2_x"), rs.getDouble("pos2_y"), rs.getDouble("pos2_z"),
                        rs.getString("world_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 更新场景信息
    public void updateScene(Scene scene) {
        String sql = "UPDATE scene SET code = ?, max_player_size = ?, min_player_size = ?, scene_name = ?, pos1_x = ?, pos1_y = ?, pos1_z = ?, pos2_x = ?, pos2_y = ?, pos2_z = ?, world_name = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, scene.getCode());
            ps.setInt(2, scene.getMaxPlayerSize());
            ps.setInt(3, scene.getMinPlayerSize());
            ps.setString(4, scene.getSceneName());
            ps.setDouble(5, scene.getPos1_x());
            ps.setDouble(6, scene.getPos1_y());
            ps.setDouble(7, scene.getPos1_z());
            ps.setDouble(8, scene.getPos2_x());
            ps.setDouble(9, scene.getPos2_y());
            ps.setDouble(10, scene.getPos2_z());
            ps.setString(11, scene.getWorldName());
            ps.setInt(12, scene.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除场景
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


