package me.jokur.nauka;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class CommandDzien implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if(command.getName().equalsIgnoreCase("dzien")) {
            Player player = (Player) sender;
            player.getLocation().getWorld().setTime(1000);
            player.sendMessage(ChatColor.GREEN + "Nastał dzien");
            return true;
        }
        return true;
    }

}
