package site.backrer.decisiveBattle.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {
    private final GameScene scene;

    public GameListener(GameScene scene) {
        this.scene = scene;
    }
    public GameScene getScene() {
        return scene;
    }
}
