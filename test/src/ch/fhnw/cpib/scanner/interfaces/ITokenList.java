package ch.fhnw.cpib.scanner.interfaces;

import ch.fhnw.cpib.scanner.symbols.Base;

public interface ITokenList {
	public void add(Base token);

	public void reset();

	public Base nextToken();

	public String toString();
}
