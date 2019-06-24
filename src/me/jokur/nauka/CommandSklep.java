package me.jokur.nauka;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;

public class CommandSklep implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("sklep")) {
            Player p = (Player) sender;
            World w =  Bukkit.getWorlds().get(0);
            double x = 98.5;
            double y = 85;
            double z = -319.5;
            double yaw = 90;
            double pitch = -2;
            Location sklep = new Location(w, x,y,z,(float)yaw,(float)pitch);
            p.teleport(sklep);
            p.sendMessage(ChatColor.GREEN + "Jesteś w sklepie");
            return true;
        }
        return true;
    }
}
