package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.MechMode;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class MechModeToken extends Base{

	private MechMode mode;
	
	public MechModeToken(MechMode mode) {
		super(Terminals.MECHMODE);
		this.mode = mode;
	}
	
	public MechMode getMechMode() {
		return mode;
	}
	
	public String toString() {
		return "(" + super.toString() + ", " + mode + ")";
	}

}
