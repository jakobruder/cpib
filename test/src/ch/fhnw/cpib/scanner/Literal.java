package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.checker.Types;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.symbols.Base;

public class Literal extends Base {

	private int intValue;
	private Types type;

	public Literal(int value) {
		super(Terminals.LITERAL);
		intValue = value;
		type = Types.INTEGER;
	}

	public Literal(boolean value) {
		super(Terminals.LITERAL);
		if (value) {
			intValue = 1;
		} else {
			intValue = 0;
		}
		type = Types.COND_BOOL;
	}

	public int getValue() {
		return intValue;
	}

	public Types getType() {
		return type;
	}

	public String toString() {
		return "(" + super.toString() + "," + intValue + ")";
	}
}
