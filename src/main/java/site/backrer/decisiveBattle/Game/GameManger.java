package site.backrer.decisiveBattle.Game;

import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class GameManger {
    private final Map<String,GameScene> scenes = new HashMap<>();

    public GameScene createScene(String code,int maxPlayer,int minPlayer,String sceneName,double pos1,double pos2,String wordName) {
        GameScene scene = new GameScene(code,maxPlayer,minPlayer,sceneName,pos1,pos2,wordName);
        scene.registerEvents();//注册监听事件
        scene.startTask(0L,20L); //启用Tick游戏事件处理器

        scenes.put(code, scene);
        return scene;
    }
    //停止游戏
    public void endScene(String id) {
        GameScene scene = scenes.get(id);
        if (scene != null){
            scene.cancel();
            scene.unregisterEvents();
            scenes.remove(id);
        }
    }
    //根据id获取场景
    public GameScene getSceneById(String id) {
        return scenes.get(id);
    }
    //获取当前玩家在哪个场景
    public GameScene getSceneByName(Player player) {
        for (GameScene scene: scenes.values()) {
            if (scene.thisPlayers.contains(player)) return scene;
        }
        return null;
    }
    //获取全部场景
    public Collection<GameScene> getAllScenes() {
        return scenes.values();
    }


}
