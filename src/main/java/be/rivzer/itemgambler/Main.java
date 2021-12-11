package be.rivzer.itemgambler;

import be.rivzer.itemgambler.Listeners.InventoryClickEvent;
import be.rivzer.itemgambler.Listeners.PlayerInteract;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryClickEvent(), this);

        saveDefaultConfig();

        System.out.println("Plugin is opgestard!  \n");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
