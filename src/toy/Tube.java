package toy;

public class Tube extends Toy {
	/**
	 * Create a Tube object. Inherits methods and attributes from the Toy abstract class;
	 * thus behaves as a toy item used to play with pets. Properties are unique to the Tube object.
	 * 
	 * Tube objects are created such that for all instances acting as a cardboard tube, they can be changed
	 * here.
	 * 
	 * @author Patrick Ma
	 * @author Eiran Ling
	 */
	
	/**
	 * Constructor to create a cardboard tube toy object by passing arguments to parent abstract class Toy constructor.
	 */
	public Tube(){
		super(5, "Cardboard Tube", 5, "A round cardboard cardboard tube that snakes find entertaining.", 5);
	}
}
