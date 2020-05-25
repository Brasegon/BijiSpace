package fr.brangers.BijisSpace.inventory;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IInventory {
	public void createInventory(final Player player);
	public ItemStack createItems(Material material, String name, final String... lore);
	public Inventory getInventory();
}
