package food;

/**
 * Create a Fish object. Inherits methods and attributes from the Food abstract class; thus behaves
 * as a food to be fed to pets. Properties are unique to the Fish object.
 * 
 * Fish objects are created such that for all instances acting as a fish, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Fish extends Food {

	
	/**
	 * Constructor to create a fish food object by passing arguments to parent abstract class Food constructor.
	 */
	public Fish(){
		super(20, "Fish", 25, "A scrumptious fillet.");
	}
}
