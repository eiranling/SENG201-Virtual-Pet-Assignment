package toy;
/**
 * Create a Bone object. Inherits methods and attributes from the Toy abstract class;
 * thus behaves as a toy item used to play with pets. Properties are unique to the Bone object.
 * 
 * Bone objects are created such that for all instances acting as a bone, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class Bone extends Toy {

	
	/**
	 * Constructor to create a bone toy object by passing arguments to parent abstract class Toy constructor.
	 */
	public Bone(){
		super(20, "Squeaky Bone", 50, "A rubber bone toy that squeaks when squished.", 15);
	}
}
