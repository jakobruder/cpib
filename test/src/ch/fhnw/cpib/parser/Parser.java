package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.parser.interfaces.IParser;
import ch.fhnw.cpib.parser.interfaces.IProgram;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.Base;

class Parser implements IParser {
	private ITokenList tokenList;
	private Base token;
	private Terminals terminal;
	// class invariant: terminal == token.getTerminal()
	// private IConcSyn concSyn; // discussed later

	public Parser(ITokenList tokenList) {
		this.tokenList = tokenList;
		this.tokenList.reset();
		// precondition: token list contains at least the SENTINEL
		token = tokenList.nextToken();
		// establish class invariant
		terminal = token.getTerminal();
		// concSyn = new ConcSyn(); // discussed later
	}

	@Override
	public IProgram parse() throws GrammarError {
		// TODO Auto-generated method stub
		return null;
	}

	private Base consume(Terminals expectedTerminal) throws GrammarError {
		if (terminal == expectedTerminal) {
			Base consumedToken = token;
			if (terminal != Terminals.SENTINEL) {
				token = tokenList.nextToken();
				// maintain class invariant
				terminal = token.getTerminal();
			}
			return consumedToken;
		} else {
			throw new GrammarError("terminal expected: " + expectedTerminal + ", terminal found: " + terminal);
		}
	}
}
