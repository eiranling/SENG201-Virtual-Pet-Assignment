package Game;
import java.util.ArrayList;
import java.util.Observable;

import pets.Pets;

/**
 * Class which is part of the MVC model. Holds all information related to the current game being played. 
 * Stores the array of players as well as being called by the GameGUI when player-pet interactions occur.
 */
public class GameModel extends Observable {

	private ArrayList<Player> players;
	private int numDays;
	
	/**
	 * Constructor which only creates an empty list for storing players. One the player set up 
	 * has finished in the GameGUI, players will be added to the 'players' ArrayList. 
	 */
	public GameModel() {
		players = new ArrayList<Player>();
	}
	
	/**
	 * Sets the number of days, value is obtained after game set up has finished from GameGUI.
	 * 
	 * @param daysInput Number of days the game will be played for.
	 */
	public void setDays(int daysInput){
		numDays = daysInput;
	}
	
	/**
	 * Getter which returns the number of days.
	 * 
	 * @return The number of days the game will be played for.
	 */
	public int getDays(){
		return numDays;
	}
	
	/**
	 * Getter which returns the ArrayList of players. 
	 * 
	 * @return Returns the ArrayList of players playing the game.
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * Getter which returns the ArrayList of pets belong to a given player.
	 * 
	 * @param player The player which the pets belong to.
	 * @return The ArrayList of pets which the player owns..
	 */
	public ArrayList<Pets> getPlayerPets(Player player) {
		return player.getPetArray();
	}
	
	/**
	 * Getter for obtaining the player's name.
	 * 
	 * @param player Player whose name we want.
	 * @return The player's name.
	 */
	public String getPlayerName(Player player) {
		return player.getName();
	}
	

	
	/**
	 * Creates and adds Players with an empty pet array. Pets will be added later after
	 * the pet set up finishes in the GameGUI.
	 * 
	 * @param names The names of the players who are going to play the game.
	 */
	public void addPlayers(String[] names) {
		int numPlayers = names.length;
		players.clear(); //clears the list
		
		for(int i = 0; i < numPlayers; i++) {
			String playerName = names[i];
			
			if (!(playerName == null)){
				players.add(new Player(playerName));
			}
		}	
	}
	
		
	
	/** Ends the day for the player, resetting each pet's action. Each pet will also be subjected
	 * to a possibility of a random event occurring.
	 * 
	 * @param player Player whose pet's actions will be reset/try for random events.
	 */
	public void endDayForPlayer(Player player){
		for (Pets pet : player.getPetArray()){
			if (pet.checkSick()){
				pet.takeSickDamage();
			}
			if (!pet.checkSick()){
				pet.getsSick();
			}
			if (!pet.checkMisbehaving()){
				pet.willMisbehave();
			}
			pet.becomeHungry();
			pet.refreshActions();
		}
	}
	
	/**
	 * Method to heal the pet using a medicinal item.
	 * 
	 * @param player Player who's inventory to use the item from.
	 * @param pet Pet to heal
	 * @param med Medicine item to use.
	 * @return A message that informs the user of what has happened.
	 */
	public String healPet(Player player, Pets pet, Item med){
		return med.use(player, pet);
	}
	
	/**
	 * Method to (try to) revive a dead pet. 
	 * 
	 * Note: A pet can only be revived once.
	 * 
	 * @param pet Pet to attempt to revive.
	 * @return A message that informs the user of what has happened, and if the pet was able to be revived.
	 */
	public String revivePet(Pets pet){
		return pet.revive();
	}

	
	/**
	 * Method which displays all medicine items in the player's inventory.
	 * 
	 * @param player The player whose inventory is being searched.
	 * @return The ArrayList of medical items found in the player's inventory.
	 */
	public ArrayList<Item> getMedItems(Player player) {
		ArrayList<Item> medItems = new ArrayList<Item>();
		for(Item medicineItem : player.getInventory()) {
			if(medicineItem.getRestoreType() == 1) {
				medItems.add(medicineItem);
			}
		}
		return medItems;
	}
	
	
	/**
	 * Player lets a pet use the bathroom.
	 * 
	 * @param player The player who owns the pet.
	 * @param pet The pet let out to use the toilet.
	 * @return A message to inform the player of what has happened.
	 */
	public String takeToToilet(Player player, Pets pet) {
		return player.takeToToilet(pet);
		
	}
	
	/**
	 * The player plays with a pet using a toy.
	 * 
	 * @param player The player which plays with their pet.
	 * @param pet The pet which plays with the toy
	 * @param toy The toy which will be used.
	 * @return A message to inform the player of what has happened.
	 */
	public String playWithPet(Player player, Pets pet, Item toy) {
		return toy.use(player, pet);
	}
	
	/**
	 * Getter which returns all the toys from the players inventory.
	 * 
	 * @param player Player whose inventory will be searched.
	 * @return An ArrayList of toys belonging to the player.
	 */
	public ArrayList<Item> getToyItems(Player player) {
		ArrayList<Item> toyItems = new ArrayList<Item>();
		for(Item toyItem : player.getInventory()) {
			if(toyItem.getRestoreType() == 3) {
				toyItems.add(toyItem);
			}
		}
		return toyItems;
	}
	
	
	/**
	 * Player puts a pet to sleep.
	 * 
	 * @param player Player whose pet will be put to sleep (not the dead kind of sleeping).
	 * @param pet The pet who will be put to sleep.
	 * @return A message that will inform the player of what has happened. 
	 */
	public String putPetToSleep(Player player, Pets pet) {
			return player.rest(pet);
	}
	
	/**
	 * Shows all the food items in the player's inventory.
	 * 
	 * @param player Player whose inventory will be searched for food items.
	 * @return An ArrayList of food items the player owns.
	 */
	public ArrayList<Item> getFoodItems(Player player) {
		ArrayList<Item> foodItems = new ArrayList<Item>();
		for(Item foodItem : player.getInventory()) {
			if(foodItem.getRestoreType() == 2) {
				foodItems.add(foodItem);
			}
		}
		return foodItems;
	}
	

	/**
	 * Method for a player will feed a pet.
	 * 
	 * @param player Player that will feed a pet.
	 * @param pet Pet that will be fed.
	 * @param food The food item that will be fed to the pet.
	 * @return The message that will inform the user of what has happened.
	 */
	public String feedPet(Player player, Pets pet, Item food) {
				return (food.use(player, pet));
	}
	
	
	/**
	 * Calculate the score for each player. Score is calculated by multiplying each of the pet's stats by a multiplier
	 * then adding it to the player's score. Penalties are given if the pet has died/is dead/is sick/is misbehaving.
	 */
	public void calculateScore() {
		double multiplier = 1;
		double penalty = 100;
		for(Player player : players) {
			double score = 0;
			score += player.getMoney() * multiplier;
			
			for(Pets pet : player.getPetArray()) {
				score += pet.getEnergy() * multiplier;
				score += pet.getHappiness() * multiplier;
				score += pet.getHealth() * multiplier;
				score += pet.getHunger() * multiplier;
				score += pet.getNeedForToilet() * multiplier;
				
				if(pet.checkDead()) {
					score -= penalty * 2; //Pet has died twice
				} else if (pet.checkDied()) {
					score -= penalty; //Pet died once
				}
				if(pet.checkMisbehaving()) { 
					score -= penalty;
				}
				if(pet.checkSick()) {
					score -= penalty;
				}
				
			}
			player.setScore(score);
		}
	}
}
