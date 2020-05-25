package fr.brangers.BijisSpace;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import fr.brangers.BijisSpace.commands.CommandFuel;
import fr.brangers.BijisSpace.commands.CommandRocket;
import fr.brangers.BijisSpace.event.EventFuel;


public class Main extends JavaPlugin {
	public static Config config = new Config();
	@Override
	public void onEnable() {
		final PluginManager pluginManager = Bukkit.getServer().getPluginManager();
		Bukkit.getServer().getConsoleSender().sendMessage("Bijis Space Enabled");
		
		pluginManager.registerEvents(new Rocket(this), this);
		pluginManager.registerEvents(new EventFuel(this), this);
		this.getCommand("rocket").setExecutor(new CommandRocket());
		this.getCommand("fuel").setExecutor(new CommandFuel(this));
	}
	@Override
	public void onDisable() {
		Bukkit.getServer().getConsoleSender().sendMessage("Bijis Space Disabled");
	}
	@Override
	public void onLoad() {
	}
}
