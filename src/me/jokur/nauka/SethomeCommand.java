package me.jokur.nauka;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.sound.sampled.Line;

public class SethomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            String uuid = p.getUniqueId().toString();
            getConfig()
        }
        else {
            sender.sendMessage("Tylko gracze moga uzywac tej komendy");
            return true;
        }


        return true;
    }

}

