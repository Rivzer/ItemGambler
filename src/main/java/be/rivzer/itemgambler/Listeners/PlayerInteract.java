package be.rivzer.itemgambler.Listeners;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onClick0(NPCRightClickEvent e){

        NPC npc = e.getNPC();
        Player p = e.getClicker();

        if(npc.getName().equals("Item Gambler")){
            Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "Item Gambler");

            for (int i = 0; i < 5; i++){
                ItemStack g = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
                ItemMeta gm = g.getItemMeta();
                gm.setDisplayName(" ");
                g.setItemMeta(gm);

                inv.setItem(i, g);
            }

            ItemStack w = new ItemStack(Material.WOOL, 1, (byte) 14);
            ItemMeta wm = w.getItemMeta();
            wm.setDisplayName("Gamble Item?");
            w.setItemMeta(wm);

            ItemStack a = new ItemStack(Material.STAINED_GLASS_PANE, 1);
            ItemMeta am = a.getItemMeta();
            am.setDisplayName("Item");
            a.setItemMeta(am);

            inv.setItem(1, a);
            inv.setItem(3, w);

            p.openInventory(inv);

            InventoryClickEvent.opened.add(p.getUniqueId());

        }

    }

}
