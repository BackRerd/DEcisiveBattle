package site.backrer.decisiveBattle.cmd;

import de.tr7zw.nbtapi.NBT;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import site.backrer.decisiveBattle.Dao.*;
import site.backrer.decisiveBattle.DecisiveBattle;
import site.backrer.decisiveBattle.Entity.ItemPackage;
import site.backrer.decisiveBattle.Entity.Scene;
import site.backrer.decisiveBattle.Entity.Vector3;
import site.backrer.decisiveBattle.Holder.ItemPackageGUIHolder;
import site.backrer.decisiveBattle.Utils.ItemUtils;
import site.backrer.decisiveBattle.Utils.VectorUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DbsCommand implements CommandExecutor, Listener {
    private final SceneDAO sceneDAO = new SceneDAO();
    private final SpawnDAO spawnDAO = new SpawnDAO();
    private final BoxDAO boxDAO = new BoxDAO();
    private final LeaveDAO leaveDAO = new LeaveDAO();
    private final ItemPackageDAO itemPackageDAO = new ItemPackageDAO();
    private Inventory gui = null;

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
            case "itemgui":
                openItemPackageGUI(player, args[1], 0);
                break;
            case "itemadd":
                addItem(player,args[1],args[2]);
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
                            editName(player,code, args[3]);
                        }
                        break;

                    case "addspawn":
                        addSpawn(player,code, player.getLocation());
                        break;

                    case "addbox":
                        addBox(player,code, player.getLocation());
                        break;
                    case "leavespawn":
                        leaveSpawn(player,code, player.getLocation());
                        break;

                    case "listspawn":
                        listSpawns(code, player);
                        break;

                    case "listbox":
                        listBoxes(code, player);
                        break;

                    case "removespawn":
                        if (args.length < 4) {
                            player.sendMessage("用法: /dbs edit <代号> " + subCommand + " <id>");
                        } else {
                            removeItem(0,player,code, subCommand, args[3]);
                        }
                        break;
                    case "removeleave":
                        if (args.length < 4) {
                            player.sendMessage("用法: /dbs edit <代号> " + subCommand + " <id>");
                        } else {
                            removeItem(2,player,code, subCommand, args[3]);
                        }
                        break;
                    case "removebox":
                        if (args.length < 4) {
                            player.sendMessage("用法: /dbs edit <代号> " + subCommand + " <id>");
                        } else {
                            removeItem(1,player,code, subCommand, args[3]);
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

    //添加物品
    private void addItem(Player player, String arg1,String arg2) {
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        if (itemInMainHand.getType() == Material.AIR) {
            player.sendMessage("空物品！");
            return;
        }
        ItemPackage itemPackage = new ItemPackage(arg1,"text",ItemUtils.itemStackToBase64(itemInMainHand),arg2);
        itemPackageDAO.insertItemPackage(itemPackage);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            player.sendMessage(ItemUtils.getNBTTag(event.getCurrentItem(),"base64"));
        }
        if (gui.getHolder() == event.getWhoClicked()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {

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
        Vector3 loc1 = new Vector3(
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"x"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"y"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"z"),
                DecisiveBattle.getCurrentConfig().getConfig().getString(player.getName()+".pos1"+"name")
        );
        Vector3 loc2 = new Vector3(
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"x"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"y"),
                DecisiveBattle.getCurrentConfig().getConfig().getDouble(player.getName()+".pos1"+"z"),
                DecisiveBattle.getCurrentConfig().getConfig().getString(player.getName()+".pos2"+"name")
        );
        if (loc1.getX() == 0 || loc2.getX() == 0){
            player.sendMessage("不存在位置坐标");
            return;
        }
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
        System.out.println(scene.toString());
        //插入
        sceneDAO.insertScene(scene);
    }
    // 修改名称逻辑
    private void editName(Player player,String code, String newName) {
        Scene sceneByCode = sceneDAO.getSceneByCode(code);
        sceneByCode.setSceneName(newName);
        sceneDAO.updateScene(sceneByCode);
        player.sendMessage("名称修改完成: " + newName);
    }
    // 添加出生点
    private void addSpawn(Player player,String code, Location loc) {
        spawnDAO.insertSpawn(Integer.parseInt(code),VectorUtils.fromLocation(loc));
        player.sendMessage("位置已添加:" + locToString(loc));
    }
    // 添加出生点
    private void leaveSpawn(Player player,String code, Location loc) {
        spawnDAO.insertSpawn(Integer.parseInt(code),VectorUtils.fromLocation(loc));
        player.sendMessage("离开位置已添加:" + locToString(loc));
    }
    // 添加宝箱
    private void addBox(Player player,String code, Location loc) {
        boxDAO.insertBox(Integer.parseInt(code),VectorUtils.fromLocation(loc));
        player.sendMessage("宝箱已添加:" + locToString(loc));
    }

    private void listSpawns(String code, Player player) {
        // 列出生点
    }

    private void listBoxes(String code, Player player) {
        // 列宝箱
    }

    private void removeItem(int z,Player player,String code, String type, String idStr) {
        if (z == 0){
            spawnDAO.deleteSpawnsById(Integer.parseInt(idStr));
        }else if (z == 1){
            boxDAO.deleteBoxesById(Integer.parseInt(idStr));
        } else if (z == 2) {
            leaveDAO.deleteSpawnsById(Integer.parseInt(idStr));
        } else {
            player.sendMessage("未知条件");
        }
        player.sendMessage("删除成功!");
    }

    private String locToString(Location loc) {
        return String.format("[%s] %.1f, %.1f, %.1f", loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ());
    }
    public void openItemPackageGUI(Player player, String packageCode, int page) {

        List<ItemPackage> packages = itemPackageDAO.getAllByCode(packageCode);
        int totalPages = (int) Math.ceil(packages.size() / 45.0);

        gui = Bukkit.createInventory(
                new ItemPackageGUIHolder(packageCode, page),
                54,
                ChatColor.GOLD + "物品包管理 - " + "总页数:"+totalPages + " 页"
        );
        gui.setItem(49,createButtonItem(Material.ARROW, ChatColor.RED +"|当前页数:"+page+1));
        if (!packages.isEmpty()){
            // 显示物品
            int start = page * 45;
            for (int i = 0; i < 45 && (start + i) < packages.size(); i++) {
                try {
                    String base64 = packages.get(start + i).getBase64();
                    ItemStack itemStack = ItemUtils.itemStackFromBase64(base64);
                    ItemUtils.setNBTTag(itemStack,"base64",base64);
                    gui.setItem(i, itemStack);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 翻页按钮
            if (page > 0) gui.setItem(45, createButtonItem(Material.ARROW, ChatColor.GREEN + "上一页"));
            if (start + 45 < packages.size()) gui.setItem(53, createButtonItem(Material.ARROW, ChatColor.GREEN + "下一页"));
        }


        player.openInventory(gui);
    }

    private ItemStack createButtonItem(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

}

