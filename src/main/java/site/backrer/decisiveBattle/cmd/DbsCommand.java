package site.backrer.decisiveBattle.cmd;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import site.backrer.decisiveBattle.Dao.SceneDAO;
import site.backrer.decisiveBattle.DecisiveBattle;
import site.backrer.decisiveBattle.Entity.Scene;
import site.backrer.decisiveBattle.Entity.Vector3;
import site.backrer.decisiveBattle.Utils.VectorUtils;

public class DbsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("该命令只能由玩家执行。");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("dbs.admin")) {
            player.sendMessage(ChatColor.RED + "你没有权限执行此命令！");
            return true; // 如果没有权限，直接返回
        }

        if (args.length == 0) {
            player.sendMessage("用法错误，请输入 /dbs help 查看用法。");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "pos1":
                // 设置 pos1
                setPos(player, 1);
                break;

            case "pos2":
                // 设置 pos2
                setPos(player, 2);
                break;

            case "create":
                if (args.length < 5) {
                    player.sendMessage("用法: /dbs create <代号> <名称> <最小玩家数> <最大玩家数>");
                } else {
                    createGame(player,args[1], args[2], args[3], args[4]);
                }
                break;

            case "edit":
                if (args.length < 3) {
                    player.sendMessage("用法错误，检查 edit 参数。");
                    break;
                }

                String code = args[1];
                String subCommand = args[2];

                switch (subCommand.toLowerCase()) {
                    case "name":
                        if (args.length < 4) {
                            player.sendMessage("用法: /dbs edit <代号> name <新名称>");
                        } else {
                            editName(code, args[3]);
                        }
                        break;

                    case "addspawn":
                        addSpawn(code, player.getLocation());
                        break;

                    case "addbox":
                        addBox(code, player.getLocation());
                        break;

                    case "listspawn":
                        listSpawns(code, player);
                        break;

                    case "listbox":
                        listBoxes(code, player);
                        break;

                    case "removespawn":
                    case "removebox":
                        if (args.length < 4) {
                            player.sendMessage("用法: /dbs edit <代号> " + subCommand + " <id>");
                        } else {
                            removeItem(code, subCommand, args[3]);
                        }
                        break;

                    default:
                        player.sendMessage("未知子命令: " + subCommand);
                        break;
                }
                break;

            default:
                player.sendMessage("未知命令，请使用 /dbs help");
                break;
        }

        return true;
    }

    private void setPos(Player player, int id) {
        Location loc = player.getLocation();
        player.sendMessage("已设置 Pos" + id + " 为: " + locToString(loc));
        DecisiveBattle.getCurrentConfig().getConfig().set(player.getName()+".pos"+id+"x",loc.getX());
        DecisiveBattle.getCurrentConfig().getConfig().set(player.getName()+".pos"+id+"y",loc.getY());
        DecisiveBattle.getCurrentConfig().getConfig().set(player.getName()+".pos"+id+"z",loc.getZ());
        DecisiveBattle.getCurrentConfig().getConfig().set(player.getName()+".pos"+id+"name",loc.getWorld().getName());
        DecisiveBattle.getCurrentConfig().save();
    }

    private void createGame(Player player,String code, String name, String min, String max) {
        // 实现游戏创建逻辑
        System.out.println("创建游戏: " + code + " 名称: " + name);
        SceneDAO sceneDAO = new SceneDAO();
        Vector3 loc1 = new Vector3(
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"x"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"y"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"z"),
                DecisiveBattle.getCurrentConfig().getConfig().getString(player.getName()+".name"+"name")
        );
        Vector3 loc2 = new Vector3(
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"x"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"y"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"z"),
                DecisiveBattle.getCurrentConfig().getConfig().getString(player.getName()+".name"+"name")
        );
        //实体类初始化
        Scene scene = new Scene(
                Integer.parseInt(code),
                Integer.parseInt(max),
                Integer.parseInt(min),
                name,
                loc1.getX(),
                loc1.getY(),
                loc1.getZ(),
                loc2.getX(),
                loc2.getY(),
                loc2.getZ(),
                loc2.getWordName()
        );
        //插入
        sceneDAO.insertScene(scene);

    }

    private void editName(String code, String newName) {
        // 修改名称逻辑
    }

    private void addSpawn(String code, Location loc) {
        // 添加出生点
    }

    private void addBox(String code, Location loc) {
        // 添加宝箱
    }

    private void listSpawns(String code, Player player) {
        // 列出生点
    }

    private void listBoxes(String code, Player player) {
        // 列宝箱
    }

    private void removeItem(String code, String type, String idStr) {
        try {
            int id = Integer.parseInt(idStr);
            // 删除指定 id 的出生点或宝箱
        } catch (NumberFormatException e) {
            // id 不是数字
        }
    }

    private String locToString(Location loc) {
        return String.format("[%s] %.1f, %.1f, %.1f", loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ());
    }
}

