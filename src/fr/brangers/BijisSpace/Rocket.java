package fr.brangers.BijisSpace;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import dev.lone.itemsadder.api.ItemsAdder;
import fr.brangers.BijisSpace.inventory.InventorySpace;

public class Rocket implements Listener {
	public static ItemStack rocket;
	public static ItemsAdder ia;
	public static InventorySpace invSpace;
	private static ArmorStand as;
	private int task;
	public static boolean isLimit = false;
	private Main main;
	
	public Rocket(Main main) {
		this.main = main;
		this.invSpace = new InventorySpace();
	}

	@EventHandler
    public void placeRocket(final PlayerInteractEvent playerInteractEvent) {
		try {
			ItemStack hand = playerInteractEvent.getPlayer().getInventory().getItemInMainHand();
			Player player = playerInteractEvent.getPlayer();
			Location add = playerInteractEvent.getClickedBlock().getLocation().add(0.5, 1.0, 0.5);
			if (playerInteractEvent.getClickedBlock() != null &&
					playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK &&
					ia.isCustomItem(hand) == true &&
					hand.getItemMeta().getDisplayName().equalsIgnoreCase("Rocket") &&
					playerInteractEvent.getHand() == EquipmentSlot.HAND && add.getWorld().getNearbyEntities(add, 0, 1, 0).size() == 0) {
				hand.setAmount(1);
				player.sendMessage("It is a custom items");
				ArmorStand armorStand = (ArmorStand) add.getWorld().spawnEntity(add, EntityType.ARMOR_STAND);
				armorStand.setCustomName("Rocket");
				armorStand.setVisible(false);
				armorStand.getEquipment().setHelmet(hand);
			}
		} catch (Exception ex) {}
	}
	
	@EventHandler
    public void breakRocket(final EntityDamageByEntityEvent entityDamageByEntityEvent) {
		try {
			if (entityDamageByEntityEvent.getDamager() instanceof Player && entityDamageByEntityEvent.getEntity() instanceof ArmorStand) {
				final ArmorStand armorStand = (ArmorStand)entityDamageByEntityEvent.getEntity();
				if (armorStand.getCustomName().equals("Rocket")) {
					EntityEquipment equip = armorStand.getEquipment();
					ItemStack item = equip.getHelmet();
					armorStand.remove();
					entityDamageByEntityEvent.getEntity().getLocation().getWorld().dropItemNaturally(entityDamageByEntityEvent.getEntity().getLocation(), item);
				}
			}
		} catch (Exception ex) {}
    }
	
	@EventHandler
    public void clickRocket(final PlayerInteractAtEntityEvent playerInteractAtEntityEvent) {
		Player player = playerInteractAtEntityEvent.getPlayer();
        if (playerInteractAtEntityEvent.getRightClicked() instanceof ArmorStand && ((ArmorStand)playerInteractAtEntityEvent.getRightClicked()).getCustomName().equals("Rocket")) {
            playerInteractAtEntityEvent.setCancelled(true);
            try {
                if (!playerInteractAtEntityEvent.getRightClicked().getPassenger().equals(playerInteractAtEntityEvent.getPlayer())) {
                    playerInteractAtEntityEvent.getRightClicked().setPassenger((Entity)playerInteractAtEntityEvent.getPlayer());
                }
            }
            catch (Exception ex) {
                playerInteractAtEntityEvent.getRightClicked().setPassenger((Entity)playerInteractAtEntityEvent.getPlayer());
                invSpace.createInventory(player);
                as = (ArmorStand) playerInteractAtEntityEvent.getRightClicked();
                player.sendMessage(as.getScoreboardTags().toString());
                player.openInventory(invSpace.getInventory());
            }
        }
    }
	
	@EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem() == null) return;
        if (e.getInventory().equals(invSpace.getInventory())) {
	        if(e.getCurrentItem().getType() == Material.BEACON){
	        	launchRocket(as, p);
	            e.setCancelled(true);
	            p.closeInventory();
	        }
        }
    }
	public void launchRocket(final ArmorStand armorStand, final Player player) {
        boolean b = false;
        final Location add = armorStand.getEyeLocation().add(0.0, 1.0, 0.0);
        final boolean allowFlight = player.getAllowFlight();
        final boolean flying = player.isFlying();
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
        	private final ArmorStand add = armorStand;
        	private final Player p = player;
			@Override
			public void run() {
				add.setVelocity(new Vector(0.0, 3.5, 0.0));
				p.setAllowFlight(true);
                p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, add.getLocation().add(0.0, -8.0, 0.0), 10);
                p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, add.getLocation().add(0.0, -8.0, 0.0), 10);
                p.getWorld().spawnParticle(Particle.SMOKE_LARGE, add.getLocation().add(0.0, -8.0, 0.0), 10);
                p.getWorld().playEffect(add.getLocation().add(0.0, -8.0, 0.0), Effect.MOBSPAWNER_FLAMES, 200, 1000);
                p.getWorld().playEffect(add.getLocation().add(0.0, -8.0, 0.0), Effect.EXTINGUISH, 10, 1000);
                if (player.getLocation().getY() > 256) {
                	isLimit = true;
                	Bukkit.getScheduler().cancelTask(task);
                }
			}
        	
        }, 0L, 2L);
	}
}
