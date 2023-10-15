package com.palmergames.bukkit.towny.command.countryClasses;

import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.countries.CountryCommand;
import net.bytebuddy.build.Plugin;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Country {
	
	public static List<Country> Countries = new ArrayList<>();
	public static HashMap<String, String> map = new HashMap<>();
	
	private String name = "";
	private int balance = 0;
	private Boolean have = false;
	private World world;
	private Player owner;
	private static ArrayList<String> participantsString = new ArrayList<>();
	private static ArrayList<String> friendsCountriesString = new ArrayList<>();
	private static ArrayList<String> enemiesCountriesString = new ArrayList<>();

	public static void showCountryInfo(Country country) {
		Player player = country.getOwner();
		if (!country.have) { //ПЕРЕДЕЛАТЬ ЧЕРЕЗ ХЕШМАП
			player.sendMessage("К сожалению, у вас еще нет государства. Создайте его через команду /country new <имя>");
		} else {
			StringBuilder help = new StringBuilder();
			
			
			
			player.sendMessage("Информация о вашем государстве: "); //ПЕРЕДЕЛАТЬ, ОПЯТЬ ЖЕ!
			player.sendMessage("Название: " + country.getName());
			player.sendMessage("Баланс: " + country.getBalance());
			player.sendMessage("Правитель: " + country.getOwner());
			for (String name : participantsString) {
				help.append(name);
			}
			player.sendMessage("Жители: " + help.toString());
			help.delete(0, help.length());
			for (String name : friendsCountriesString) {
				help.append(name);
			}
			player.sendMessage("Союзники: " + help.toString());
			help.delete(0, help.length());
			for (String name : enemiesCountriesString) {
				help.append(name);
			}
			player.sendMessage("Враги: " + help.toString());
			help.delete(0, help.length());
		}
	}

	public static void deleteCountry(Country country) {
		//todo
	}

	public static void createCountry(Country country) {
		map.put(country.getOwner().getName(), country.getName());
		Countries.add(country);
	}
	

	public Country(String name) {
		this.name = name;
	}

	public Country(String name, int balance, Boolean have, World world, Player owner) {
		this.name = name;
		this.balance = balance;
		this.have = have;
		this.world = world;
		this.owner = owner;
	}

	public static void saveCountries() throws IOException {
		File folder = new File(Towny.getPlugin().getDataFolder().getAbsolutePath() + "/countries");
		
		folder.mkdirs();
		
		for (Country country : Countries) {
			File file = new File(folder.getAbsolutePath() + "/" + country.getName() + ".yml");
			
			file.createNewFile();

			YamlConfiguration configuration = new YamlConfiguration();
			configuration.set("name", country.getName());
			configuration.set("balance", country.getBalance());
			configuration.set("have", true);
			configuration.set("world", country.getWorld());
			configuration.set("owner", country.getOwner());
			configuration.set("participantsString", country.getParticipantsString());
			configuration.set("friendsCountriesString", country.getFriendsCountriesString());
			configuration.set("enemiesCountriesString", country.getEnemiesCountriesString());
			
			configuration.save(file);
		}
	}
	
	public static void loadCountries() throws IOException{
		File folder = new File(Towny.getPlugin().getDataFolder().getAbsolutePath() + "/countries");
		
		if (folder.exists()) {
			for (File file : folder.listFiles()) {
				YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
				
				Country country = new Country(file.getName().replaceAll(".yml", ""), configuration.getInt("balance"), true, (World) configuration.get("world"), (Player) configuration.get("owner"));
				
				country.participantsString = country.getParticipantsString();
				country.friendsCountriesString = country.getFriendsCountriesString();
				country.enemiesCountriesString = country.getEnemiesCountriesString();
				
				country.createCountry(country);
			}
		}
	}

	public Country(String name, int balance, Boolean have, World world, Player owner, ArrayList<String> participantsString, ArrayList<String> friendsCountriesString, ArrayList<String> enemiesCountriesString) {
		this.name = name;
		this.balance = balance;
		this.have = have;
		this.world = world;
		this.owner = owner;
		this.participantsString = participantsString;
		this.friendsCountriesString = friendsCountriesString;
		this.enemiesCountriesString = enemiesCountriesString;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Boolean getHave() {
		return have;
	}

	public void setHave(Boolean have) {
		this.have = have;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public ArrayList<String> getParticipantsString() {
		return participantsString;
	}

	public void setParticipantsString(ArrayList<String> participantsString) {
		this.participantsString = participantsString;
	}

	public ArrayList<String> getFriendsCountriesString() {
		return friendsCountriesString;
	}

	public void setFriendsCountriesString(ArrayList<String> friendsCountriesString) {
		this.friendsCountriesString = friendsCountriesString;
	}

	public ArrayList<String> getEnemiesCountriesString() {
		return enemiesCountriesString;
	}

	public void setEnemiesCountriesString(ArrayList<String> enemiesCountriesString) {
		this.enemiesCountriesString = enemiesCountriesString;
	}
}
