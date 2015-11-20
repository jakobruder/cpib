package ch.fhnw.cpib.scanner.Symbols;

import Interfaces.IToken;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class Semicolon extends Base implements IToken {


	Semicolon() {
		super(Terminals.SEMICOLON);
	}
	
	public String toString() {
		return "(" + super.toString() + ")";
	}
}
