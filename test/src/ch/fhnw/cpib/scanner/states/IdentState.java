package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;

public class IdentState implements IScannerState {

	private String value = new String("");

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		if (ch.matches("[a-zA-Z0-9]")) {
			value += ch;
			return this;
		} else {
			// Todo --> Add Token to list
			list.add(new Ident(value));
			return new DefaultState().handleCharacter(ch, list);
		}
	}

}
