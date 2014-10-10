
public class Reflector extends Rotor{
	private final int[] reflectorI = {24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
	private final int[] reflectorII = { 5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11 };
	
	public Reflector(String type, int position) {
		super(type, position);
		initialise(type);
	}
	

	public void initialise(String type) {
		if(type.equals("ReflectorI")){
			mapping = reflectorI;

		}
		else if(type.equals("ReflectorII")){
			mapping = reflectorII;
		}
	}

	public int substitute(int letterInt){
		return mapping[letterInt];
		}

}

