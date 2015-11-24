package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public class Comma extends Base implements IToken {

	public Comma() {
		super(Terminals.COMMA);
	}

	public String toString() {
		return "(" + super.toString() + ")";
	}

}
