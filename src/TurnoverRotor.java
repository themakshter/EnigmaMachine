
public class TurnoverRotor extends BasicRotor {
	private int turnoverPosition;
	public TurnoverRotor(String type, int position) {
		super(type, position);
		initialise(type);
		if (type.equals("I")) {
			 turnoverPosition = 24;
		}
		if (type.equals("II")) {
			 turnoverPosition = 12;
		}
		if (type.equals("III")) {
			 turnoverPosition = 3;
		}
		if (type.equals("IV")) {
			 turnoverPosition = 17;
		}
		if (type.equals("V")) {
			 turnoverPosition = 7;
		}
	}
	
	@Override
	public void rotate(){
		int positionToBeSet;
		positionToBeSet = getPosition() + 1;
		if (positionToBeSet > 25) {
			positionToBeSet = 0;
		}
		setPosition(positionToBeSet);
		if(positionToBeSet == turnoverPosition){
			nextRotor.rotate();
		}
	}
	
}
