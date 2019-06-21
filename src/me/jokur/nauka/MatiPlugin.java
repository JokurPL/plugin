package me.jokur.nauka;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
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

public class MatiPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        getServer().getConsoleSender().sendMessage("Plugin " + ChatColor.AQUA + Bukkit.getPluginManager().getPlugin("MatiPlugin").getName() + ChatColor.WHITE + " wlaczony!");
        Bukkit.getWorlds().get(0).setThundering(false);

        getCommand("gracze").setExecutor(new CommandGracze());
        getCommand("dzien").setExecutor(new CommandDzien());
        getCommand("noc").setExecutor(new CommandNoc());
        getCommand("gm").setExecutor(new CommandGm());
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

}
