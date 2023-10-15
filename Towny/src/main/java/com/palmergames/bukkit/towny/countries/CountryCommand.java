package com.palmergames.bukkit.towny.countries;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyEconomyHandler;
import com.palmergames.bukkit.towny.command.countryClasses.Country;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

import static com.palmergames.bukkit.towny.command.countryClasses.Country.Countries;

public class CountryCommand implements TabExecutor {
	@Override
	public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (strings[0].equalsIgnoreCase("new") || strings[0].equalsIgnoreCase("create")) {
			//creation of a country
			Player player = (Player) commandSender;
			CountryMenuEvents.nameCountry = strings[1];
			player.openInventory(CountryCreateMenu.instance.inventory);

//			Countries.add(new Country())
			return true;
		}
		else if (strings[0].equalsIgnoreCase("") || strings[0].equalsIgnoreCase("info") || strings[0].equalsIgnoreCase(" ")) {
			//check info about the country
			Player player = (Player) commandSender;
			
			
			return true;
		}
		else if (strings[0].equalsIgnoreCase("del") || strings[0].equalsIgnoreCase("delete") || strings[0].equalsIgnoreCase("remove")) {
			//remove the country
			Player player = (Player) commandSender;


			return true;
		}
		
		return false;
	}

	@Nullable
	@Override
	public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
		if (strings.length == 1) {
			return Arrays.asList("new", "info", "delete");
		}
		return null;
	}
}
