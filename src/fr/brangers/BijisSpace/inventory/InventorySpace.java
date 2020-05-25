package fr.brangers.BijisSpace.inventory;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class InventorySpace implements IInventory {
	
	private static Inventory inv;
	@Override
	public void createInventory(final Player player) {
        final Inventory inventory = Bukkit.createInventory((InventoryHolder)null, InventoryType.HOPPER, "TP");
        ItemStack stack1 = createItems(Material.BEACON, "Launch", "Permet de lancer la fusée");
        inventory.setItem(0, stack1);
        inv = inventory;
    }
	@Override
	public
	ItemStack createItems(Material material, String name, final String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		return item;
	}
	@Override
	public Inventory getInventory() {
		return inv;
	}
}
