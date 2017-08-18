package Game;
import java.util.ArrayList;

import pets.Pets;

/**
 * Player class acting as a model, storing a player's name, score, inventory, pets and their money.
 * 
 * Player's score is determined by a pet's stat percentages as well as how much money the player
 * has left at the end of the game. If a pet has died, the player will be penalized. 
 * 
 * Player can also interact with pets, such as feeding,playing, disciplining, healing, putting a pet to sleep and 
 * letting a pet use the toilet.
 * 
 * @author Patrick Ma
 */
public class Player{

	private ArrayList<Pets> pets;
	private double score;
	private ArrayList<Item> inventory;
	private String name;
	private double money;
	
	/**
	 * Constructor to create player, given a name and Array of pets. Used for testing, not in the game logic.
	 * 
	 * @param petsInput The given ArrayList of the player's pets from from GameGUI during player set up. 
	 * @param nameInput Name the player.
	 */
	public Player(ArrayList<Pets> petsInput, String nameInput) {
		score = 0;
		pets = petsInput;
		for (Pets pet : pets){
			pet.setOwner(this);
		}
		name = nameInput;
		money = 1000;
		inventory = new ArrayList<Item>();
	}
	
	/**
	 * Overloaded constructor to create a player base, with no pets. The pest will be allocated
	 * after the player has chosen them in the pet set up, determined by the controller in GameGUI.
	 * 
	 * @param nameInput The name of the player.
	 */
	public Player(String nameInput){
		score = 0;
		pets = new ArrayList<Pets>();
		name = nameInput;
		money = 1000;
		inventory = new ArrayList<Item>();
	}
	
	/**
	 * Set a player an ArrayList of their chosen pets.
	 * 
	 * @param petsInput ArrayList of the player's chosen pets.
	 */
	public void setPets(ArrayList<Pets> petsInput){
		pets = petsInput;
		
	}
	
	/**
	 * Method to find the index of a specific item in the player's inventory.
	 * -1 is returned if the item can not be found. 
	 * 
	 * @param selectedItem -- item to find the index of.
	 * @return The index of the item.
	 */
	public int findItemIndex(Item selectedItem){
		for (Item item : getInventory()){
			if (item.equals(selectedItem)) {
				return getInventory().indexOf(item);
			}
		}
		//returns -1 if can't find it.
		return -1;
	}
	/**
	 * Getter for used obtain the player's chosen pets. 
	 * @return ArrayList of the player's chosen pets.
	 */
	public ArrayList<Pets> getPetArray() {
		return pets;
	}
	
	/**
	 * Returns the inventory of the player.
	 * 
	 * @return An ArrayList of the players inventory.
	 */
	public ArrayList<Item> getInventory(){
		return inventory;
	}
	
	/**
	 * Getter for obtaining the player's name.
	 * 
	 * @return Name of the player
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for setting the name of the player.
	 * 
	 * @param nameInput The player's name that will be set to.
	 */
	public void setName(String nameInput) {
		name = nameInput;
	}
	
	/**
	 * Method for the player to play with their pet using a toy from their inventory. 
	 * The toy will take durability damage and their pet will become happier.
	 * 
	 * @param pet Pet that will play with a toy. Pet's stats will change.
	 * @param item Toy takes damage to durability.
	 * 
	 * @return A confirmation string
	 */
	public String playWithPet(Pets pet, Item item) {
		return pet.playWithToy(item);
	}
	
	/**
	 * Player will feed the pet food. Counts as an actions.
	 * @param pet Pet that will eat the food. Stats will change as required.
	 * @param item Food Object to be eaten by the pet.
	 * 
	 * @return A confirmation string
	 */
	public String feed(Pets pet, Item item) {
		return pet.eat(item);
	}
	
	/**
	 * Interact with pet and put them to sleep.
	 * 
	 * @param pet Pet that will go to sleep.
	 * 
	 * @return A confirmation string
	 */
	public String rest(Pets pet) {
		return pet.sleep();
	}
	
	/**
	 * Player has the pet use the toilet.
	 * 
	 * @param pet Pet that will use the toilet.
	 * 
	 * @return A confirmation string
	 */
	public String takeToToilet(Pets pet) {
		return pet.useToilet();
	}
	
