package me.jokur.nauka;

import org.apache.commons.lang.UnhandledException;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleCreateEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.WeatherEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.ParseException;

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
        getCommand("sethome").setExecutor(this);
        getCommand("home").setExecutor(this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Pluginy wylaczony");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(p.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.RED + p.getName() + ChatColor.GOLD + " witaj na serwerze!");
        }
        else{
            p.setGameMode(GameMode.ADVENTURE);
            e.setJoinMessage(ChatColor.AQUA + p.getName() + ChatColor.YELLOW + " nowy gracz na serwerze!");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.AQUA + p.getName() + ChatColor.RED + " żegnaj z  serwera!");
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
        return true;
    }

}
