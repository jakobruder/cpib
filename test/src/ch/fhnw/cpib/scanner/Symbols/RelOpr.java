package ch.fhnw.cpib.scanner.Symbols;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class RelOpr extends Base {

	private final Operators relOpr;

	RelOpr(Operators relOpr) {
		super(Terminals.RELOPR);
		this.relOpr = relOpr;
	}

	public Operators getRelOpr() {
		return relOpr;
	}

	public String toString() {
		return "(" + super.toString() + "," + relOpr + ")";
	}
	
}
