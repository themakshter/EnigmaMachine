
import java.util.ArrayList;

public class Plugboard {
	private ArrayList<Plug> listOfPlugs = new ArrayList<Plug>();

	public boolean addPlug(char letterIn, char letterOut) {
		Plug plugin = new Plug(letterIn, letterOut);
		boolean added = true;
		for (Plug p : listOfPlugs) {
			if (plugin.clashesWith(p)) {
				added = false;
			}
		}
		if (added = true) {
			listOfPlugs.add(plugin);
		}
		return added;
	}
	public char getPlugCharacter(char letterIn){
		char letterOut = letterIn;
		for(Plug p: listOfPlugs){
			if(p.getEnd1() == letterIn || p.getEnd2() == letterIn){
				letterOut = p.test(letterIn);
			}
		}
		return letterOut;
	}

	public int getNumPlugs() {
		return listOfPlugs.size();
	}

	public void clear() {
		listOfPlugs.clear();
	}

}
