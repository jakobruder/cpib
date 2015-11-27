package ch.fhnw.cpib.scanner;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.Base;

public class TokenList implements ITokenList {

	private LinkedList<Base> tokenList;

	public TokenList() {
		tokenList = new LinkedList<>();
	}

	@Override
	public void add(Base token) {
		tokenList.add(token);
	}

	@Override
	public void reset() {
		// ????
	}

	@Override
	public Base nextToken() {
		return tokenList.remove();
	}

	public String toString() {
		String returnString = "";
		for (IToken token : tokenList) {
			returnString += token;
		}
		return returnString;
	}

}
