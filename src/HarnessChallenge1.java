

public class HarnessChallenge1 {
	private Plugboard p = new Plugboard();
	private EnigmaMachine eM = new EnigmaMachine(p);
	private char[] listOfChars= {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	public void addRotors(){
		BasicRotor br = new BasicRotor("IV",8);
		eM.addRotor(br,0);
		BasicRotor br2 = new BasicRotor("III",4);
		eM.addRotor(br2,1);
		BasicRotor br3 = new BasicRotor("II",21);
		eM.addRotor(br3,2);
		Reflector ref = new Reflector("ReflectorI",0);
		eM.addReflector(ref);
	}
	
	public void addPlugs(){
		char firstKnownLetter = 'D';
		char secondKnownLetter = 'S';
		for(int i = 0; i < listOfChars.length; i++){
			eM.addPlug(firstKnownLetter,listOfChars[i]);
		}
		
		
	}
	public static void main(String[] args){
		
		

		
	}
	
	
}
