package com.vhbob.pn;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ChangeNavName implements CommandExecutor {

	Plugin pl;

	public ChangeNavName(Plugin plugin) {
		pl = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String name, String[] args) {
		if (cmd.getName().equalsIgnoreCase("changenavname")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("hub.changenavname") || p.isOp()) {
					String navName = "";
					for (String s : args) {
						if (navName == null || navName.equals("")) {
							navName += s;
						} else {
							navName += " " + s;
						}
					}
					pl.getConfig().set("navname", navName);
					pl.saveConfig();
					p.sendMessage(ChatColor.GREEN + "The name has been saved!");
				}else {
					p.sendMessage(ChatColor.RED + "Missing permission: hub.changenavname" );
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You are not a player!");
			}
		}
		return false;
	}

}