	/**
	 * Player interacts with pet to heal them.
	 * 
	 * @param pet Pet which will use medicine.
	 * @param item Will affect pet's stats. 
	 * 
	 * @return A confirmation string
	 */
	public String heal(Pets pet, Item item) {
		return pet.heal(item);
	}
	
	/**
	 * Player will stop the pet from misbehaving. 
	 * 
	 * @param pet Pet which will be disciplined.
	 * 
	 * @return A confirmation string
	 */
	public String discipline(Pets pet) {
		return pet.discipline();
	}
	
	/**
	 * If pet has died, player can revive them. Can only revive a pet once.
	 * 
	 * @param pet Pet that will be revived.
	 * 
	 * @return A confirmation string
	 */
	public String revive(Pets pet) {
		return pet.revive();
	}
	
	/**
	 * Returns the player's score.
	 * 
	 * @return The player's score.
	 */
	public double getScore() {
		return score;
	}
	
	/**
	 * Sets the player's score.
	 * 
	 * @param scoreInput The amount the player's score will be set to.
	 */
	public void setScore(double scoreInput) {
		score = scoreInput;
	}
	
	/**
	 * Change the player's score
	 * 
	 * @param amount Give positive number to increase, give negative number to decrease.
	 */
	public void changeScore(int amount) {
		score += amount;
	}
	
	/**
	 * The player's name and pets will be returned when called the Player object
	 * is to be printed.
	 */
	@Override
	public String toString() {
		String output = String.format("Player %s has pet(s)", name);
		
		for(Pets pet : getPetArray()) {
			output += String.format(" %s", pet.getName());
		}
		
		return output;
	}
	
	/**
	 * Returns the amount of the money the player has.
	 * 
	 @return The amount of money the player has.
	 */
	public double getMoney() {
		return money;
	}
	
	/**
	 * The player spends and loses a certain amount of money.
	 * 
	 * @param spent The amount the player has spent.
	 */
	public void spendMoney(double spent) {
		money -= spent;
	}
	
	/**
	 * The player earns and increase a certain amount of their money.
	 * 
	 * @param earned The amount the player has earned.
	 */
	public void earnedMoney(double earned) {
		money += earned;
	}
	
	/**
	 * Adds a specified item into the player's inventory. Increases the stack if it's stackable and is already
	 * in the inventory, else adds is added to the inventory as a separate item.
	 * 
	 * @param item Item to be added
	 */
	public void addItem(Item item){
		if (item.returnIsStackable()){ 
			// if the item is stackable, check if a copy exists in the inventory. Otherwise add it in.
			int ownedItemIndex = 0;
			searchingIfAlreadyOwned:
			for(Item ownedItem : inventory) {
				if(ownedItem.equals(item)) {
					break searchingIfAlreadyOwned;
				} else {
					ownedItemIndex++;
				}
			}
			
			//Has completed the searching
			if(ownedItemIndex == inventory.size()) {
				//Index out of range, item is not in inventory
				inventory.add(item);
			} else {
				//Item found in inventory already, increment number of uses
				inventory.get(ownedItemIndex).increaseNumUses();
			}
				
			
		} else {
			//Is not stackable, just add it to the inventory
			inventory.add(item);
		}
				
	}
	
	/**
	 * If determining if there is inventory space to add a consumable item (i.e. Food/Medicine), always
	 * return true as they are 'stackable' in the player's inventory. If room for a toy is being determined,
	 * return true if the player does not have the toy yet. If the player does already have the toy in their
	 * inventory return false. A player can only have one each toy in their inventory at a time.
	 * 
	 * @param item Item to check whether there is space in the player's inventory to add.
	 * @return Boolean value true if there is space, false if there is no space.
	 */
	public boolean hasInventorySpace(Item item){
		if(!item.returnIsStackable()) {
			// if the item is not stackable, check how many of the identical items are in the players inventory (Hard limit of 1 identical toy)
			int counter = 0;
			for (Item toy : inventory){
				if (toy.equals(item)){
					counter++;
				}
			}
			
			if (counter < 1){
				return true;
			} else {
				return false;
			}
		} else {
			return true; //If item is stackable, can always add
		}
	}
	
}
