package ch.fhnw.cpib.scanner.interfaces;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IToken;

public interface IScannerState {
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException;

}
