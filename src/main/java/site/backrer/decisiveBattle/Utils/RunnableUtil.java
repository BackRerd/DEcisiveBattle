package site.backrer.decisiveBattle.Utils;

import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import site.backrer.decisiveBattle.DecisiveBattle;
import site.backrer.decisiveBattle.actions.ContinuouslyExecutingEvents;
import site.backrer.decisiveBattle.actions.NotifyAfterDelay;

public class RunnableUtil {
    private static final long TwoZero = 20L;
    //等待 time秒后执行操作
    public static BukkitTask AfterDelay(NotifyAfterDelay notifyAfterDelay,long time) {
        return new BukkitRunnable() {
            @Override
            public void run() {
                notifyAfterDelay.event1();
            }
        }.runTaskLater(DecisiveBattle.getPlugin(), time * TwoZero);
    }
    //每time秒执行一次
    public static BukkitTask startRepeating(ContinuouslyExecutingEvents continuouslyExecutingEvents,long time){
        return new BukkitRunnable(){

            @Override
            public void run() {
                if (continuouslyExecutingEvents.isTick()){
                    cancel();
                }
                continuouslyExecutingEvents.event1();
            }
        }.runTaskTimer(DecisiveBattle.getPlugin(), 0L, time * TwoZero);
    }
}
