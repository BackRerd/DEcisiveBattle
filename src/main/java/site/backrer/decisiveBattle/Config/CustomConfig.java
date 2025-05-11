package site.backrer.decisiveBattle.Config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import site.backrer.decisiveBattle.DecisiveBattle;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

    private final Plugin plugin = DecisiveBattle.getPlugin();
    private final String fileName;
    private File file;
    private FileConfiguration config;

    public CustomConfig(String fileName) {
        this.fileName = fileName.endsWith(".yml") ? fileName : fileName + ".yml";
        setup();
    }

    private void setup() {
        file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                plugin.getLogger().info(fileName + " 文件已创建。");
            } catch (IOException e) {
                plugin.getLogger().severe("无法创建 " + fileName + ": " + e.getMessage());
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("无法保存 " + fileName + ": " + e.getMessage());
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void deleteFile() {
        if (file.exists()) {
            file.delete();
        }
    }

    public File getFile() {
        return file;
    }
}
