package food;

/**
 * Class to create a fly food object. Inherits methods and attributes from the Food abstract class; thus behaves
 * as a food to be fed to pets. Properties are unique to the Fly object.
 * 
 * Fly objects are created such that for all instances acting as a fly, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */

public class Fly extends Food {

	/**
	 * Constructor to create a fly food object by passing arguments to parent abstract class Food constructor.
	 */
	public Fly(){
		super(5, "Fly", 5, "An ordinary fruit fly.");
	}
}
