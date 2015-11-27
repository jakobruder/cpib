package ch.fhnw.cpib.scanner.interfaces;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;

public interface IScannerState {
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException;

}
