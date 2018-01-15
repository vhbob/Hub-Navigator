package com.vhbob.pn;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AddLocation implements CommandExecutor {

	Plugin pl;

	public AddLocation(Plugin plugin) {
		pl = plugin;
	}

	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
		if (command.getName().equalsIgnoreCase("addnewitem")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.isOp() || p.hasPermission("hub.addnewitem")) {
					if (args.length == 2 && !p.getItemInHand().equals(null)
							&& !p.getItemInHand().getType().equals(Material.AIR) && isInt(args[0])) {
						if (Integer.parseInt(args[0]) > 0
								&& Integer.parseInt(args[0]) < pl.getConfig().getInt("inventory.size")) {
							int slot = Integer.parseInt(args[0]);
							slot--;
							if (!pl.getConfig().contains("items." + slot)) {
								pl.getConfig().set("items." + slot + ".world", p.getLocation().getWorld().getName());
								pl.getConfig().set("items." + slot + ".z", p.getLocation().getZ());
								pl.getConfig().set("items." + slot + ".y", p.getLocation().getY());
								pl.getConfig().set("items." + slot + ".x", p.getLocation().getX());
								pl.getConfig().set("items." + slot + ".yaw", p.getLocation().getYaw());
								pl.getConfig().set("items." + slot + ".pitch", p.getLocation().getPitch());
								pl.getConfig().set("items." + slot + ".name", args[1]);
								pl.getConfig().set("items." + slot + ".type", p.getItemInHand().serialize());
								pl.saveConfig();
								pl.reloadConfig();
								p.sendMessage(ChatColor.GREEN + "Slot " + (slot + 1) + " set to " + ChatColor.GOLD + ""
										+ ChatColor.BOLD + args[1]);
							} else {
								p.sendMessage(ChatColor.RED + "That slot is already taken up!");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Usage: /addnewitem [inventory slot 1-"
									+ pl.getConfig().getInt("inventory.size")
									+ "] [name] ALSO make sure you have an item in your hand! This item will be used in the navigator");
						}
					} else {
						p.sendMessage(ChatColor.RED
								+ "Usage: /addnewitem [inventory slot 1-45] [name] ALSO make sure you have an item in your hand! This item will be used in the navigator");
					}
				} else {
					p.sendMessage(ChatColor.RED + "Missing Permission: hub.addnewitem");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Only players can use this command");
			}
		}
		return false;
	}

	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
