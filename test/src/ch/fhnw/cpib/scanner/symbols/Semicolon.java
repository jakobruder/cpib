package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public class Semicolon extends Base implements IToken {


	Semicolon() {
		super(Terminals.SEMICOLON);
	}
	
	public String toString() {
		return "(" + super.toString() + ")";
	}
}
