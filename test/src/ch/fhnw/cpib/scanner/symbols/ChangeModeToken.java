package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.ChangeMode;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class ChangeModeToken extends Base {

	private ChangeMode mode;
	
	public ChangeModeToken(ChangeMode mode) {
		super(Terminals.CHANGEMODE);
		this.mode = mode;
	}

	public ChangeMode getChangeMode() {
		return mode;
	}
	
	public String toString() {
		return "(" + super.toString() + ", " + mode + ")";
	}
}
