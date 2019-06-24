package me.jokur.nauka;

import org.apache.commons.lang.UnhandledException;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MatiPlugin extends JavaPlugin implements Listener {

    public void loadConfiguration() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        getServer().getConsoleSender().sendMessage("Plugin " + ChatColor.AQUA + Bukkit.getPluginManager().getPlugin("MatiPlugin").getName() + ChatColor.WHITE + " wlaczony!");
        Bukkit.getWorlds().get(0).setThundering(false);


        getCommand("gracze").setExecutor(new CommandGracze());
        getCommand("dzien").setExecutor(new CommandDzien());
        getCommand("noc").setExecutor(new CommandNoc());
        getCommand("gm").setExecutor(new CommandGm());
        getCommand("pogoda").setExecutor(new CommandPogoda());
        getCommand("sklep").setExecutor(new CommandSklep());
        getCommand("spawn").setExecutor(new CommandSpawn());
        getCommand("sethome").setExecutor(this);
        getCommand("home").setExecutor(this);
        getCommand("setsecurity").setExecutor(this);

    }

    @Override
    public void onDisable() {
        this.getLogger().info("Pluginy wylaczony");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Integer x = p.getStatistic(Statistic.MINE_BLOCK, Material.DIAMOND_ORE);

        p.sendMessage(x.toString());
        if(p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.RED + p.getName() + ChatColor.GOLD + " witaj na serwerze!");
        }
        else{
            p.setGameMode(GameMode.ADVENTURE);
            e.setJoinMessage(ChatColor.AQUA + p.getName() + ChatColor.YELLOW + " nowy gracz na serwerze!");
        }
    }

    @EventHandler
    public void opShow(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.isOp()) {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Administrator" + ChatColor.WHITE + "] " + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
        }
        else {
            e.setFormat(ChatColor.WHITE + "[" + ChatColor.DARK_GRAY + "Gracz" + ChatColor.WHITE + "] " + p.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + e.getMessage());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.AQUA + p.getName() + ChatColor.RED + " odszedł od nas!");
    }

    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Block block = e.getBlock();
        Material material = block.getType();
        Player player = e.getPlayer();
        if (!player.isOp()) {
            if (material.equals(Material.TNT)) {
                block.setType(Material.AIR);
                player.sendMessage(ChatColor.RED + "Nie możesz używać TNT!");
            }
        }
    }

    @EventHandler
    public void WeatherEvent(LightningStrikeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void tntminecartExplodeBlock(EntityExplodeEvent e){
            if (e.getEntityType() == EntityType.MINECART_TNT) {
                e.setCancelled(true);
            }
        }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("sethome")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                String uuid = p.getUniqueId().toString();
                getConfig().set(uuid + ".uuid", uuid);
                getConfig().set(uuid + ".world", p.getLocation().getWorld().getName());
                getConfig().set(uuid + ".x", p.getLocation().getX());
                getConfig().set(uuid + ".y", p.getLocation().getY());
                getConfig().set(uuid + ".z", p.getLocation().getZ());
                getConfig().set(uuid + ".pitch", p.getEyeLocation().getPitch());
                getConfig().set(uuid + ".yaw", p.getEyeLocation().getYaw());
                saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Ustawiłeś home");
                return true;
            }
            else {
                sender.sendMessage("Tylko gracze moga uzywac tej komendy");
                return true;
            }
        }
        if(command.getName().equalsIgnoreCase("home")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                String uid = getConfig().getString(p.getUniqueId().toString() + ".uuid");
                try {
                    World w = Bukkit.getServer().getWorld(getConfig().getString(uid + ".world"));
                    double x = getConfig().getDouble(uid + ".x");
                    double y = getConfig().getDouble(uid + ".y");
                    double z = getConfig().getDouble(uid + ".z");
                    double pitch = getConfig().getDouble(uid + ".pitch");
                    double yaw = getConfig().getDouble(uid + ".yaw");
                    p.teleport(new Location(w,x,y,z,(float)pitch,(float)yaw));
                    sender.sendMessage(ChatColor.WHITE + "Ah Shit, Here We Go Again");
                    return true;
                }
                catch (Exception e){
                    p.sendMessage(ChatColor.AQUA + "Nie masz ustawionego homa");
                    p.sendMessage(ChatColor.YELLOW + "Możesz to zrobić za pomocą komendy " + ChatColor.WHITE + "/sethome");
                    return true;
                }
            }
            else {
                sender.sendMessage("Tylko gracze moga uzywac tej komendy");
            }
        }
        if(command.getName().equalsIgnoreCase("setsecurity")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                String uuid = p.getUniqueId().toString();
                getConfig().set("security."+p.getName()+".uuid", uuid);
                getConfig().set("security."+p.getName()+".world", p.getLocation().getWorld().getName());
                getConfig().set("security."+p.getName()+".x", p.getLocation().getX());
                getConfig().set("security."+p.getName()+".y", p.getLocation().getY());
                getConfig().set("security."+p.getName()+".z", p.getLocation().getZ());
                saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Ustawiłeś strzeżony obszar");
                return true;
            }
            else {
                sender.sendMessage("Tylko gracze moga uzywac tej komendy");
                return true;
            }
        }
        return true;
    }

    @EventHandler
    public void stealSecurity(InventoryClickEvent e) {
        Inventory inv = e.getClickedInventory();
        Player p = (Player) e.getWhoClicked();

        double x = p.getLocation().getX();
        double y = p.getLocation().getY();
        double z = p.getLocation().getZ();

        if (e.getInventory().getType() == InventoryType.CHEST){
            if (e.getCurrentItem().getType() == Material.DIAMOND || e.getCurrentItem().getType() == Material.DIAMOND_BLOCK) {
                getServer().getConsoleSender().sendMessage(p.getName() + " robi cos z diaxami w skrzyni na x: " + x + " y: " + y + " z: " + z);
            }
            else {
                getServer().getConsoleSender().sendMessage(p.getName() + " grzebie w skrzyni na x: " + x + " y: " + y + " z: " + z);
            }
        }
    }

}