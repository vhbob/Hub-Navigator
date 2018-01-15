package com.vhbob.pn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DeleteLocation implements CommandExecutor {

	Plugin pl;

	public DeleteLocation(Plugin plugin) {
		pl = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
		if (command.getName().equalsIgnoreCase("deletemenuitem")) {
			if (sender instanceof Player && sender.hasPermission("hub.deletemenuitem") || sender.isOp()) {
				Player p = (Player) sender;
				if (args.length == 1) {
					if (isInt(args[0])) {
						if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) < pl.getConfig().getInt("inventory.size")) {
							int slotDel = Integer.parseInt(args[0]);
							slotDel--;
							if (pl.getConfig().contains("items." + slotDel + ".type")) {
								pl.getConfig().set("items." + slotDel, null);
								p.sendMessage(ChatColor.RED + "That slot item has been deleted!");
							} else {
								p.sendMessage(ChatColor.RED + "There is nothing in that slot!");
							}
						} else {
							p.sendMessage(ChatColor.RED + "usage: /deletemenuitem (slot number 1 - " + pl.getConfig().getInt("inventory.size") + ")");
						}
					} else {
						p.sendMessage(ChatColor.RED + "usage: /deletemenuitem (slot number 1 - " + pl.getConfig().getInt("inventory.size") + ")");
					}
				} else {
					p.sendMessage(ChatColor.RED + "usage: /deletemenuitem (slot number 1 - " + pl.getConfig().getInt("inventory.size") + ")");
				}
			} else {
				sender.sendMessage(ChatColor.RED
						+ "You are either not a player or are missing the permission: hub.deletemenuitem");
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
