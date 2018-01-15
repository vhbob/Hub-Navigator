package com.vhbob.pn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class TeleportViaNav implements Listener {

	Plugin pl;

	public TeleportViaNav(Plugin plugin) {
		pl = plugin;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (e.getClickedInventory().getName()
				.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")))) {
			if (e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName() != null) {
				if (pl.getConfig().contains("items." + e.getSlot() + ".world")) {
					Double x = pl.getConfig().getDouble("items." + e.getSlot() + ".x");
					Double y = pl.getConfig().getDouble("items." + e.getSlot() + ".y");
					Double z = pl.getConfig().getDouble("items." + e.getSlot() + ".z");
					float yaw = pl.getConfig().getInt("items." + e.getSlot() + ".yaw");
					float pitch = pl.getConfig().getInt("items." + e.getSlot() + ".pitch");
					if (e.getWhoClicked() instanceof Player) {
						Player p = (Player) e.getWhoClicked();
						Location l = new Location(
								Bukkit.getWorld(pl.getConfig().getString("items." + e.getSlot() + ".world")), x, y, z,
								(float) yaw, (float) pitch);
						p.teleport(l);
					}
				}
			}
			e.setCancelled(true);
		} else if (e.getClickedInventory().getName()
				.equalsIgnoreCase(ChatColor.AQUA + "" + ChatColor.BOLD + "Hub Navagation")) {
			e.setCancelled(true);
		}
	}

}
