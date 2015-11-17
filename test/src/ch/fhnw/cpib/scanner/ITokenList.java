package ch.fhnw.cpib.scanner;

public interface ITokenList {
	public void add(IToken.Base token);

	public void reset();

	public IToken.Base nextToken();

	public String toString();
}
