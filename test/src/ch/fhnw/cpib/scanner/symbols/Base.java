package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public class Base implements IToken {

	private final Terminals terminal;

	public Base(Terminals t) {
		this.terminal = t;
	}

	public Terminals getTerminal() {
		return terminal;
	}

	public String toString() {
		return terminal.toString();
	}

}
