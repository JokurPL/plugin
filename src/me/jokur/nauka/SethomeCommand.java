package me.jokur.nauka;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class SethomeCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("sethome"))
            if (sender instanceof Player) {
                Player p = (Player) sender;
//                String uuid = p.getUniqueId().toString();
//                getConfig().set("uuid.world", p.getLocation().getWorld().getName());
//                getConfig().set("uuid.x", p.getLocation().getX());
//                getConfig().set("uuid.y", p.getLocation().getY());
//                getConfig().set("uuid.z", p.getLocation().getZ());
//                getConfig().set("uuid.pitch", p.getEyeLocation().getPitch());
//                getConfig().set("uuid.yaw", p.getEyeLocation().getYaw());
//                saveConfig();
            }
            else {
                sender.sendMessage("Tylko gracze moga uzywac tej komendy");
                return true;
            }


        return true;
    }

}

