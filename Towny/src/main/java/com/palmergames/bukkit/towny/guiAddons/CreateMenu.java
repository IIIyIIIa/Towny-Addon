package com.palmergames.bukkit.towny.guiAddons;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import com.palmergames.bukkit.towny.utils.DecorationItemUtil;

import java.util.Arrays;
import java.util.List;

public class CreateMenu {
	public static CreateMenu instance = new CreateMenu();
	public final Inventory inventory = Bukkit.createInventory(null, 27, "Выберите способ оплаты");
	
	private List<ItemStack> DecorationItems = Arrays.asList(
		DecorationItemUtil.createDecorItem(Material.BLACK_STAINED_GLASS_PANE)
	);
	
	private List<ItemStack> MoneyItems = Arrays.asList(
		DecorationItemUtil.createMoneyPay(Material.SUNFLOWER, "500$")
	);

	private List<ItemStack> ResourcesItems = Arrays.asList(
		DecorationItemUtil.createResourcesPay(Material.DIAMOND, "32 Булыжника, 16 брёвен дуба, и 8 угля.")
	);
	private List<ItemStack> InfoItems = Arrays.asList(
		DecorationItemUtil.createInfoSign(Material.OAK_SIGN)
	);
	
	private CreateMenu() {
		for (int i = 0; i < inventory.getSize(); i++) {
			if (i == 16) {
				inventory.setItem(i, ResourcesItems.get(0));
			} else if (i == 10) {
				inventory.setItem(i, MoneyItems.get(0));
			} else if (i == 13) {
				inventory.setItem(i, InfoItems.get(0));
			} else {
				inventory.setItem(i, DecorationItems.get(0));
			}
		}
	}
}
