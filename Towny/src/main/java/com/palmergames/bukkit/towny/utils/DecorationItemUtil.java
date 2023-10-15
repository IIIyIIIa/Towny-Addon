package com.palmergames.bukkit.towny.utils;

import net.citizensnpcs.api.persistence.ItemStackPersister;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DecorationItemUtil {
	public static ItemStack createDecorItem(Material material) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName(" ");

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static ItemStack createResourcesPay(Material material, String resources) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();
		
		itemMeta.setDisplayName("Ресурсы");
		itemMeta.setLore(Arrays.asList("Цена создания города: " + "\n" + resources));

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static ItemStack createMoneyPay(Material material, String money) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName("Монеты");
		itemMeta.setLore(Arrays.asList("Цена создания города: " + "\n" + money));

		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}

	public static ItemStack createInfoSign(Material material) {
		ItemStack itemStack = new ItemStack(material);
		ItemMeta itemMeta = itemStack.getItemMeta();

		itemMeta.setDisplayName("Инфо");
		itemMeta.setLore(Arrays.asList("Пожалуйста, выберите удобный для вас способ оплаты, что бы создать город."));
//		itemMeta.getPersistentDataContainer().set(NamespacedKey.fromString());
		
		itemStack.setItemMeta(itemMeta);
		return itemStack;
	}
}
