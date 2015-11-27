package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;

public class LiteralState implements IScannerState {

	private String value = new String("");

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		if (ch.matches("[0-9]")) {
			value += ch;
			return this;
		} else if (ch.matches("[']")) {
			return this;
		} else {
			list.add(new Literal(Integer.valueOf(value)));
			return new DefaultState().handleCharacter(ch, list);
		}
	}
}
