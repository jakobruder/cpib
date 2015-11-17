package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Terminals;

public interface IToken {
	public Terminals getTerminal();

	public String toString();
}
