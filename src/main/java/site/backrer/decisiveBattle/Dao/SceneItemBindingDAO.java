package site.backrer.decisiveBattle.Dao;

import site.backrer.decisiveBattle.Entity.SceneItemBinding;
import site.backrer.decisiveBattle.Utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SceneItemBindingDAO {

    // 插入场景绑定
    public void insertBinding(SceneItemBinding binding) {
        String sql = "INSERT INTO scene_item_binding (scene_id, item_package_code) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, binding.getSceneId());
            ps.setString(2, binding.getItemPackageCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据ID获取绑定记录
    public SceneItemBinding getBindingById(int id) {
        String sql = "SELECT * FROM scene_item_binding WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new SceneItemBinding(
                        rs.getInt("id"),
                        rs.getInt("scene_id"),
                        rs.getString("item_package_code")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取指定场景的所有绑定记录
    public List<SceneItemBinding> getBindingsBySceneId(int sceneId) {
        String sql = "SELECT * FROM scene_item_binding WHERE scene_id = ?";
        List<SceneItemBinding> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sceneId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new SceneItemBinding(
                        rs.getInt("id"),
                        rs.getInt("scene_id"),
                        rs.getString("item_package_code")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 删除绑定记录
    public void deleteBinding(int id) {
        String sql = "DELETE FROM scene_item_binding WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

