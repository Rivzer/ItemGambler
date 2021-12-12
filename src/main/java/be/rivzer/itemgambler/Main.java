package be.rivzer.itemgambler;

import be.rivzer.itemgambler.Commands.OpenMenu;
import be.rivzer.itemgambler.Config.Config;
import be.rivzer.itemgambler.Listeners.InventoryClick;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        Config.createCustomConfig1();

        System.out.print(Logger.color("&aPlugin &2ItemGambler &ais opgestart"));

        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClick(), this);

        new OpenMenu(this);

    }

}
