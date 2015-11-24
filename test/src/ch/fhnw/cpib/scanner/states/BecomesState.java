package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.Base;

public class BecomesState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		list.add(new Base(Terminals.BECOMES));
		return new DefaultState().handleCharacter(ch, list);
	}

}
