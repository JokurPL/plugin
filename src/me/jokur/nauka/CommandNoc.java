package me.jokur.nauka;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandNoc implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if(command.getName().equalsIgnoreCase("noc")) {
            Player player = (Player) sender;
            player.getLocation().getWorld().setTime(13000);
            player.sendMessage(ChatColor.GREEN + "Nastała noc");
            return true;
        }
        return true;
    }

}
