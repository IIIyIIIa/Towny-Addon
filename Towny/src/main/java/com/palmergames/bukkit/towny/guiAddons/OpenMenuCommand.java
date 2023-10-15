package com.palmergames.bukkit.towny.guiAddons;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenMenuCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		Player player = (Player) commandSender;
		MenuEvents.nameTown = strings[1];
		player.openInventory(CreateMenu.instance.inventory);
		return true;
	}
	
}
