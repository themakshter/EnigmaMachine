

import java.io.*;

public class EnigmaMachine {
	protected BasicRotor[] slotsOfRotors = new BasicRotor[3];
	private Reflector r;
	private Plugboard plugBoard;
	private static int running = 1;

	public EnigmaMachine(Plugboard p) {
		plugBoard = p;

	}

	public void addPlug(char letterIn, char letterOut) {
		plugBoard.addPlug(letterIn, letterOut);
	}

	public void clearPlugboard() {
		plugBoard.clear();
	}

	public void addRotor(BasicRotor bRotor, int slot) {
		slotsOfRotors[slot] = bRotor;
	}

	public Rotor getRotor(int slot) {
		return slotsOfRotors[slot];
	}

	public void addReflector(Reflector rRotor) {
		r = rRotor;
	}

	public Reflector getReflector() {
		return r;
	}

	public void setPosition(int slot, int position) {
		slotsOfRotors[slot].setPosition(position);
	}

	public char encodeLetter(char letterToBeEncoded) {
		letterToBeEncoded = plugBoard.getPlugCharacter(letterToBeEncoded);
		int lettersNumericRepresentation = (int) letterToBeEncoded - 65;
		lettersNumericRepresentation = slotsOfRotors[0]
				.substitute(lettersNumericRepresentation);
		lettersNumericRepresentation = slotsOfRotors[1]
				.substitute(lettersNumericRepresentation);
		lettersNumericRepresentation = slotsOfRotors[2]
				.substitute(lettersNumericRepresentation);
		lettersNumericRepresentation = r
				.substitute(lettersNumericRepresentation);
		lettersNumericRepresentation = slotsOfRotors[2]
				.substituteBack(lettersNumericRepresentation);
		lettersNumericRepresentation = slotsOfRotors[1]
				.substituteBack(lettersNumericRepresentation);
		lettersNumericRepresentation = slotsOfRotors[0]
				.substituteBack(lettersNumericRepresentation);
		slotsOfRotors[0].rotate();
		lettersNumericRepresentation = lettersNumericRepresentation + 65;
		letterToBeEncoded = (char) lettersNumericRepresentation;
		letterToBeEncoded = plugBoard.getPlugCharacter(letterToBeEncoded);
		return letterToBeEncoded;
	}

	public String readString() {
		System.out.println("Enter your code");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String string;
		string = null;
		try {
			string = br.readLine();
		} catch (IOException ioe) {
			System.err.println("There was an input error");
		}
		return string;
	}

