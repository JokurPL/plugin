package me.jokur.nauka;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHello implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(ChatColor.RED + "Witaj graczu");
        }
        else {
            sender.sendMessage(ChatColor.RED + "Nie jestes graczem");
        }
        return true;
    }

}
