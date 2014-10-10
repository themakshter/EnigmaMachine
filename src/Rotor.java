
public abstract class Rotor {
	private String rotorType;
	private int rotorPosition;
	int [] mapping = new int[26];
	
	public Rotor(String type,int position){
		rotorType = type;
		rotorPosition = position;
		initialise(type);
	}
	
	public int getPosition(){
		return rotorPosition;
	}
		
	public void setPosition(int position){
		rotorPosition = position;
	}
	
	public abstract void initialise(String type);
	
	public abstract int substitute(int letterInt);
}
