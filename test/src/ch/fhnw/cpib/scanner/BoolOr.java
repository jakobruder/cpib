package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class BoolOr extends Base {

	private final Operators opr;
	
	public BoolOr(Operators opr) {
		super(Terminals.BOOLOR);
		this.opr = opr;
	}
	
	public Operators getOperator() {
		return opr;
	}
	
	public String toString() {
		return "(" + super.toString() + "," + opr + ")";
	}
}
