package ch.fhnw.cpib.scanner.util;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.ChangeMode;
import ch.fhnw.cpib.scanner.enums.FlowMode;
import ch.fhnw.cpib.scanner.enums.MechMode;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.enums.Value;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.ChangeModeToken;
import ch.fhnw.cpib.scanner.symbols.FlowModeToken;
import ch.fhnw.cpib.scanner.symbols.MechModeToken;
import ch.fhnw.cpib.scanner.symbols.MultOpr;
import ch.fhnw.cpib.scanner.symbols.ValueToken;

public class Utility {

	public static IToken createToken(String tokenString) {
		IToken token = null;

		switch (tokenString) {
		case "bool":
			token = new ValueToken(Value.BOOL);
			break;
		case "call":
			token = new Base(Terminals.CALL);
			break;
		case "const":
			token = new ChangeModeToken(ChangeMode.CONST);
			break;
		case "copy":
			token = new MechModeToken(MechMode.COPY);
			break;
		case "debugin":
			token = new Base(Terminals.DEBUGIN);
			break;
		case "debugout":
			token = new Base(Terminals.DEBUGOUT);
			break;
		case "divE":
			token = new MultOpr(Operators.DIV_E);
			break; 
		case "do":
			token = new Base(Terminals.DO);
			break;
		case "else":
			token = new Base(Terminals.ELSE);
			break;
		case "endfun":
			token = new Base(Terminals.ENDFUN);
			break;
		case "endif":
			token = new Base(Terminals.ENDIF);
			break;
		case "endproc":
			token = new Base(Terminals.ENDPROC);
			break;
		case "endprogram":
			token = new Base(Terminals.ENDPROGRAM);
			break;
		case "endwhile":
			token = new Base(Terminals.ENDWHILE);
			break;
		case "false":
			token = new Literal(false);
			break;
		case "fun":
			token = new Base(Terminals.FUN);
			break;
		case "global":
			token = new Base(Terminals.GLOBAL);
			break;
		case "if":
			token = new Base(Terminals.IF);
			break;
		case "in":
			token = new FlowModeToken(FlowMode.IN);
			break;
		case "init":
			token = new Base(Terminals.INIT);
			break;
		case "inout":
			token = new FlowModeToken(FlowMode.INOUT);
			break;
		case "int64":
			token = new ValueToken(Value.INT64);
			break;
		case "local":
			token = new Base(Terminals.LOCAL);
			break;
		case "modE":
			token = new MultOpr(Operators.MOD_E);
			break;
		case "not":
			token = new Base(Terminals.NOT);
			break;
		case "out":
			token = new FlowModeToken(FlowMode.OUT);
			break;
		case "program":
			token = new Base(Terminals.PROGRAM);
			break;
		case "ref":
			token = new MechModeToken(MechMode.REF);
			break;
		case "returns":
			token = new Base(Terminals.RETURNS);
			break;
		case "skip":
			token = new Base(Terminals.SKIP);
			break;
		case "then":
			token = new Base(Terminals.THEN);
			break;
		case "true":
			token = new Literal(true);
			break;
		case "var":
			token = new ChangeModeToken(ChangeMode.VAR);
			break;
		case "while":
			token = new Base(Terminals.WHILE);
			break;
		default:
			token = new Ident(tokenString);
			break;
		}

		return token;
	}
}