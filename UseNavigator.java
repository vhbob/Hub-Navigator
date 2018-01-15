package com.vhbob.pn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class UseNavigator implements Listener {

	Plugin pl;

	public UseNavigator(Plugin plugin) {
		pl = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void openMenu(PlayerInteractEvent e) {
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			Player p = e.getPlayer();
			if (p.getItemInHand() != null && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(
					ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")))) {
				Inventory finalI = Bukkit.createInventory(null, pl.getConfig().getInt("inventory.size"),
						ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")));
				Inventory i1 = Bukkit.createInventory(null, pl.getConfig().getInt("inventory.size"),
						ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")));
				Inventory i2 = Bukkit.createInventory(null, pl.getConfig().getInt("inventory.size"),
						ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")));
				Inventory i3 = Bukkit.createInventory(null, pl.getConfig().getInt("inventory.size"),
						ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")));
				Inventory i4 = Bukkit.createInventory(null, pl.getConfig().getInt("inventory.size"),
						ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")));
				if (pl.getConfig().getConfigurationSection("items") != null) {
					for (String string : pl.getConfig().getConfigurationSection("items").getKeys(false)) {
						ItemStack item = ItemStack.deserialize(
								pl.getConfig().getConfigurationSection("items." + string + ".type").getValues(true));
						ItemMeta itemm = item.getItemMeta();
						itemm.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD
								+ pl.getConfig().getString("items." + string + ".name"));
						item.setItemMeta(itemm);
						int slotNumber = Integer.parseInt(string);
						int slotMod = slotNumber % 9;
						if (slotMod == 4) {
							i1.setItem(slotNumber - 4, item);
							i2.setItem(slotNumber - 3, item);
							i3.setItem(slotNumber - 2, item);
							i4.setItem(slotNumber - 1, item);
							finalI.setItem(slotNumber, item);
						} else if (slotMod == 0 || slotMod == 8) {
							finalI.setItem(slotNumber, item);
						} else if (slotMod == 1) {
							i4.setItem(slotNumber - 1, item);
							finalI.setItem(slotNumber, item);
						} else if (slotMod == 2) {
							i3.setItem(slotNumber - 2, item);
							i4.setItem(slotNumber - 1, item);
							finalI.setItem(slotNumber, item);
						} else if (slotMod == 3) {
							i2.setItem(slotNumber - 3, item);
							i3.setItem(slotNumber - 2, item);
							i4.setItem(slotNumber - 1, item);
							finalI.setItem(slotNumber, item);
						} else if (slotMod == 5) {
							i2.setItem(slotNumber + 3, item);
							i3.setItem(slotNumber + 2, item);
							i4.setItem(slotNumber + 1, item);
							finalI.setItem(slotNumber, item);
						} else if (slotMod == 6) {
							i3.setItem(slotNumber + 2, item);
							i4.setItem(slotNumber + 1, item);
							finalI.setItem(slotNumber, item);
						} else if (slotMod == 7) {
							i4.setItem(slotNumber + 1, item);
							finalI.setItem(slotNumber, item);
						}
					}
				}
				ItemStack blackPane = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
				p.openInventory(i1);
				for (int i = 0; i < i1.getSize(); i++) {
					if (i1.getItem(i) == null || i1.getItem(i).getType().equals(Material.AIR)) {
						i1.setItem(i, blackPane);
					}
				}
				
				pl.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
					@Override
					public void run() {
						p.openInventory(i2);
						for (int i = 0; i < i2.getSize(); i++) {
							if (i2.getItem(i) == null || i2.getItem(i).getType().equals(Material.AIR)) {
								i2.setItem(i, blackPane);
							}
						}
					}
				}, 2);
				pl.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
					@Override
					public void run() {
						p.openInventory(i3);
						for (int i = 0; i < i3.getSize(); i++) {
							if (i3.getItem(i) == null || i3.getItem(i).getType().equals(Material.AIR)) {
								i3.setItem(i, blackPane);
							}
						}
					}
				}, 4);
				pl.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
					@Override
					public void run() {
						p.openInventory(i4);
						for (int i = 0; i < i4.getSize(); i++) {
							if (i4.getItem(i) == null || i4.getItem(i).getType().equals(Material.AIR)) {
								i4.setItem(i, blackPane);
							}
						}
					}
				}, 6);
				pl.getServer().getScheduler().scheduleSyncDelayedTask(pl, new Runnable() {
					@Override
					public void run() {
						p.openInventory(finalI);
						for (int i = 0; i < finalI.getSize(); i++) {
							if (finalI.getItem(i) == null || finalI.getItem(i).getType().equals(Material.AIR)) {
								finalI.setItem(i, blackPane);
							}
						}
					}
				}, 8);
			}
		}
	}

	@EventHandler
	public void playerDrop(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().hasItemMeta()
				&& e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(
						ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("navname")))) {
			e.setCancelled(true);
		}
	}

}
