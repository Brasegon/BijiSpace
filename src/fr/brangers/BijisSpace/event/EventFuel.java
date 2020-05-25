package fr.brangers.BijisSpace.event;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import dev.lone.itemsadder.api.ItemsAdder;
import fr.brangers.BijisSpace.Main;
import fr.brangers.BijisSpace.commands.CommandFuel;
import fr.brangers.BijisSpace.inventory.InventoryFuel;
import fr.brangers.BijisSpace.inventory.InventorySpace;

public class EventFuel implements Listener {

	private Main main;
	private ItemsAdder ia;
	private InventoryFuel invfuel = CommandFuel.invfuel;

	public EventFuel(Main main) {
		this.main = main;
	}
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) return;
        if (e.getInventory().equals(invfuel.getInventory())) {
        	if (e.getCurrentItem().getType() == Material.BLUE_STAINED_GLASS) {
        		e.setCancelled(true);
        	} if (ia.isCustomItem(e.getInventory().getItem(0))) {
        		if (e.getCurrentItem().getType() == Material.BLUE_STAINED_GLASS) {
            		p.sendMessage("Ajout d'essense");
	        	}
	        }
	    }
	}
	@EventHandler
    public void onInventoryClose(InventoryCloseEvent e){
		Player p = (Player) e.getPlayer();
		if (e.getInventory().equals(invfuel.getInventory())) {
			if (p.getInventory().getSize() >= 36) {
				p.getWorld().dropItemNaturally(p.getLocation(), e.getInventory().getItem(0));
			} else {
				
				p.getInventory().addItem(e.getInventory().getItem(0));
			}
		}
	}
}
