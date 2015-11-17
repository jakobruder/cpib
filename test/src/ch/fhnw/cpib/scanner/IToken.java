package ch.fhnw.cpib.scanner;

import ch.fhnw.cpib.scanner.enums.Terminal;

public interface IToken {
	public Terminal getTerminal();

	public String toString();
}
