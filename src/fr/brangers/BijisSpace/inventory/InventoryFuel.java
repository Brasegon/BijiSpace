package fr.brangers.BijisSpace.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class InventoryFuel implements IInventory {
	private static Inventory inv;

	@Override
	public void createInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "FUEL");
		ItemStack stack1 = new ItemStack(Material.BLUE_STAINED_GLASS);
        inventory.setItem(1, stack1);
        inventory.setItem(2, stack1);
        inventory.setItem(3, stack1);
		inv = inventory;
	}

	@Override
	public ItemStack createItems(Material material, String name, String... lore) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Inventory getInventory() {
		// TODO Auto-generated method stub
		return inv;
	}

}
