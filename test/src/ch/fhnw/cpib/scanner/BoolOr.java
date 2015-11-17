package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class BoolOr extends Base {

	private final Operators opr;
	
	public BoolOr(Terminals t, Operators opr) {
		super(t);
		this.opr = opr;
	}
	
	public Operators getOperator() {
		return opr;
	}
	
	public String toString() {
		return opr.toString();
	}
}
