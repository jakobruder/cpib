package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.ChangeMode;
import ch.fhnw.cpib.scanner.enums.FlowMode;
import ch.fhnw.cpib.scanner.enums.MechMode;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.ChangeModeToken;
import ch.fhnw.cpib.scanner.symbols.FlowModeToken;
import ch.fhnw.cpib.scanner.symbols.MechModeToken;
import ch.fhnw.cpib.scanner.symbols.Type;

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

			if (value.equalsIgnoreCase(Terminals.CALL.toString())) {
				list.add(new Base(Terminals.CALL));
			} else if (value.equalsIgnoreCase("const")) {
				list.add(new ChangeModeToken(ChangeMode.CONST));
			} else if (value.equalsIgnoreCase("var")) {
				list.add(new ChangeModeToken(ChangeMode.VAR));
			} else if (value.equalsIgnoreCase("copy")) {
				list.add(new MechModeToken(MechMode.COPY));
			} else if (value.equalsIgnoreCase("ref")) {
				list.add(new MechModeToken(MechMode.REF));
			} else if (value.equalsIgnoreCase(Terminals.DEBUGIN.toString())) {
				list.add(new Base(Terminals.DEBUGIN));
			} else if (value.equalsIgnoreCase(Terminals.DEBUGOUT.toString())) {
				list.add(new Base(Terminals.DEBUGOUT));
			} else if (value.equalsIgnoreCase(Terminals.DO.toString())) {
				list.add(new Base(Terminals.DO));
			} else if (value.equalsIgnoreCase(Terminals.ELSE.toString())) {
				list.add(new Base(Terminals.ELSE));
			} else if (value.equalsIgnoreCase(Terminals.ENDFUN.toString())) {
				list.add(new Base(Terminals.ENDFUN));
			} else if (value.equalsIgnoreCase(Terminals.ENDIF.toString())) {
				list.add(new Base(Terminals.ENDIF));
			} else if (value.equalsIgnoreCase(Terminals.ENDPROC.toString())) {
				list.add(new Base(Terminals.ENDPROC));
			} else if (value.equalsIgnoreCase(Terminals.ENDPROGRAM.toString())) {
				list.add(new Base(Terminals.ENDPROGRAM));
			} else if (value.equalsIgnoreCase(Terminals.ENDWHILE.toString())) {
				list.add(new Base(Terminals.ENDWHILE));
			} else if (value.equalsIgnoreCase(Terminals.FALSE.toString())) {
				list.add(new Literal(false));
			} else if (value.equalsIgnoreCase(Terminals.TRUE.toString())) {
				list.add(new Literal(true));
			} else if (value.equalsIgnoreCase("in")) {
				list.add(new FlowModeToken(FlowMode.IN));
			} else if (value.equalsIgnoreCase("out")) {
				list.add(new FlowModeToken(FlowMode.OUT));
			} else if (value.equalsIgnoreCase("inout")) {
				list.add(new FlowModeToken(FlowMode.INOUT));
			} else if (value.equalsIgnoreCase(Terminals.FUN.toString())) {
				list.add(new Base(Terminals.FUN));
			} else if (value.equalsIgnoreCase(Terminals.GLOBAL.toString())) {
				list.add(new Base(Terminals.GLOBAL));
			} else if (value.equalsIgnoreCase(Terminals.IF.toString())) {
				list.add(new Base(Terminals.IF));
			} else if (value.equalsIgnoreCase(Terminals.INIT.toString())) {
				list.add(new Base(Terminals.INIT));
			} else if (value.equalsIgnoreCase(Terminals.INT64.toString())) {
				list.add(new Type(Terminals.INT64));
			} else if (value.equalsIgnoreCase(Terminals.BOOL.toString())) {
				list.add(new Type(Terminals.BOOL));
			} else if (value.equalsIgnoreCase(Terminals.LOCAL.toString())) {
				list.add(new Base(Terminals.LOCAL));
			} else if (value.equalsIgnoreCase(Terminals.MODE.toString())) {
				list.add(new Base(Terminals.MODE));
			} else if (value.equalsIgnoreCase(Terminals.NOT.toString())) {
				list.add(new Base(Terminals.NOT));
			} else if (value.equalsIgnoreCase(Terminals.PROC.toString())) {
				list.add(new Base(Terminals.PROC));
			} else if (value.equalsIgnoreCase(Terminals.PROGRAM.toString())) {
				list.add(new Base(Terminals.PROGRAM));
			} else if (value.equalsIgnoreCase(Terminals.RETURNS.toString())) {
				list.add(new Base(Terminals.RETURNS));
			} else if (value.equalsIgnoreCase(Terminals.SKIP.toString())) {
				list.add(new Base(Terminals.SKIP));
			} else if (value.equalsIgnoreCase(Terminals.THEN.toString())) {
				list.add(new Base(Terminals.THEN));
			} else if (value.equalsIgnoreCase(Terminals.WHILE.toString())) {
				list.add(new Base(Terminals.WHILE));
			} else {
				list.add(new Ident(value));
			}

			return new DefaultState().handleCharacter(ch, list);
		}
	}
}
