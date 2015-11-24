package ch.fhnw.cpib.scanner;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.states.DefaultState;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.MultOpr;

public class Scanner {

	private IScannerState state = new DefaultState();

	public LinkedList<IToken> scan(CharSequence program) {
		LinkedList<IToken> tokenList = new LinkedList<>();
		try {
			for (int i = 0; i < program.length(); i++) {
				String currentChar = String.valueOf(program.charAt(i));

				state = state.handleCharacter(currentChar, tokenList);

			}
			// handle end
			state.handleCharacter("", tokenList);
		} catch (ScannerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tokenList.add(new Base(Terminals.SENTINEL));
		return tokenList;
	}

	public IToken createToken(String tokenString) {
		IToken token = null;

		switch (tokenString) {
		case "bool":
//			token = new Type(Terminals.BOOL);
			break;
		case "call":
			token = new Base(Terminals.CALL);
			break;
		case "const":
//			token = new ChangeModeToken(Terminals.CONST);
			break;
		case "copy":
//			token = new MechModeToken(Terminals.COPY);
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
//			token = new Literal(value)
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
		case "init":
			token = new Base(Terminals.INIT);
			break;
		case "int64":
//			token = new Type(Terminals.INT64);
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
		case "program":
			token = new Base(Terminals.PROGRAM);
			break;
		case "ref":
//			token = new MechModeToken(Terminals.REF);
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
//			token = new Base();
			break;
		case "var":
//			token = new ChangeModeToken(Terminals.VAR);
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

	
	public static void main(String[] args) {
		Scanner s = new Scanner();
		CharSequence program = "asdf := /= ; 123 + - + * 123123abab< <= > >= :=";
		LinkedList<IToken> list = s.scan(program);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
