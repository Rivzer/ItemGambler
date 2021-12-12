package be.rivzer.itemgambler.Guis;

import be.rivzer.itemgambler.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {

    public static void Menu(Player p){
        Inventory inv = Bukkit.createInventory(p, 9, Logger.color("&6&lItemGambler &7&l- &e&lMenu"));

        for (int i = 0; i < 9; i++){
            setGlass(inv, Material.STAINED_GLASS_PANE, i, " ", (short) 7);
        }

        setGlass(inv, Material.AIR, 4, "", (short) 7);

        p.closeInventory();
        p.openInventory(inv);
    }

    public static void setGlass(Inventory inv, Material mat, Integer slotnum, String name, short color){
        ItemStack item = new ItemStack(mat, 1, (short) color);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            meta.setDisplayName(Logger.color(name));
        }
        item.setItemMeta(meta);

        inv.setItem(slotnum, item);
    }

}
