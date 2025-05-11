package site.backrer.decisiveBattle.Game;

import org.bukkit.entity.Player;
import site.backrer.decisiveBattle.Dao.BoxDAO;
import site.backrer.decisiveBattle.Dao.SpawnDAO;
import site.backrer.decisiveBattle.Entity.Vector3;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManger {
    private final Map<Integer,GameScene> scenes = new HashMap<>();
    private final SpawnDAO spawnDAO = new SpawnDAO();
    private final BoxDAO boxDAO = new BoxDAO();

    public GameScene createScene(int code,int maxPlayer,int minPlayer,String sceneName,double pos1,double pos2,String wordName) {
        GameScene scene = new GameScene(code,maxPlayer,minPlayer,sceneName,pos1,pos2,wordName);
        scene.registerEvents();//注册监听事件
        scene.startTask(0L,20L); //启用Tick游戏事件处理器
        //获取出生点
        List<Vector3> spawnsBySceneId = spawnDAO.getSpawnsBySceneId(code);
        //获取宝箱位置
        List<Vector3> boxesBySceneId = boxDAO.getBoxesBySceneId(code);
        //初始化场景信息
        scene.initialization(spawnsBySceneId,boxesBySceneId);
        //注册到表内
        scenes.put(code, scene);
        return scene;
    }
    //玩家进入场景
    public void JoinScene(int code,Player player) {
        GameScene gameScene = scenes.get(code);
        int i = gameScene.joinPlayer(player);
        if (i == 0){
            player.sendMessage("加入场景成功!场景:"+gameScene.getSceneName());
        } else if (i == 1) {
            player.sendMessage("您以在该场景中!场景:"+gameScene.getSceneName());
        }
    }
    //玩家离开场景
    public void LeaveScene(int code,Player player) {
        GameScene gameScene = scenes.get(code);
        int i = gameScene.leavePlayer(player);
        if (i == 0){
            player.sendMessage("您以离开该场景:"+gameScene.getSceneName());
        }
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
