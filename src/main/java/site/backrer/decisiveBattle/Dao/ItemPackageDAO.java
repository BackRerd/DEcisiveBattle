package site.backrer.decisiveBattle.Dao;

import site.backrer.decisiveBattle.Entity.ItemPackage;
import site.backrer.decisiveBattle.Utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemPackageDAO {

    // 插入物品包
    public void insertItemPackage(ItemPackage itemPackage) {
        String sql = "INSERT INTO item_package (code, name, base64, probability) VALUES (?, ?, ?,?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, itemPackage.getCode());
            ps.setString(2, itemPackage.getName());
            ps.setString(3, itemPackage.getBase64());
            ps.setString(4,itemPackage.getProbability());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 根据ID获取物品包
    public ItemPackage getItemPackageById(int id) {
        String sql = "SELECT * FROM item_package WHERE id = ?";
        return getItemPackage(id, sql);
    }

    // 根据code获取物品包
    public ItemPackage getItemPackageByCode(String code) {
        String sql = "SELECT * FROM item_package WHERE code = ?";
        return getItemPackage(code, sql);
    }

    private ItemPackage getItemPackage(Object param, String sql) {
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            if (param instanceof Integer) {
                ps.setInt(1, (Integer) param);
            } else {
                ps.setString(1, (String) param);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ItemPackage(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("base64"),
                        rs.getString("probability")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 更新物品包
    public void updateItemPackage(ItemPackage itemPackage) {
        String sql = "UPDATE item_package SET code = ?, name = ?,probability = ?, base64 = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, itemPackage.getCode());
            ps.setString(2, itemPackage.getName());
            ps.setString(3, itemPackage.getBase64());
            ps.setInt(4, itemPackage.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除物品包
    public void deleteItemPackage(int id) {
        String sql = "DELETE FROM item_package WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //获取物品的所有的物品
    public List<ItemPackage> getAllByCode(String code) {
        List<ItemPackage> list = new ArrayList<>();
        String sql = "SELECT * FROM item_package WHERE code = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ItemPackage(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getString("base64"),
                        rs.getString("probability")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}

