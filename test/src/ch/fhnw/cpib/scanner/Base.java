package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Terminals;

public class Base implements IToken {

	private final Terminals terminal;

	Base(Terminals t) {
		this.terminal = t;
	}

	public Terminals getTerminal() {
		return terminal;
	}

	public String toString() {
		return terminal.toString();
	}

}
