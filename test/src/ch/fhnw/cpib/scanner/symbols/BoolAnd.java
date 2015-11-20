package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class BoolAnd extends Base {

	private final Operators opr;

	BoolAnd(Operators opr) {
		super(Terminals.BOOLAND);
		this.opr = opr;
	}
	
	public Operators getOperator() {
		return opr;
	}
	
	public String toString() {
		return "(" + super.toString() + "," + opr + ")";
	}

}
