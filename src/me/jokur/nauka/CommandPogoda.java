package me.jokur.nauka;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandPogoda implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pogoda")) {
            Player p = (Player) sender;
            World w = p.getWorld();
            w.setStorm(false);
            p.sendMessage(ChatColor.GREEN + "Piękna pogoda");
            return true;
        }
        return true;
    }
}
