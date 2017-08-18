package food;

/**
 * Class to create a seeds food object. Inherits methods and attributes from the Food abstract class; thus behaves
 * as a food to be fed to pets. Properties are unique to the seeds object.
 * 
 * Seeds objects are created such that for all instances acting as seeds, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Seeds extends Food {

	
	/**
	 * Constructor to create a seeds food object by passing arguments to parent abstract class Food constructor.
	 */
	public Seeds() {
		super(10, "Seeds", 15, "Unborn sunflowers that will never live up to their full potential.");
	}

}
