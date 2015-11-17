package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class AddOpr extends Base {

	private final Operators opr;
	
	public AddOpr(Operators opr) {
		super(Terminals.ADDOPR);
		this.opr = opr;
	}
	
	public Operators getOperator() {
		return opr;
	}
	
	public String toString() {
		return "(" + super.toString() + "," + opr + ")";
	}
	
}
