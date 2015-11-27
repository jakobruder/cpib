package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.enums.Value;

public class ValueToken extends Base{
	
	private Value value;
	
	public ValueToken(Value value) {
		super(Terminals.VALUE);
		this.value = value;
	}

	public Value getValue() {
		return value;
	}
	
	public String toString() {
		return "(" + super.toString() + ", " + value + ")";
	}

}
