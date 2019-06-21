package me.jokur.nauka;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGm implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gm")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.isOp()) {
                    if (args.length < 1 || args.length > 2) {
                        player.sendMessage(ChatColor.DARK_RED + "Podaj wartość(cyfra): " + ChatColor.YELLOW + "1 - Survival, 2 - Creative, 3 - Adventure, 4 - Spectator");
                        return true;
                    } else if (args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(ChatColor.DARK_GRAY + "Tryb: " + ChatColor.YELLOW + "survival");
                        return true;
                    } else if (args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(ChatColor.DARK_GRAY + "Tryb: " + ChatColor.YELLOW + "przygody");
                        return true;
                    } else if (args[0].equalsIgnoreCase("3")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(ChatColor.DARK_GRAY + "Tryb: " + ChatColor.YELLOW + "kreatywny");
                        return true;
                    } else if (args[0].equalsIgnoreCase("4")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(ChatColor.DARK_GRAY + "Tryb: " + ChatColor.YELLOW + "obserwatora");
                        return true;
                    }
                    }   else {
                    player.sendMessage(ChatColor.RED + "Nie masz uprawnień, aby użyć tej komendy.");
                    return true;

                }
            }else {
                sender.sendMessage("Tylko gracze moga uzywac tej komendy");
                return true;
            }
        }
        return true;
    }
}
