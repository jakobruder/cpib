package ch.fhnw.cpib.scanner.Symbols;

import Interfaces.IToken;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class Colon extends Base implements IToken {

	Colon() {
		super(Terminals.COLON);
	}

	public String toString() {
		return "(" + super.toString() + ")";
	}	

}
