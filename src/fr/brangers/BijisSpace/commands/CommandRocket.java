package fr.brangers.BijisSpace.commands;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.lone.itemsadder.Libs.nbt.nbtapi.NBTItem;
import dev.lone.itemsadder.api.ItemsAdder;

public class CommandRocket implements CommandExecutor  {

	private static ItemsAdder ia;
	@Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
            	ItemStack rocket = ia.getCustomItem("rocket");
            	NBTItem nbti = new NBTItem(rocket);
            	nbti.setDouble("Essence", 0.0);
            	
            	rocket = nbti.getItem();
            	
            	ItemMeta meta = rocket.getItemMeta();
            	meta.setLore(Arrays.asList("Carburant : " + nbti.getDouble("Essence").toString()));
            	rocket.setItemMeta(meta);
            	
            	player.getInventory().addItem(rocket);
            	player.sendMessage("La commande marche bien");  	
            } catch (Exception ex) {
            	player.sendMessage("L'item n'existe pas");  
            }
        }
		return true;
    }

}
