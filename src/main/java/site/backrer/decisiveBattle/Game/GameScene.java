package site.backrer.decisiveBattle.Game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import site.backrer.decisiveBattle.DecisiveBattle;
import site.backrer.decisiveBattle.Entity.Vector3;
import site.backrer.decisiveBattle.Utils.RunnableUtil;
import site.backrer.decisiveBattle.actions.ContinuouslyExecutingEvents;
import site.backrer.decisiveBattle.actions.NotifyAfterDelay;

import java.util.ArrayList;
import java.util.List;


public class GameScene extends BukkitRunnable {
    private int code; //场景代号
    private int maxPlayerSize; //最大玩家数量
    private int minPlayerSize; //最小多少玩家开始游戏
    private String sceneName; //场景名称

    private double pos1; //范围1
    private double pos2; //范围2
    private String worldName; //世界名称
    private List<Vector3> spawns = new ArrayList<>(); //出生地点
    private List<Vector3> boxs = new ArrayList<>(); //宝箱位置
    private List<Vector3> leaveSpawns = new ArrayList<>();

    private GameListener listener; //监听的事件
    List<Player> thisPlayers = new ArrayList<Player>(); //存放记录玩家表
    boolean isStarted = false; //场景是否处于开启状态

    //临时存储变量
    int countDown = 0; //倒计时游戏开始
    boolean isWaiting = false;

    public GameScene(int code, int maxPlayerSize, int minPlayerSize, String sceneName, double pos1, double pos2, String worldName) {
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
        //处理事件
    }
    //玩家进入场景事件:0加入成功,1加入失败以存在该玩家,2游戏已经开启。
    public int joinPlayer(Player player){
        if (isStarted){
            return 2;
        }
        if (!thisPlayers.contains(player)) {
            thisPlayers.add(player);
            return 0;
        }
        if (thisPlayers.size() > minPlayerSize
        && !isWaiting) {
            isWaiting = true;

            //倒计时准备开始游戏
            RunnableUtil.startRepeating(new ContinuouslyExecutingEvents() {
                private int counter = countTimeMax;
                @Override
                public boolean isTick() {
                    if (thisPlayers.size() < minPlayerSize){
                        isWaiting = false;
                        return true;
                    }
                    return false;
                }

                @Override
                public void event1() {
                    counter--; //减少时间
                    countDown = counter; //记录当前剩余时间，给予整个使用

                    if (counter <= 0){
                        //游戏开始
                        isStarted = true;
                        StartInit();
                    }
                }
            }, 1);

        }
        return 1;
    }

    //玩家退出场景事件:0退出成功
    public int leavePlayer(Player player){
        thisPlayers.remove(player);
        return 0;
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

    //游戏首次开启后处理的事件
    private void StartInit(){

    }

    ///  get And set
    public int getCode() {
        return code;
    }
    public int getMaxPlayerSize() {
        return maxPlayerSize;
    }
    public int getMinPlayerSize() {
        return minPlayerSize;
    }
    public String getSceneName() {
        return sceneName;
    }
    public double getPos1() {
        return pos1;
    }
    public double getPos2() {
        return pos2;
    }
    public String getWorldName() {
        return worldName;
    }
    public List<Vector3> getSpawns() {
        return spawns;
    }
    public List<Vector3> getBoxs() {
        return boxs;
    }
    public GameListener getListener() {
        return listener;
    }
    public List<Player> getThisPlayers() {
        return thisPlayers;
    }
    public boolean isStarted() {
        return isStarted;
    }
    public List<Vector3> getLeaveSpawns() {return leaveSpawns;}
}
