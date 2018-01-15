package com.vhbob.pn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class PlayerJoin implements Listener {

	Plugin pl;

	public PlayerJoin(Plugin plugin) {
		pl = plugin;
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (pl.getConfig().contains("hub.spawnpoint.world")) {
			p.teleport(new Location(pl.getServer().getWorld(pl.getConfig().getString("hub.spawnpoint.world")),
					pl.getConfig().getDouble("hub.spawnpoint.x"), pl.getConfig().getDouble("hub.spawnpoint.y"),
					pl.getConfig().getDouble("hub.spawnpoint.z")));
		}
		ItemStack nav = new ItemStack(Material.COMPASS);
		ItemMeta navm = nav.getItemMeta();
		navm.setDisplayName(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")));
		nav.setItemMeta(navm);
		p.getInventory().setItem(0, nav);
	}

}
