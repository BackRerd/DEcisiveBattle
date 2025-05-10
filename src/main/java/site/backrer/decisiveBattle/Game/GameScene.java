package site.backrer.decisiveBattle.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import site.backrer.decisiveBattle.DecisiveBattle;
import site.backrer.decisiveBattle.Entity.Vector3;

import java.util.ArrayList;
import java.util.List;


public class GameScene extends BukkitRunnable {
    private String code; //场景代号
    private int maxPlayerSize; //最大玩家数量
    private int minPlayerSize; //最小多少玩家开始游戏
    private String sceneName; //场景名称

    private double pos1; //范围1
    private double pos2; //范围2
    private String worldName; //世界名称
    private List<Vector3> spawns = new ArrayList<>(); //出生地点
    private List<Vector3> boxs = new ArrayList<>(); //宝箱位置

    private GameListener listener; //监听的事件
    List<Player> thisPlayers = new ArrayList<Player>(); //存放记录玩家表
    boolean isStarted = false; //场景是否处于开启状态

    public GameScene(String code, int maxPlayerSize, int minPlayerSize, String sceneName, double pos1, double pos2, String worldName) {
        this.code = code;
        this.maxPlayerSize = maxPlayerSize;
        this.minPlayerSize = minPlayerSize;
        this.sceneName = sceneName;
        this.pos1 = pos1;
        this.pos2 = pos2;
        this.worldName = worldName;
    }

    //初始化场景信息
    public void initialization(List<Vector3> spawns, List<Vector3> boxs){
        this.spawns = spawns;
        this.boxs = boxs;
    }

    @Override
    public void run() {

    }
    // 初始化并启动任务
    public void startTask(long delayTicks, long intervalTicks) {
        this.runTaskTimer(DecisiveBattle.getPlugin(), delayTicks, intervalTicks);
    }
    //注册监听事件
    public void registerEvents() {
        listener = new GameListener(this);
        Bukkit.getPluginManager().registerEvents(listener, DecisiveBattle.getPlugin());
    }
    //销毁监听事件
    public void unregisterEvents() {
        if (listener != null) {
            HandlerList.unregisterAll(listener);
        }
    }
}
