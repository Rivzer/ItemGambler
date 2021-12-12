package be.rivzer.itemgambler.Listeners;

import be.rivzer.itemgambler.Logger;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        System.out.print(e.getClickedInventory());

        if(e.getClickedInventory() == null) return;

        if(e.getClickedInventory().getName().equalsIgnoreCase(Logger.color("&6&lItemGambler &7&l- &e&lMenu"))){
            ItemStack is = e.getCurrentItem();
            if(is == null) return;
            if(is.getType() == null) return;

            if(is.getType() == Material.STAINED_GLASS_PANE){
                e.setCancelled(true);
            }

            System.out.print("f");

            if(e.getClickedInventory().getItem(4) == null) return;

            if(e.getClickedInventory().getItem(4).getType() != Material.AIR){
                System.out.print("TEST");
            }
        }
    }

}
