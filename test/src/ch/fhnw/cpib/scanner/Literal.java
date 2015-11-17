package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Terminals;

public class Literal extends Base {

	private final int value;
	
	public Literal(Terminals t, int value) {
		super(t);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return value + "";
	}
}
