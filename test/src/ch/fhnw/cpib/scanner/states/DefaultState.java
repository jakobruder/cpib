package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.AddOpr;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.MultOpr;
import ch.fhnw.cpib.scanner.symbols.RelOpr;

public class DefaultState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException {

		if (ch.matches("[a-zA-Z]")) {
			return new IdentState().handleCharacter(ch, list);
		} else if (ch.matches("/")) {
			return new SlashState();
		} else if (ch.matches("[0-9]")) {
			return new LiteralState().handleCharacter(ch, list);
		} else if (ch.matches(":")) {
			return new ColonState();
		} else if (ch.matches("&")) {
			return new BoolAndState();
		} else if (ch.matches("<")) {
			return new LessState();
		} else if (ch.matches(">")) {
			return new GreaterState();
		} else if (ch.equals("|")) {
			return new BoolOrState();
		} else if (ch.matches(",")) {
			list.add(new Base(Terminals.COMMA));
			return new DefaultState();
		} else if (ch.matches("\\(")) {
			list.add(new Base(Terminals.LPAREN));
			return new DefaultState();
		} else if (ch.matches("\\)")) {
			list.add(new Base(Terminals.RPAREN));
			return new DefaultState();
		} else if (ch.matches(";")) {
			list.add(new Base(Terminals.SEMICOLON));
			return new DefaultState();
		} else if (ch.matches("\\*")) {
			list.add(new MultOpr(Operators.TIMES));
			return new DefaultState();
		} else if (ch.matches("\\+")) {
			list.add(new AddOpr(Operators.PLUS));
			return new DefaultState();
		} else if (ch.matches("\\-")) {
			list.add(new AddOpr(Operators.MINUS));
			return new DefaultState();
		} else if (ch.matches("\\-")) {
			list.add(new RelOpr(Operators.EQ));
			return new DefaultState();
		} else if (ch.matches(" ")) {
			return new DefaultState();
		} else {
			if (ch != null && !ch.isEmpty()) {
				throw new ScannerException(
						"Invalid Character in default State: " + ch);
			}
			return new DefaultState();
		}

	}
}
