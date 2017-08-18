package toy;
/**
 * Create a Slide object. Inherits methods and attributes from the Toy abstract class;
 * thus behaves as a toy item used to play with pets. Properties are unique to the Slide object.
 * 
 * Slide objects are created such that for all instances acting as a slide, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Slide extends Toy {
	
	/**
	 * Constructor to create a slide toy object by passing arguments to parent abstract class Toy constructor.
	 */
	public Slide(){
		super(15, "Slide", 100, "A colorful ramp that attracts the attention of birds.", 20);
	}
}
