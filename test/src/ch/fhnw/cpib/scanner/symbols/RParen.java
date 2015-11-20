package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public class RParen extends Base implements IToken {

	RParen() {
		super(Terminals.RPAREN);
	}

	public String toString() {
		return "(" + super.toString() + ")";
	}

}
