package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.symbols.Base;

public class Ident extends Base {

	private final String ident;
	
	public Ident(String ident) {
		super(Terminals.IDENT);
		this.ident = ident;
	}
	
	public String getIdent() {
		return ident;
	}
	
	public String toString() {
		return "(" + super.toString() + "," + ident + ")";
	}
	
}
