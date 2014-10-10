
public class BasicRotor extends Rotor {
	protected BasicRotor nextRotor;
	private final int[] i = { 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 };
	private final int[] ii = { 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 };
	private final int[] iii = { 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 };
	private final int[] iv = {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 };
	private final int[] v = { 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 };
	public BasicRotor(String type, int position) {
		super(type, position);
		initialise(type);
	}

	public void initialise(String type) {
		if (type.equals("I")) {
			mapping = i;
		}
		if (type.equals("II")) {
			mapping = ii;
		}
		if (type.equals("III")) {
			mapping = iii;
		}
		if (type.equals("IV")) {
			mapping = iv;
		}
		if (type.equals("V")) {
			mapping = v;
		}
	}

	public int substitute(int letterInt) {
		int numToBeSubstituted;
		letterInt = letterInt - getPosition();
		if (letterInt < 0) {
			letterInt = mapping.length + letterInt;
		}
		numToBeSubstituted = mapping[letterInt] + getPosition();
		if (numToBeSubstituted > 25) {
			numToBeSubstituted = numToBeSubstituted - mapping.length;
		}
		return numToBeSubstituted;
	}

	public int substituteBack(int letterInt) {
		int[] inverseMapping = new int[26];
		int numToBeSubstituted;
		int i;
		for (i = 0; i < 26; i++) {
			int numberFromArray = mapping[i];
			inverseMapping[numberFromArray] = i;
		}
		letterInt = letterInt - getPosition();
		if (letterInt < 0) {
			letterInt = inverseMapping.length + letterInt;
		}
		numToBeSubstituted = inverseMapping[letterInt] + getPosition();
		if (numToBeSubstituted > 25) {
			numToBeSubstituted = 0 + (numToBeSubstituted - inverseMapping.length);
		}
		return numToBeSubstituted;
	}

	public void rotate() {
		int positionToBeSet;
		positionToBeSet = getPosition() + 1;
		if (positionToBeSet > 25) {
			positionToBeSet = 0;
		}
		setPosition(positionToBeSet);
	}
}
