package be.rivzer.itemgambler.Listeners;

import be.rivzer.itemgambler.Logger;
import be.rivzer.itemgambler.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class InventoryClickEvent implements Listener {

    private Main plugin = Main.getPlugin(Main.class);
    private Inventory inv = null;
    private HashMap<UUID, ItemStack> item = new HashMap<>();
    private HashMap<UUID, Integer> plaats = new HashMap<>();
    private HashMap<UUID, Integer> ammount = new HashMap<>();
    public static ArrayList<UUID> opened = new ArrayList<>();

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(opened.contains(e.getPlayer().getUniqueId())){
            opened.remove(e.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void invClick(org.bukkit.event.inventory.InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();

        if(e.getClickedInventory() == null) return;
        if(!opened.contains(p.getUniqueId())) return;

        if(e.getClickedInventory().getName().equalsIgnoreCase("container.inventory")){
            ItemStack is = e.getCurrentItem();

            if(is.getType().equals(Material.AIR)) return;

            inv = Bukkit.createInventory(null, InventoryType.HOPPER, "Item Gambler");

            for (int i = 0; i < 5; i++){
                ItemStack g = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
                ItemMeta gm = g.getItemMeta();
                gm.setDisplayName(" ");
                g.setItemMeta(gm);

                inv.setItem(i, g);
            }

            ItemStack w = new ItemStack(Material.WOOL, 1, (byte) 5);
            ItemMeta wm = w.getItemMeta();
            wm.setDisplayName("Gamble Item?");
            w.setItemMeta(wm);

            item.put(p.getUniqueId(), is);
            plaats.put(p.getUniqueId(), e.getRawSlot());
            ammount.put(p.getUniqueId(), is.getAmount());

            inv.setItem(1, is);
            inv.setItem(3, w);

            p.openInventory(inv);

            opened.remove(p.getUniqueId());
        }

        if(e.getClickedInventory().getName().equalsIgnoreCase("Item Gambler")){
            e.setCancelled(true);

            ItemStack is = e.getCurrentItem();

            if(is.getType().equals(Material.AIR)) return;

            assert inv != null;
            if(inv.getItem(1).getType().equals(Material.AIR) || inv.getItem(1).getType().equals(Material.STAINED_GLASS_PANE)) return;

            if(is.getType().equals(Material.WOOL) && is.getItemMeta() != null){
                if(is.getItemMeta().getDisplayName() != null){
                    p.closeInventory();

                    Random random = new Random();
                    int chance = random.nextInt(101);

                    if (chance <= plugin.getConfig().getInt("Settings.Chance")) {

                        p.sendMessage(Logger.color("&2Uw item is succesvol gegambelt! En word nu verdubbeld!"));

                        p.getInventory().addItem(item.get(p.getUniqueId()));

                        item.remove(p.getUniqueId());
                        plaats.remove(p.getUniqueId());
                        ammount.remove(p.getUniqueId());

                    }
                    else{

                        p.sendMessage(Logger.color("&cUw item is kwijt gegambelt! U bent u item nu verloren!"));

                        item.get(p.getUniqueId()).setAmount(ammount.get(p.getUniqueId())-ammount.get(p.getUniqueId()));

                        item.remove(p.getUniqueId());
                        plaats.remove(p.getUniqueId());
                        ammount.remove(p.getUniqueId());

                    }

                    if(opened.contains(p.getUniqueId())){
                        opened.remove(p.getUniqueId());
                    }
                }
            }
        }

    }

}
