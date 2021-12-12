package be.rivzer.itemgambler.Commands;

import be.rivzer.itemgambler.Guis.Menu;
import be.rivzer.itemgambler.Logger;
import be.rivzer.itemgambler.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenMenu implements CommandExecutor {

    private Main plugin;

    public OpenMenu(Main plugin){
        this.plugin = plugin;
        plugin.getCommand("itemgambler").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(Logger.color("&f---------------------"));
            sender.sendMessage(Logger.color("&cDeze commands zijn niet bedoeld voor de console!"));
            sender.sendMessage(Logger.color("&f---------------------"));
            return true;
        }

        Player p = (Player) sender;

        if(args.length == 0){
            if(p.hasPermission("itemgambler.use")){
                Menu.Menu(p);
            }
            return true;
        }

        return false;
    }

}
