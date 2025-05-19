package site.backrer.decisiveBattle.Board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.List;

public class ScoreBoard {
    private Player player;
    private Scoreboard scoreboard;
    private Objective objective;
    private String title;

    public ScoreBoard(Player player, String title) {
        this.player = player;
        this.title = title;
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective(String.valueOf(player.getUniqueId()), Criteria.DUMMY,title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(title);
    }
    public void setList(List<String> list){
        for (String ll : list){
            objective.getScore(ll).setScore(0);
        }
    }

    public void showSidebar(){
        player.setScoreboard(scoreboard);
    }
    public void hideSidebar(){
        Scoreboard empty = Bukkit.getScoreboardManager().getNewScoreboard();
        player.setScoreboard(empty);
    }
}
