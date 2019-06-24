package me.jokur.nauka;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class CommandSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("spawn")) {
            Player p = (Player) sender;
            Location spawn = Bukkit.getServer().getWorlds().get(0).getSpawnLocation();
            p.teleport(spawn);
            p.sendMessage(ChatColor.DARK_AQUA + "Jesteś na spawnie");
            return true;
        }
        return true;
    }
}
