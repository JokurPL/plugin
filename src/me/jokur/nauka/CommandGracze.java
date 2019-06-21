package me.jokur.nauka;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getServer;

public class CommandGracze implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lable, String[] args) {
        if(command.getName().equalsIgnoreCase("gracze")) {
            if (sender instanceof Player) {
                int playersOnline = getServer().getOnlinePlayers().size();
                int maxPlayers = getServer().getMaxPlayers();
                sender.sendMessage("Ilość graczy: " + ChatColor.AQUA + playersOnline + "/" + maxPlayers);
            }
        }
        return true;
    }

}
