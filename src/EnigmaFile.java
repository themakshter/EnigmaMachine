
import java.io.*;
public class EnigmaFile extends EnigmaMachine{
private BufferedReader reader;
	public EnigmaFile(Plugboard p) {
		super(p);
		EnigmaMachine em = new EnigmaMachine(p);
		try {
			reader = new BufferedReader(new FileReader("Codes.txt"));
		} catch (FileNotFoundException fnfe) {
			System.err.println("Error! The file you requested was not found!");
		}
		
	}
	public String getLine() {
		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException ioe) {
			System.err.println("Error! Cannot read from the file");
		}
		return line;
	}
	public boolean fileIsReady() {
		boolean fileReady = true;
		try {
			fileReady = reader.ready();
		} catch (Exception nre) {
			System.err.println("Error! The file is not ready!");
		}
		return fileReady;
	}
	
	@Override
	public void start(){
		String fileInput = getLine();
		char letterIn;
		String rotorType;
		String output = ""; 
		System.out.println("The Enigma Machine will decode the string from the file. Please enter the settings first");
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
		for(int i = 0 ; i < fileInput.length(); i++){
			letterIn = fileInput.charAt(i);
			letterIn = encodeLetter(letterIn);
			output = output + Character.toString(letterIn);
		}
		System.out.println("The encoded/decoded message is " + output);
		String userAnswer;
		do {
		System.out.println("Would you like to save it?(Y/N)");
		userAnswer = readString().toUpperCase();
		}while(!(userAnswer.equals("Y") || userAnswer.equals("N")));
		if(userAnswer.equals("Y")){
			saveCode(output);
			System.out.print("Your answer has been saved!");
		}
		
		
	}
	public void saveCode(String code){
		try {
			PrintStream pst = new PrintStream("Decodes.txt");
			pst.print(code);
		} catch (FileNotFoundException fnfe) {
			System.err.println("Error! File not found!");
		}
	}
	
	
	public static void main(String[] args){
		Plugboard p = new Plugboard();
		EnigmaFile ef = new EnigmaFile(p);
		ef.start();
	}
}
