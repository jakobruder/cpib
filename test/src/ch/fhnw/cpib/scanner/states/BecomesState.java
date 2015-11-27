package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.Base;

public class BecomesState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException {
		list.add(new Base(Terminals.BECOMES));
		return new DefaultState().handleCharacter(ch, list);
	}

}
