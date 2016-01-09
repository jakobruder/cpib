package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;

public class BoolOrState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException {
		if (ch.equals("?")) {
			return new COrState();
		} else {
			throw new ScannerException("Invalid Character in BoolOr State: "
					+ ch);
		}
	}

}
