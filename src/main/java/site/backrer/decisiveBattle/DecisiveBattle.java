package site.backrer.decisiveBattle;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import site.backrer.decisiveBattle.Config.CurrentConfig;
import site.backrer.decisiveBattle.Config.CustomConfig;
import site.backrer.decisiveBattle.Game.GameManger;
import site.backrer.decisiveBattle.Utils.DBUtil;
import site.backrer.decisiveBattle.cmd.DbsCommand;

import java.sql.Connection;
import java.sql.SQLException;

public final class DecisiveBattle extends JavaPlugin {
    private static Plugin plugin;
    private static ConfigurationSection config;
    private static CurrentConfig currentConfig;
    private static GameManger gameManger;

    public static GameManger getGameManger() {
        return gameManger;
    }

    @Override
    public void onEnable() {
        plugin = this;
        config = getConfig();
        currentConfig = new CurrentConfig();
        gameManger = new GameManger();
        //生成config文件
        saveDefaultConfig();
        DbsCommand dbsCommand = new DbsCommand();
        //注册指令
        getCommand("dbs").setExecutor(dbsCommand);
        //注册事件
        Bukkit.getPluginManager().registerEvents(dbsCommand, this);
        try {
            Connection connection = DBUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //nbt前置加载加载
        if (!NBT.preloadApi()) {
            getLogger().warning("未安装NBT前置插件！");
            getPluginLoader().disablePlugin(this);
            return;
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
