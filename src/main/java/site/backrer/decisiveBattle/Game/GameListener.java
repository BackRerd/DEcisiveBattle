package site.backrer.decisiveBattle.Game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import site.backrer.decisiveBattle.Utils.RunnableUtil;

public record GameListener(GameScene scene) implements Listener {
    @EventHandler
    public void exit(PlayerQuitEvent e) {
        //玩家退出服务器直接退出当前场景
        if (scene.thisPlayers.contains(e.getPlayer())) {
            scene.thisPlayers.remove(e.getPlayer());
        }
    }
    @EventHandler
    public void death(PlayerDeathEvent e) {
        if (scene.thisPlayers.contains(e.getEntity())) {
            Player player = Bukkit.getPlayer(e.getEntity().getUniqueId());
            //死亡切换观察者等待五秒后删除玩家
            player.setGameMode(GameMode.SPECTATOR);
            RunnableUtil.AfterDelay(() -> {
                scene.thisPlayers.remove(player);
                player.setGameMode(GameMode.SURVIVAL);
            },5);

        }
    }
}
