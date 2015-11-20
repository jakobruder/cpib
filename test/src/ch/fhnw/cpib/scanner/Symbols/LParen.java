package ch.fhnw.cpib.scanner.Symbols;

import Interfaces.IToken;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class LParen extends Base implements IToken {

	LParen() {
		super(Terminals.LPAREN);
	}
	
	public String toString() {
		return "(" + super.toString() + ")";
	}
	

}
