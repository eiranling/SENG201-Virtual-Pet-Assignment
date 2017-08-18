package medicine;
/**
 * Create a EpiPen object. Inherits methods and attributes from the Medicine abstract class;
 * thus behaves as a medical item used to heal pets. Properties are unique to the EpiPen object.
 * 
 * EpiPen objects are created such that for all instances acting as a EpiPen, they can be changed
 * here.
 * 
 * @author Patrick Ma
 * @author Eiran Ling
 */
public class EpiPen extends Medicine {

	
	/**
	 * Constructor to create a EpiPen medicine object by passing arguments to parent abstract class Medicine constructor.
	 */
	public EpiPen(){
		super(100, "EpiPen", 1000000, "Vital medicine which many people rely on to relieve severe if not deathly allergic reactions.");
	}
}