	public int readInteger() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String number;
		number = null;
		try {
			number = br.readLine();
		} catch (IOException ioe) {
			System.err.println("There was an input error");
		}
		try {
			return new Integer(number);
		} catch (NumberFormatException e) {
			System.err
					.println("There is something wrong with the number you entered");

		}
		return -1;
	}

	public void addPlugs() {
		int numOfPlugs;
		do {
			System.out
					.println("Enter the number of plugs you want in the machine (cannot be more than 13)");
			numOfPlugs = readInteger();
		} while (numOfPlugs > 13 || numOfPlugs < 0);
		for (int i = 0; i < numOfPlugs; i++) {
			System.out
					.println("What would you like to be the first character? (for Plug "
							+ (i + 1) + ")");
			String firstPlugLetter = readString().toUpperCase();
			char end1 = firstPlugLetter.charAt(0);
			char end2;
			do {
				System.out
						.println("What would you like to be the second character?(it cannot be the same as the last one!)");
				String secondPlugLetter = readString().toUpperCase();
				end2 = secondPlugLetter.charAt(0);
			} while (end2 == end1);
			addPlug(end1, end2);
		}
	}

	public void setNextRotor() {
		slotsOfRotors[0].nextRotor = slotsOfRotors[1];
		slotsOfRotors[1].nextRotor = slotsOfRotors[2];
	}

	public void addBasicRotor(int slotPosition) {
		String rotorType;
		int rotorPosition;
		do {
			System.out
					.println("Please enter the type of rotor to be entered (only type I,II,III,IV or V)");
			rotorType = readString().toUpperCase();
		} while (!(rotorType.equals("I") || rotorType.equals("II")
				|| rotorType.equals("III") || rotorType.equals("IV") || rotorType
				.equals("V")));
		do {
			System.out
					.println("Now enter the position you want the rotor to be in, initially(Can only be from 0-25)");
			rotorPosition = readInteger();
		} while (rotorPosition < 0 || rotorPosition > 25);
		BasicRotor br = new BasicRotor(rotorType, rotorPosition);
		addRotor(br, slotPosition);
	}

	public void addTurnoverRotor(int slotPosition) {
		String rotorType;
		int rotorPosition;
		do {
			System.out
					.println("Please enter the type of rotor to be entered (only type I,II,III,IV or V)");
			rotorType = readString().toUpperCase();
		} while (!(rotorType.equals("I") || rotorType.equals("II")
				|| rotorType.equals("III") || rotorType.equals("IV") || rotorType
				.equals("V")));
		do {
			System.out
					.println("Now enter the position you want the rotor to be in, initially(Can only be from 0-25)");
			rotorPosition = readInteger();
		} while (rotorPosition < 0 || rotorPosition > 25);
		TurnoverRotor tr = new TurnoverRotor(rotorType, rotorPosition);
		addRotor(tr, slotPosition);
	}

	public void addReflectorRotor() {
		String rotorType;
		do {
			System.out
					.println("Please enter the type of reflector rotor you want (either I or II)?");
			rotorType = "Reflector" + readString().toUpperCase();
		} while (!((rotorType.equals("ReflectorI") || rotorType
				.equals("ReflectorII"))));
		Reflector ref = new Reflector(rotorType, 0);
		addReflector(ref);
	}

	public void start() {
		String userInput;
		char letterIn;
		String rotorType;
		String output = "";
		addPlugs();
		for (int i = 0; i < slotsOfRotors.length; i++) {
			do {
				System.out.println("Which rotor would you like at slot " + i
						+ " ? (T)urnover Rotor or (B)asic Rotor?");
				rotorType = readString().toUpperCase();
			} while (!(rotorType.equals("T") || rotorType.equals("B")));
			if (rotorType.equals("T")) {
				addTurnoverRotor(i);
			} else if (rotorType.equals("B")) {
				addBasicRotor(i);
			}
		}
		addReflectorRotor();
		setNextRotor();
		System.out
				.println("Now that you've chosen the settings, please enter the string you'd like coded or decoded");
		userInput = readString().toUpperCase();
		for (int i = 0; i < userInput.length(); i++) {
			letterIn = userInput.charAt(i);
			letterIn = encodeLetter(letterIn);
			output = output + Character.toString(letterIn);
		}

		System.out.println("The encoded/decoded message is " + output);
	}

	public static void main(String[] args) {
		Plugboard p = new Plugboard();
		EnigmaMachine eMachine = new EnigmaMachine(p);

		System.out
				.println("This is the Enigma Machine, it will code and decode stuff for you *very cool*");
		while (running == 1) {
			eMachine.start();
			eMachine.clearPlugboard();
			System.out.println("Would you like to decode something else?(y/n)");
			String answer = eMachine.readString();
			if (answer.equals("n")) {
				running = 0;
			}
		}

		// eMachine.test1();
		// eMachine.test2();
		// eMachine.test3();
	}
	
	/*
	public void test1(){
		addPlug('A','M');
		addPlug('G','L');
		addPlug('E','T');
		BasicRotor br = new BasicRotor("I",6);
		addRotor(br,0);
		BasicRotor br2 = new BasicRotor("II",12);
		addRotor(br2,1);
		BasicRotor br3 = new BasicRotor("III",5);
		addRotor(br3,2);
		Reflector ref = new Reflector("ReflectorI",0);
		addReflector(ref);
		String testCode = "GFWIQH";
		char letterIn;
		String decodedText = "";
		for (int i = 0; i < testCode.length(); i++) {
			letterIn = testCode.charAt(i);
			letterIn = encodeLetter(letterIn);
			decodedText = decodedText + Character.toString(letterIn);
		}
		System.out.println(testCode + " decodes to " + decodedText);
	}
	*/
	
	/*
	public void test2(){
		addPlug('B','C');
		addPlug('R','I');
		addPlug('S','M');
		addPlug('A','F');
		BasicRotor br = new BasicRotor("IV",23);
		addRotor(br,0);
		BasicRotor br2 = new BasicRotor("V",4);
		addRotor(br2,1);
		BasicRotor br3 = new BasicRotor("II",9);
		addRotor(br3,2);
		Reflector ref = new Reflector("ReflectorII",0);
		addReflector(ref);
		String testCode = "GACIG";
		char letterIn;
		String decodedText = "";
		for (int i = 0; i < testCode.length(); i++) {
			letterIn = testCode.charAt(i);
			letterIn = encodeLetter(letterIn);
			decodedText = decodedText + Character.toString(letterIn);
		}
		System.out.println(testCode + " decodes to " + decodedText);
	}
	*/
	/*
	public void test3(){
		addPlug('Q','F');
		BasicRotor tr = new TurnoverRotor("I",23);
		addRotor(tr,0);
		BasicRotor tr2 = new TurnoverRotor("II",11);
		addRotor(tr2,1);
		BasicRotor tr3 = new TurnoverRotor("III",7);
		addRotor(tr3,2);
		Reflector ref = new Reflector("ReflectorI",0);
		addReflector(ref);
		setNextRotor();
		String testCode = "OJVAYFGUOFIVOTAYRNIWJYQWMXUEJGXYGIFT";
		char letterIn;
		String decodedText = "";
		for (int i = 0; i < testCode.length(); i++) {
			letterIn = testCode.charAt(i);
			letterIn = encodeLetter(letterIn);
			decodedText = decodedText + Character.toString(letterIn);
		}
		System.out.println(testCode + " decodes to " + decodedText);
	}
	*/
}
