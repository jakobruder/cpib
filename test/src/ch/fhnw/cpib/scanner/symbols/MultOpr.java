package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class MultOpr extends Base {

	private final Operators opr;

	MultOpr(Operators opr) {
		super(Terminals.MULTOPR);
		this.opr = opr;
	}

	public Operators getOperator() {
		return opr;
	}

	public String toString() {
		return "(" + super.toString() + "," + opr + ")";
	}
}
