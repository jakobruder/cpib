package ch.fhnw.cpib.scanner.Symbols;

import Interfaces.IToken;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class RParen extends Base implements IToken {

	RParen() {
		super(Terminals.RPAREN);
	}

	public String toString() {
		return "(" + super.toString() + ")";
	}

}
