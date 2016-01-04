package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.parser.interfaces.IParser;
import ch.fhnw.cpib.parser.interfaces.IProgram;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.MultOpr;

class Parser implements IParser {
	private ITokenList tokenList;
	private Base token;
	private Terminals terminal;

	// class invariant: terminal == token.getTerminal()
	// private IConcSyn concSyn; // discussed later

	public Parser(ITokenList tokenList) {
		this.tokenList = tokenList;
		this.tokenList.reset();
		// precondition: token list contains at least the SENTINEL
		token = tokenList.nextToken();
		// establish class invariant
		terminal = token.getTerminal();
		// concSyn = new ConcSyn(); // discussed later
	}

	@Override
	public IProgram parse() throws GrammarError {

		// To be changed to receive a tree once it is implemented
		program();

		consume(Terminals.SENTINEL);
		return null;
	}

	// to be changed to return a tree
	private void program() throws GrammarError {
		if (terminal == Terminals.PROGRAM) {
			consume(Terminals.PROGRAM);
			consume(Terminals.IDENT);
			progParamList();
			programop();
			consume(Terminals.DO);
			cpsCmd();
			consume(Terminals.ENDPROGRAM);
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void progParamList() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			consume(Terminals.LPAREN);
			progParamListop();
			consume(Terminals.RPAREN);
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void progParamListop() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			progParam();
			progParamListopop();
		} else if (terminal == Terminals.CHANGEMODE) {
			progParam();
			progParamListopop();
		} else if (terminal == Terminals.FLOWMODE) {
			progParam();
			progParamListopop();
		} else if (terminal == Terminals.RPAREN) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void progParamListopop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			progParam();
			progParamListopop();
		} else if (terminal == Terminals.RPAREN) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void progParam() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			globImpop1();
			globImpop2();
			typedIdent();
		} else if (terminal == Terminals.CHANGEMODE) {
			globImpop1();
			globImpop2();
			typedIdent();
		} else if (terminal == Terminals.FLOWMODE) {
			globImpop1();
			globImpop2();
			typedIdent();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private IConcTree.ICpsDecl cpsDecl() throws GrammarError {
		if (terminal == Terminals.PROC) {
			IConcTree.IDecl decl = decl();
			IConcTree.ICpsDeclop cpsDeclop = cpsDeclop();
			return new IConcTree.CpsDecl(decl, cpsDeclop);
		} else if (terminal == Terminals.FUN) {
			decl();
			cpsDeclop();
		} else if (terminal == Terminals.IDENT) {
			decl();
			cpsDeclop();
		} else if (terminal == Terminals.CHANGEMODE) {
			decl();
			cpsDeclop();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cpsDeclop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			consume(Terminals.SEMICOLON);
			decl();
			cpsDeclop();
		} else if (terminal == Terminals.DO) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cpsCmd() throws GrammarError {
		if (terminal == Terminals.DEBUGOUT) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.DEBUGIN) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.CALL) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.WHILE) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.IF) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.LPAREN) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.ADDOPR) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.NOT) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.IDENT) {
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.SKIP) {
			cmd();
			cpsCmdop();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cpsCmdop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			consume(Terminals.SEMICOLON);
			cmd();
			cpsCmdop();
		} else if (terminal == Terminals.ENDWHILE) {
		} else if (terminal == Terminals.ENDIF) {
		} else if (terminal == Terminals.ELSE) {
		} else if (terminal == Terminals.ENDPROC) {
		} else if (terminal == Terminals.ENDFUN) {
		} else if (terminal == Terminals.ENDPROGRAM) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private IConcTree.IProgramop programop() throws GrammarError {
		if (terminal == Terminals.GLOBAL) {
			IToken global = (IToken)consume(Terminals.GLOBAL);
			IConcTree.ICpsDecl cpsDecl = cpsDecl();
			return new IConcTree.ProgramopGlobal(global, cpsDecl);
		} else if (terminal == Terminals.DO) {
			return new IConcTree.ProgramopDo();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void decl() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			stoDecl();
		} else if (terminal == Terminals.CHANGEMODE) {
			stoDecl();
		} else if (terminal == Terminals.FUN) {
			funDecl();
		} else if (terminal == Terminals.PROC) {
			procDecl();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void funDecl() throws GrammarError {
		if (terminal == Terminals.FUN) {
			consume(Terminals.FUN);
			consume(Terminals.IDENT);
			paramList();
			consume(Terminals.RETURNS);
			stoDecl();
			funDeclop1();
			funDeclop2();
			consume(Terminals.DO);
			cpsCmd();
			consume(Terminals.ENDFUN);
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void funDeclop1() throws GrammarError {
		if (terminal == Terminals.GLOBAL) {
			consume(Terminals.GLOBAL);
			globImps();
		} else if (terminal == Terminals.DO) {
		} else if (terminal == Terminals.LOCAL) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void funDeclop2() throws GrammarError {
		if (terminal == Terminals.LOCAL) {
			consume(Terminals.LOCAL);
			cpsStoDecl();
		} else if (terminal == Terminals.DO) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void procDecl() throws GrammarError {
		if (terminal == Terminals.PROC) {
			consume(Terminals.PROC);
			consume(Terminals.IDENT);
			paramList();
			consume(Terminals.RETURNS);
			stoDecl();
			funDeclop1();
			funDeclop2();
			consume(Terminals.DO);
			cpsCmd();
			consume(Terminals.ENDPROC);
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void stoDecl() throws GrammarError {
		if (terminal == Terminals.CHANGEMODE) {
			consume(Terminals.CHANGEMODE);
			typedIdent();
		} else if (terminal == Terminals.IDENT) {
			typedIdent();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void typedIdent() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			consume(Terminals.IDENT);
			consume(Terminals.COLON);
			consume(Terminals.TYPE);
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void paramList() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			consume(Terminals.LPAREN);
			paramListop();
			consume(Terminals.RPAREN);
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void paramListop() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			param();
			paramListopop();
		} else if (terminal == Terminals.CHANGEMODE) {
			param();
			paramListopop();
		} else if (terminal == Terminals.MECHMODE) {
			param();
			paramListopop();
		} else if (terminal == Terminals.FLOWMODE) {
			param();
			paramListopop();
		} else if (terminal == Terminals.RPAREN) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void paramListopop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			param();
			paramListopop();
		} else if (terminal == Terminals.RPAREN) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImp() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			globImpop1();
			globImpop2();
			consume(Terminals.IDENT);
		} else if (terminal == Terminals.CHANGEMODE) {
			globImpop1();
			globImpop2();
			consume(Terminals.IDENT);
		} else if (terminal == Terminals.FLOWMODE) {
			globImpop1();
			globImpop2();
			consume(Terminals.IDENT);
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImpop1() throws GrammarError {
		if (terminal == Terminals.FLOWMODE) {
			consume(Terminals.FLOWMODE);
		} else if (terminal == Terminals.MECHMODE) {
		} else if (terminal == Terminals.IDENT) {
		} else if (terminal == Terminals.CHANGEMODE) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImpop2() throws GrammarError {
		if (terminal == Terminals.CHANGEMODE) {
			consume(Terminals.CHANGEMODE);
		} else if (terminal == Terminals.IDENT) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImps() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			globImp();
			globImpsop();
		} else if (terminal == Terminals.CHANGEMODE) {
			globImp();
			globImpsop();
		} else if (terminal == Terminals.FLOWMODE) {
			globImp();
			globImpsop();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImpsop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			globImp();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cpsStoDecl() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			stoDecl();
			cpsStoDeclop();
		} else if (terminal == Terminals.CHANGEMODE) {
			stoDecl();
			cpsStoDeclop();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cpsStoDeclop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			consume(Terminals.SEMICOLON);
			stoDecl();
			cpsStoDeclop();
		} else if (terminal == Terminals.DO) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void param() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			globImpop1();
			paramop();
			globImpop2();
			typedIdent();
		} else if (terminal == Terminals.CHANGEMODE) {
			globImpop1();
			paramop();
			globImpop2();
			typedIdent();
		} else if (terminal == Terminals.MECHMODE) {
			globImpop1();
			paramop();
			globImpop2();
			typedIdent();
		} else if (terminal == Terminals.FLOWMODE) {
			globImpop1();
			paramop();
			globImpop2();
			typedIdent();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void paramop() throws GrammarError {
		if (terminal == Terminals.MECHMODE) {
			consume(Terminals.MECHMODE);
		} else if (terminal == Terminals.IDENT) {
		} else if (terminal == Terminals.CHANGEMODE) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cmd() throws GrammarError {
		if (terminal == Terminals.SKIP) {
			consume(Terminals.SKIP);
		} else if (terminal == Terminals.LPAREN) {
			expr();
			consume(Terminals.BECOMES);
			expr();
		} else if (terminal == Terminals.ADDOPR) {
			expr();
			consume(Terminals.BECOMES);
			expr();
		} else if (terminal == Terminals.NOT) {
			expr();
			consume(Terminals.BECOMES);
			expr();
		} else if (terminal == Terminals.IDENT) {
			expr();
			consume(Terminals.BECOMES);
			expr();
		} else if (terminal == Terminals.IF) {
			consume(Terminals.IF);
			expr();
			consume(Terminals.THEN);
			cpsCmd();
			consume(Terminals.ELSE);
			cpsCmd();
			consume(Terminals.ENDIF);
		} else if (terminal == Terminals.WHILE) {
			consume(Terminals.WHILE);
			expr();
			consume(Terminals.DO);
			cpsCmd();
			consume(Terminals.ENDWHILE);
		} else if (terminal == Terminals.CALL) {
			consume(Terminals.CALL);
			consume(Terminals.IDENT);
			exprList();
			cmdop();
		} else if (terminal == Terminals.DEBUGIN) {
			consume(Terminals.DEBUGIN);
			expr();
		} else if (terminal == Terminals.DEBUGOUT) {
			consume(Terminals.DEBUGOUT);
			expr();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cmdop() throws GrammarError {
		if (terminal == Terminals.INIT) {
			globInits();
		} else if (terminal == Terminals.ENDWHILE) {
		} else if (terminal == Terminals.ENDIF) {
		} else if (terminal == Terminals.ELSE) {
		} else if (terminal == Terminals.ENDPROC) {
		} else if (terminal == Terminals.ENDFUN) {
		} else if (terminal == Terminals.ENDPROGRAM) {
		} else if (terminal == Terminals.SEMICOLON) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globInits() throws GrammarError {
		if (terminal == Terminals.INIT) {
			consume(Terminals.INIT);
			idents();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void idents() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			consume(Terminals.IDENT);
			identsop();
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void identsop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			consume(Terminals.IDENT);
			identsop();
		} else if (terminal == Terminals.ENDWHILE) {
		} else if (terminal == Terminals.ENDIF) {
		} else if (terminal == Terminals.ELSE) {
		} else if (terminal == Terminals.ENDPROC) {
		} else if (terminal == Terminals.ENDFUN) {
		} else if (terminal == Terminals.ENDPROGRAM) {
		} else if (terminal == Terminals.SEMICOLON) {
		} else {
			throw new GrammarError("Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private Base consume(Terminals expectedTerminal) throws GrammarError {
		if (terminal == expectedTerminal) {
			Base consumedToken = token;
			if (terminal != Terminals.SENTINEL) {
				token = tokenList.nextToken();
				// maintain class invariant
				terminal = token.getTerminal();
			}
			return consumedToken;
		} else {
			throw new GrammarError("terminal expected: " + expectedTerminal + ", terminal found: " + terminal);
		}
	}

	private void expr() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			term1();
			exprbool();
			break;
		case ADDOPR:
			term1();
			exprbool();
			break;
		case NOT:
			term1();
			exprbool();
			break;
		case IDENT:
			term1();
			exprbool();
			break;
		case BOOLOR:
			consume(Terminals.BOOLOR);
			term1();
			term1opor();
			break;
		case BOOLAND:
			consume(Terminals.BOOLAND);
			term1();
			term1opand();
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void exprbool() throws GrammarError {
		switch (terminal) {
		case BOOLOR:
			consume(Terminals.BOOLOR);
			term1();
			term1opor();
			break;
		case BOOLAND:
			consume(Terminals.BOOLAND);
			term1();
			term1opand();
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void repADDOPRterm3() throws GrammarError {
		switch (terminal) {
		case ADDOPR:
			consume(Terminals.ADDOPR);
			term3();
			repADDOPRterm3();
			break;
		case BOOLOR:
			// epsilon
			break;
		case BOOLAND:
			// epsilon
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		case RELOPR:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void term3() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			factor();
			repMULTOPRfactor();
			break;
		case ADDOPR:
			factor();
			repMULTOPRfactor();
			break;
		case NOT:
			factor();
			repMULTOPRfactor();
			break;
		case IDENT:
			factor();
			repMULTOPRfactor();
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void repMULTOPRfactor() throws GrammarError {
		switch (terminal) {
		case MULTOPR:
			consume(Terminals.MULTOPR);
			factor();
			repMULTOPRfactor();
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		case RELOPR:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void factor() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			consume(Terminals.LPAREN);
			expr();
			consume(Terminals.RPAREN);
			break;
		case ADDOPR:
			monadicOpr();
			factor();
			break;
		case NOT:
			monadicOpr();
			factor();
			break;
		case IDENT:
			consume(Terminals.IDENT);
			factorop();
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void factorop() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			exprList();
			break;
		case ADDOPR:
			// epsilon
			break;
		case BOOLOR:
			// epsilon
			break;
		case BOOLAND:
			// epsilon
			break;
		case MULTOPR:
			// epsilon
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		case RELOPR:
			// epsilon
			break;
		case INIT:
			consume(Terminals.INIT);
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void term2() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			term3();
			repADDOPRterm3();
			break;
		case ADDOPR:
			term3();
			repADDOPRterm3();
			break;
		case NOT:
			term3();
			repADDOPRterm3();
			break;
		case IDENT:
			term3();
			repADDOPRterm3();
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void term2op() throws GrammarError {
		switch (terminal) {
		case RELOPR:
			consume(Terminals.RELOPR);
			term2();
			term2op();
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void term1() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			term2();
			term2op();
			break;
		case ADDOPR:
			term2();
			term2op();
			break;
		case NOT:
			term2();
			term2op();
			break;
		case IDENT:
			term2();
			term2op();
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void term1opor() throws GrammarError {
		switch (terminal) {
		case BOOLOR:
			consume(Terminals.BOOLOR);
			term1();
			term1opor();
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void term1opand() throws GrammarError {
		switch (terminal) {
		case BOOLAND:
			consume(Terminals.BOOLAND);
			term1();
			term1opand();
			break;
		case DO:
			// epsilon
			break;
		case THEN:
			// epsilon
			break;
		case ENDWHILE:
			// epsilon
			break;
		case ENDIF:
			// epsilon
			break;
		case ELSE:
			// epsilon
			break;
		case ENDPROC:
			// epsilon
			break;
		case ENDFUN:
			// epsilon
			break;
		case ENDPROGRAM:
			// epsilon
			break;
		case SEMICOLON:
			// epsilon
			break;
		case BECOMES:
			// epsilon
			break;
		case RPAREN:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void exprList() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			consume(Terminals.LPAREN);
			exprListop();
			consume(Terminals.RPAREN);
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void monadicOpr() throws GrammarError {
		switch (terminal) {
		case ADDOPR:
			consume(Terminals.ADDOPR);
			break;
		case NOT:
			consume(Terminals.NOT);
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void exprListop() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			expr();
			exprListopop();
			break;
		case ADDOPR:
			expr();
			exprListopop();
			break;
		case NOT:
			expr();
			exprListopop();
			break;
		case IDENT:
			expr();
			exprListopop();
			break;
		case RPAREN:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private void exprListopop() throws GrammarError {
		switch (terminal) {
		case COMMA:
			consume(Terminals.COMMA);
			expr();
			exprListopop();
			break;
		case RPAREN:
			// epsilon
			break;
		default:
			throw new GrammarError("Unexpected token");
		}
	}

}
