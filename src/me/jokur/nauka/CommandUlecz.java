package me.jokur.nauka;

import net.minecraft.server.v1_14_R1.ChatComponentContextual;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUlecz implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("ulecz")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                double actually_hp = player.getHealth();
                if (actually_hp < 20) {
                    player.setHealth(20);
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "Zostałeś uzdrowiony");
                    return true;
                } else {
                    sender.sendMessage(ChatColor.BLUE + "Nie potrzebujesz pomocy :D");
                    return true;
                }

            } else {
                sender.sendMessage("Tylko gracze moga uzywac tej komendy");
            }
        }

        return true;
    }

}
