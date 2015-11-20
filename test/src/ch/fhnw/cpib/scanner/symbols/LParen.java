package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public class LParen extends Base implements IToken {

	LParen() {
		super(Terminals.LPAREN);
	}
	
	public String toString() {
		return "(" + super.toString() + ")";
	}
	

}
