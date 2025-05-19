package site.backrer.decisiveBattle.Game;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import site.backrer.decisiveBattle.Dao.*;
import site.backrer.decisiveBattle.Entity.ItemPackage;
import site.backrer.decisiveBattle.Entity.SceneItemBinding;
import site.backrer.decisiveBattle.Entity.Vector3;
import site.backrer.decisiveBattle.Utils.ItemUtils;

import java.io.IOException;
import java.util.*;

public class GameManger {
    private final Map<Integer,GameScene> scenes = new HashMap<>();
    private final SpawnDAO spawnDAO = new SpawnDAO();
    private final BoxDAO boxDAO = new BoxDAO();
    private final LeaveDAO leaveDAO = new LeaveDAO();
    private final ItemPackageDAO itemPackageDAO = new ItemPackageDAO();
    private final SceneItemBindingDAO sceneItemBindingDAO = new SceneItemBindingDAO();

    public GameScene createScene(int code,int maxPlayer,int minPlayer,String sceneName,double pos1,double pos2,String wordName) {
        GameScene scene = new GameScene(code,maxPlayer,minPlayer,sceneName,pos1,pos2,wordName);
        scene.registerEvents();//注册监听事件
        //获取出生点
        List<Vector3> spawnsBySceneId = spawnDAO.getSpawnsBySceneId(code);
        //获取宝箱位置
        List<Vector3> boxesBySceneId = boxDAO.getBoxesBySceneId(code);
        //获取撤离地点
        List<Vector3> leaveSpawnBySceneId = leaveDAO.getSpawnsBySceneId(code);
        //获取并且注册所有物品
        List<SceneItemBinding> bindingsBySceneId = sceneItemBindingDAO.getBindingsBySceneId(code);
        HashMap<String,List<ItemStack>> items = new HashMap<>();
        for (SceneItemBinding sceneItemBinding : bindingsBySceneId) {
            //获取所有物品
            List<ItemPackage> allByCode = itemPackageDAO.getAllByCode(sceneItemBinding.getItemPackageCode());
            //临时物品表
            List<ItemStack> items1 = new ArrayList<>();
            //注册物品
            for (ItemPackage itemPackage : allByCode) {
                try {
                    //添加到表内
                    items1.add(ItemUtils.itemStackFromBase64(itemPackage.getBase64()));
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            //添加到表中
            items.put(sceneItemBinding.getItemPackageCode(),items1);
        }
        //初始化场景信息
        scene.initialization(spawnsBySceneId,boxesBySceneId,leaveSpawnBySceneId,items);
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
    public void endScene(int id) {
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
