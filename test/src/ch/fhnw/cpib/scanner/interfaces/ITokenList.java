package ch.fhnw.cpib.scanner.interfaces;

public interface ITokenList {
	public void add(IToken token);

	public void reset();

	public IToken nextToken();

	public String toString();
}
