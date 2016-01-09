package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;

public class Type extends Base {
	private Terminals type;

	public Type(Terminals type) {
		super(Terminals.TYPE);
		this.type = type;
	}

	public Terminals getType() {
		return type;
	}

	public String toString() {
		return "(" + super.toString() + ")";
	}
}
