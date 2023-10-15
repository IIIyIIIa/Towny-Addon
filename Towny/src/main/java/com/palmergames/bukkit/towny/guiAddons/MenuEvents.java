package com.palmergames.bukkit.towny.guiAddons;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.command.BaseCommand;
import com.palmergames.bukkit.towny.command.TownCommand;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.spawnlevel.SpawnLevel;
import com.palmergames.bukkit.towny.utils.MinecraftVersion;
import net.md_5.bungee.api.chat.ClickEvent;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class MenuEvents extends TownCommand implements Listener {
	public static String nameTown = "1";

	public MenuEvents(Towny instance) {
		super(instance);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) throws TownyException {
		Player player = (Player) event.getWhoClicked();
		Resident resident = BaseCommand.getResidentOrThrow(player.getName());
		
		if (event.getInventory() == CreateMenu.instance.inventory) {
			event.setCancelled(true);

			if (event.getCurrentItem() != null) {
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Инфо")) {
					event.getWhoClicked().sendMessage("Пожалуйста, выберите способ оплаты, что бы создать свой город.");
				}
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals("Ресурсы")) {
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

						TownCommand.newTown(player, nameTown, resident, true);
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
					player.closeInventory();

					TownCommand.newTown(player, nameTown, resident, false);
					
				} else {

				}
			} 
		}
	}

}