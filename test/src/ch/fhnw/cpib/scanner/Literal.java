package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Terminals;

public class Literal extends Base {

	private final int value;
	
	public Literal(int value) {
		super(Terminals.LITERAL);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "(" + super.toString() + "," + value + ")";
	}}
