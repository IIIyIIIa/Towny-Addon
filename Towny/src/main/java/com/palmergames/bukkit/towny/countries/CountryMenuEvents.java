package com.palmergames.bukkit.towny.countries;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownyEconomyHandler;
import com.palmergames.bukkit.towny.command.BaseCommand;
import com.palmergames.bukkit.towny.command.TownCommand;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.guiAddons.CreateMenu;
import com.palmergames.bukkit.towny.object.Resident;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class CountryMenuEvents implements Listener {
	public static String nameCountry = "1";
	public static int amount = 1000;
	public static boolean successful_charge = false;

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		World world = player.getWorld();
		
		if (event.getInventory() == CountryCreateMenu.instance.inventory) {
			event.setCancelled(true);

			if (event.getCurrentItem() != null) {
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Инфо")) {
					event.getWhoClicked().sendMessage("Пожалуйста, выберите способ оплаты, что бы создать свою страну.");
					event.setCancelled(true);
				}
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Ресурсы")) {
					event.setCancelled(true);
					boolean cobblestone_bool = false;
					boolean oak_log_bool = false;
					boolean coal_bool = false;
					for (int cobblestone = 32; cobblestone <= 1728; cobblestone++) {
						if (event.getWhoClicked().getInventory().contains(new ItemStack(Material.COBBLESTONE, cobblestone))) cobblestone_bool = true;
					}
					for (int oak_log = 16; oak_log <= 1728; oak_log++) {
						if (event.getWhoClicked().getInventory().contains(new ItemStack(Material.OAK_LOG, oak_log))) oak_log_bool = true;
					}
					for (int coal = 8; coal <= 1728; coal++) {
						if (event.getWhoClicked().getInventory().contains(new ItemStack(Material.COAL, coal))) coal_bool = true;
					}
					if (cobblestone_bool && oak_log_bool && coal_bool) {
						player.closeInventory();

						event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.COBBLESTONE, 32));
						event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.OAK_LOG, 16));
						event.getWhoClicked().getInventory().removeItem(new ItemStack(Material.COAL, 8));

//						TownCommand.newTown(player, nameTown, resident, true);
//						Country creation method here (must be!)
					} else {
						player.closeInventory();

						event.getWhoClicked().sendMessage(ChatColor.DARK_RED + "Кажется, у вас недостаточно ресурсов для этого.");
						event.getWhoClicked().sendMessage(" ");
						event.getWhoClicked().sendMessage(ChatColor.BLUE + "Для создания города требуется: ");
						event.getWhoClicked().sendMessage( ChatColor.DARK_GREEN + "1. " + ChatColor.DARK_AQUA + ChatColor.UNDERLINE + "32 " + ChatColor.RESET + ChatColor.DARK_AQUA + "Булыжника");
						event.getWhoClicked().sendMessage( ChatColor.DARK_GREEN + "2. " + ChatColor.DARK_AQUA + ChatColor.UNDERLINE + "16 " + ChatColor.RESET + ChatColor.DARK_AQUA + "Дуба");
						event.getWhoClicked().sendMessage( ChatColor.DARK_GREEN + "3. " + ChatColor.DARK_AQUA + ChatColor.UNDERLINE + "8 " + ChatColor.RESET + ChatColor.DARK_AQUA + "Угля");
					}
				}
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Монеты")) {
					event.setCancelled(true);
					if (TownyEconomyHandler.hasEnough(player.getName(), amount, world)) {
						successful_charge = TownyEconomyHandler.subtract(player.getName(), amount, world);
						player.sendMessage(ChatColor.GOLD + "[Towny] " + ChatColor.DARK_GREEN + "Вы успешно создали государство, которое называется " + nameCountry);
						//	TownCommand.newTown(player, nameTown, resident, false);
						//	Country creation method here again
					} else {
						player.sendMessage(ChatColor.GOLD + "[Towny] " + ChatColor.DARK_RED + "У вас недостаточно средств!");
					}
					player.closeInventory();
					

				}
			}
		}
	}
	
}
