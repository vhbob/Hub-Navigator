package com.vhbob.pn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetSpawnpoint implements CommandExecutor {

	Plugin pl;
	
	public SetSpawnpoint(Plugin plugin) {
		pl = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
		if (command.getName().equalsIgnoreCase("sethubspawn")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.isOp() || p.hasPermission("hub.setspawn")) {
					pl.getConfig().set("hub.spawnpoint.x", p.getLocation().getX());
					pl.getConfig().set("hub.spawnpoint.y", p.getLocation().getY());
					pl.getConfig().set("hub.spawnpoint.z", p.getLocation().getZ());
					pl.getConfig().set("hub.spawnpoint.world", p.getLocation().getWorld().getName());
					pl.saveConfig();
					pl.reloadConfig();
					p.sendMessage(ChatColor.GREEN + "Hub spawnpoint saved to your location!");
				} else {
					p.sendMessage(ChatColor.RED + "Error: Missing permission [ hub.setspawn ]");
				}
			} else {
				sender.sendMessage(ChatColor.RED + "This command is for players only!");
			}
		}
		return false;
	}
	
}
