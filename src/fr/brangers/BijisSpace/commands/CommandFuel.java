package fr.brangers.BijisSpace.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.lone.itemsadder.Libs.nbt.nbtapi.NBTItem;
import fr.brangers.BijisSpace.Config;
import fr.brangers.BijisSpace.Main;
import fr.brangers.BijisSpace.inventory.InventoryFuel;

public class CommandFuel implements CommandExecutor {
	private Main main;
	public static InventoryFuel invfuel = new InventoryFuel();
	
	public CommandFuel(Main main) {
		this.main = main;
	}
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player player = (Player) sender;
            invfuel.createInventory(player);
            player.openInventory(invfuel.getInventory());
        }
		return true;
    }
}
