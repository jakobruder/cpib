package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public class BoolOrState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		if (ch.matches("?")) {
			return new COrState();
		} else {
			throw new ScannerException("Invalid Character in BoolOr State: "
					+ ch);
		}
	}

}
