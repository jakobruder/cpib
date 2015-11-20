package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public class Colon extends Base implements IToken {

	Colon() {
		super(Terminals.COLON);
	}

	public String toString() {
		return "(" + super.toString() + ")";
	}	

}
