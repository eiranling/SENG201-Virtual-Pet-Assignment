package food;

/**
 * Class to create a steak food object. Inherits methods and attributes from the Food abstract class; thus behaves
 * as a food to be fed to pets. Properties are unique to the steak object.
 * 
 * Steak objects are created such that for all instances acting as steak, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Steak extends Food {
	
	/**
	 * Constructor to create a steak food object by passing arguments to parent abstract class Food constructor.
	 */
	public Steak(){
		super(25, "Steak", 30, "A prime cut of meat. From an innocent cow. That you brutally murdered. You monster.");
	}
}
