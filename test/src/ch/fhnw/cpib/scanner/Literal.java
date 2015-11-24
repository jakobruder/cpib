package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.symbols.Base;

public class Literal extends Base {

	private final String value;

	public Literal(int value) {
		super(Terminals.LITERAL);
		this.value = String.valueOf(value);
	}

	public Literal(boolean value) {
		super(Terminals.LITERAL);
		if (value) {
			this.value = "BoolVal true";
		} else {
			this.value = "BoolVal false";
		}
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return "(" + super.toString() + "," + value + ")";
	}
}
