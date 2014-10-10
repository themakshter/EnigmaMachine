
public class Plug {
	private char end1;
	private char end2;

	public Plug(char letterIn, char letterOut) {
		end1 = letterIn;
		end2 = letterOut;
	}

	public char getEnd1() {
		return end1;
	}

	public char getEnd2() {
		return end2;
	}

	public char test(char letterIn) {
		if (letterIn == end1) {
			return end2;
		} else if (letterIn == end2) {
			return end1;
		} else {
			return letterIn;
		}
	}

	public boolean clashesWith(Plug plugin) {
		boolean clashes;
		if (plugin.getEnd1() == end1 || plugin.getEnd2() == end2 || plugin.getEnd1() == end2 || plugin.getEnd2() == end1) {
			clashes = true;
		} else {
			clashes = false;
		}
		return clashes;
	}
}
