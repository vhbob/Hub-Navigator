package com.vhbob.pn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetInvSize implements CommandExecutor {

	Plugin pl;

	public SetInvSize(Plugin plugin) {
		pl = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("changeinvsize")) {
				if (sender.hasPermission("hub.changeinvsize") || p.isOp()) {
					if (args.length == 1) {
						if (isInt(args[0])) {
							int size = Integer.parseInt(args[0]);
							if (size % 9 == 0 && size != 0) {
								pl.getConfig().set("inventory.size", size);
								pl.saveConfig();
								p.sendMessage(ChatColor.GREEN + "Inventory size changed to "
										+ pl.getConfig().getInt("inventory.size"));
							} else {
								p.sendMessage(
										ChatColor.RED + "The size must be a multiple of 9! (9, 18, 27, 36, etc.)");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Usage: /changeinvsize (size)");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Usage: /changeinvsize (size)");
					}
				} else {
					sender.sendMessage(ChatColor.RED + "Missing perm: hub.changeinvsize");
				}
			}
		}
		return false;
	};

	public boolean isInt(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
