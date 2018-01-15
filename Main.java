package com.vhbob.pn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		getCommand("sethubspawn").setExecutor(new SetSpawnpoint(this));
		getCommand("addnewitem").setExecutor(new AddLocation(this));
		getCommand("deletemenuitem").setExecutor(new DeleteLocation(this));
		getCommand("changenavname").setExecutor(new ChangeNavName(this));
		getCommand("changeinvsize").setExecutor(new SetInvSize(this));
		Bukkit.getPluginManager().registerEvents(new TeleportViaNav(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
		Bukkit.getPluginManager().registerEvents(new UseNavigator(this), this);
		if (!getConfig().contains("inventory.size")) {
			getConfig().set("inventory.size", 45);
		}
		if (!getConfig().contains("navname")) {
			getConfig().set("navname", ChatColor.GOLD + "" + ChatColor.BOLD + "Hub Navigator");
		}
		saveConfig();
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Hub Navigator has been enabled!");
	}

	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Hub Navigator has been disabled!");
	}

	public int inventorySize() {
		return getConfig().getInt("inventory.size");
	}

	public String getNavName() {
		return getConfig().getString("navname");
	}

}
