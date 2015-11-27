package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;

public class IdentState implements IScannerState {

	private String value = new String("");

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
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
