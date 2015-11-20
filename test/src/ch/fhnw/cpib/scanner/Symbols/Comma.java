package ch.fhnw.cpib.scanner.Symbols;

import Interfaces.IToken;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class Comma extends Base implements IToken {

	Comma() {
		super(Terminals.COMMA);
	}

	public String toString() {
		return "(" + super.toString() + ")";
	}

}
