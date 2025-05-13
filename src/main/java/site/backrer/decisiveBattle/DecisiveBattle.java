package site.backrer.decisiveBattle;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import site.backrer.decisiveBattle.Config.CurrentConfig;
import site.backrer.decisiveBattle.Config.CustomConfig;
import site.backrer.decisiveBattle.Utils.DBUtil;
import site.backrer.decisiveBattle.cmd.DbsCommand;

import java.sql.Connection;
import java.sql.SQLException;

public final class DecisiveBattle extends JavaPlugin {
    private static Plugin plugin;
    private static ConfigurationSection config;
    private static CurrentConfig currentConfig;

    @Override
    public void onEnable() {
        plugin = this;
        config = getConfig();
        currentConfig = new CurrentConfig();
        //生成config文件
        saveDefaultConfig();
        getCommand("dbs").setExecutor(new DbsCommand());
        try {
            Connection connection = DBUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static CurrentConfig getCurrentConfig() {
        return currentConfig;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
