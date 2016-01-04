package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.parser.IConcTree.*;
import ch.fhnw.cpib.parser.IConcTree.IExpr;
import ch.fhnw.cpib.parser.IConcTree.IExprList;
import ch.fhnw.cpib.parser.IConcTree.IExprListop;
import ch.fhnw.cpib.parser.IConcTree.IExprListopop;
import ch.fhnw.cpib.parser.IConcTree.IExprbool;
import ch.fhnw.cpib.parser.IConcTree.IFactor;
import ch.fhnw.cpib.parser.IConcTree.IFactorop;
import ch.fhnw.cpib.parser.IConcTree.IIdents;
import ch.fhnw.cpib.parser.IConcTree.IIdentsop;
import ch.fhnw.cpib.parser.IConcTree.IProgParamList;
import ch.fhnw.cpib.parser.IConcTree.IMonadicOpr;
import ch.fhnw.cpib.parser.IConcTree.IRepaddoprterm3;
import ch.fhnw.cpib.parser.IConcTree.IRepmultoprfactor;
import ch.fhnw.cpib.parser.IConcTree.ITerm1;
import ch.fhnw.cpib.parser.IConcTree.ITerm1opand;
import ch.fhnw.cpib.parser.IConcTree.ITerm1opor;
import ch.fhnw.cpib.parser.IConcTree.ITerm2;
import ch.fhnw.cpib.parser.IConcTree.ITerm2op;
import ch.fhnw.cpib.parser.IConcTree.ITerm3;
import ch.fhnw.cpib.parser.IConcTree.ProgParamList;
import ch.fhnw.cpib.parser.interfaces.IParser;
import ch.fhnw.cpib.parser.interfaces.IProgram;
import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.AddOpr;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.BoolAnd;
import ch.fhnw.cpib.scanner.symbols.BoolOr;
import ch.fhnw.cpib.scanner.symbols.Comma;
import ch.fhnw.cpib.scanner.symbols.MultOpr;
import ch.fhnw.cpib.scanner.symbols.RelOpr;

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
	private IConcTree.IProgram program() throws GrammarError {
		if (terminal == Terminals.PROGRAM) {
			IToken program = consume(Terminals.PROGRAM);
			Ident ident = (Ident) consume(Terminals.IDENT);
			IConcTree.IProgParamList progParamList = progParamList();
			IConcTree.IProgramop programOp = programop();
			IToken doToken = consume(Terminals.DO);
			IConcTree.ICpsCmd cpsCmd = cpsCmd();
			IToken endProgram = consume(Terminals.ENDPROGRAM);
			return new IConcTree.Program(program, ident, progParamList,
					programOp, doToken, cpsCmd, endProgram);
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private IConcTree.IProgParamList progParamList() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			IToken lparen = consume(Terminals.LPAREN);
			IConcTree.IProgParamListop progParamListOp = progParamListop();
			IToken rparen = consume(Terminals.RPAREN);
			return new IConcTree.ProgParamList(lparen, progParamListOp, rparen);
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void progParamListop() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IProgParam progParam = progParam();
			IConcTree.IProgParamListopop progParamListOpOp = progParamListopop();
		} else if (terminal == Terminals.CHANGEMODE) {
			progParam();
			progParamListopop();
		} else if (terminal == Terminals.FLOWMODE) {
			progParam();
			progParamListopop();
		} else if (terminal == Terminals.RPAREN) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void progParamListopop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			progParam();
			progParamListopop();
		} else if (terminal == Terminals.RPAREN) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cpsDeclop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			consume(Terminals.SEMICOLON);
			decl();
			cpsDeclop();
		} else if (terminal == Terminals.DO) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private IConcTree.IProgramop programop() throws GrammarError {
		if (terminal == Terminals.GLOBAL) {
			IToken global = (IToken) consume(Terminals.GLOBAL);
			IConcTree.ICpsDecl cpsDecl = cpsDecl();
			return new IConcTree.ProgramopGlobal(global, cpsDecl);
		} else if (terminal == Terminals.DO) {
			return new IConcTree.ProgramopDo();
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void funDeclop1() throws GrammarError {
		if (terminal == Terminals.GLOBAL) {
			consume(Terminals.GLOBAL);
			globImps();
		} else if (terminal == Terminals.DO) {
		} else if (terminal == Terminals.LOCAL) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void funDeclop2() throws GrammarError {
		if (terminal == Terminals.LOCAL) {
			consume(Terminals.LOCAL);
			cpsStoDecl();
		} else if (terminal == Terminals.DO) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void stoDecl() throws GrammarError {
		if (terminal == Terminals.CHANGEMODE) {
			consume(Terminals.CHANGEMODE);
			typedIdent();
		} else if (terminal == Terminals.IDENT) {
			typedIdent();
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void typedIdent() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			consume(Terminals.IDENT);
			consume(Terminals.COLON);
			consume(Terminals.TYPE);
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void paramList() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			consume(Terminals.LPAREN);
			paramListop();
			consume(Terminals.RPAREN);
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void paramListopop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			param();
			paramListopop();
		} else if (terminal == Terminals.RPAREN) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImpop1() throws GrammarError {
		if (terminal == Terminals.FLOWMODE) {
			consume(Terminals.FLOWMODE);
		} else if (terminal == Terminals.MECHMODE) {
		} else if (terminal == Terminals.IDENT) {
		} else if (terminal == Terminals.CHANGEMODE) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImpop2() throws GrammarError {
		if (terminal == Terminals.CHANGEMODE) {
			consume(Terminals.CHANGEMODE);
		} else if (terminal == Terminals.IDENT) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globImpsop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			consume(Terminals.COMMA);
			globImp();
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void cpsStoDeclop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			consume(Terminals.SEMICOLON);
			stoDecl();
			cpsStoDeclop();
		} else if (terminal == Terminals.DO) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void paramop() throws GrammarError {
		if (terminal == Terminals.MECHMODE) {
			consume(Terminals.MECHMODE);
		} else if (terminal == Terminals.IDENT) {
		} else if (terminal == Terminals.CHANGEMODE) {
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private void globInits() throws GrammarError {
		if (terminal == Terminals.INIT) {
			consume(Terminals.INIT);
			idents();
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private IIdents idents() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			Base ident = consume(Terminals.IDENT);
			IIdentsop identsop = identsop();
			return new IConcTree.Idents(ident, identsop);
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
		}
	}

	private IIdentsop identsop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			Base comma = consume(Terminals.COMMA);
			Base ident = consume(Terminals.IDENT);
			IIdentsop identsop = identsop();
			return new IConcTree.IdentsopComma(comma, ident, identsop);
		} else if (terminal == Terminals.ENDWHILE) {
			return new IConcTree.Identsop();
		} else if (terminal == Terminals.ENDIF) {
			return new IConcTree.Identsop();
		} else if (terminal == Terminals.ELSE) {
			return new IConcTree.Identsop();
		} else if (terminal == Terminals.ENDPROC) {
			return new IConcTree.Identsop();
		} else if (terminal == Terminals.ENDFUN) {
			return new IConcTree.Identsop();
		} else if (terminal == Terminals.ENDPROGRAM) {
			return new IConcTree.Identsop();
		} else if (terminal == Terminals.SEMICOLON) {
			return new IConcTree.Identsop();
		} else {
			throw new GrammarError(
					"Does not start with terminal PROGRAM YOU DUMB LITTLE SHIT.");
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
			throw new GrammarError("terminal expected: " + expectedTerminal
					+ ", terminal found: " + terminal);
		}
	}

	private IExpr expr() throws GrammarError {
		ITerm1 term1;
		IExprbool exprbool;
		switch (terminal) {
		case LPAREN:
			term1 = term1();
			exprbool = exprbool();
			return new IConcTree.ExprLParen(term1, exprbool);
		case ADDOPR:
			term1 = term1();
			exprbool = exprbool();
			return new IConcTree.ExprAddopr(term1, exprbool);
		case NOT:
			term1 = term1();
			exprbool = exprbool();
			return new IConcTree.ExprNot(term1, exprbool);
		case IDENT:
			term1 = term1();
			exprbool = exprbool();
			return new IConcTree.ExprIdent(term1, exprbool);
		case BOOLOR:
			Base boolor = consume(Terminals.BOOLOR);
			term1 = term1();
			ITerm1opor term1opor = term1opor();
			return new IConcTree.ExprBoolOr((BoolOr) boolor, term1, term1opor);
		case BOOLAND:
			Base booland = consume(Terminals.BOOLAND);
			term1 = term1();
			ITerm1opand term1opand = term1opand();
			return new IConcTree.ExprBoolAnd((BoolAnd) booland, term1,
					term1opand);
		case DO:
			return new IConcTree.Expr();
		case THEN:
			// epsilon
			return new IConcTree.Expr();

		case ENDWHILE:
			// epsilon
			return new IConcTree.Expr();
		case ENDIF:
			// epsilon
			return new IConcTree.Expr();
		case ELSE:
			// epsilon
			return new IConcTree.Expr();
		case ENDPROC:
			// epsilon
			return new IConcTree.Expr();
		case ENDFUN:
			// epsilon
			return new IConcTree.Expr();
		case ENDPROGRAM:
			// epsilon
			return new IConcTree.Expr();
		case SEMICOLON:
			// epsilon
			return new IConcTree.Expr();
		case BECOMES:
			// epsilon
			return new IConcTree.Expr();
		case RPAREN:
			// epsilon
			return new IConcTree.Expr();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IExprbool exprbool() throws GrammarError {
		ITerm1 term1;
		switch (terminal) {
		case BOOLOR:
			Base boolor = consume(Terminals.BOOLOR);
			term1 = term1();
			ITerm1opor term1opor = term1opor();
			return new IConcTree.ExprboolBoolor((BoolOr) boolor, term1,
					term1opor);
		case BOOLAND:
			Base booland = consume(Terminals.BOOLAND);
			term1 = term1();
			ITerm1opand term1opand = term1opand();
			return new IConcTree.ExprboolBoolAnd((BoolAnd) booland, term1,
					term1opand);
		case DO:
			return new IConcTree.Exprbool();
		case THEN:
			return new IConcTree.Exprbool();
		case ENDWHILE:
			return new IConcTree.Exprbool();
		case ENDIF:
			return new IConcTree.Exprbool();
		case ELSE:
			return new IConcTree.Exprbool();
		case ENDPROC:
			return new IConcTree.Exprbool();
		case ENDFUN:
			return new IConcTree.Exprbool();
		case ENDPROGRAM:
			return new IConcTree.Exprbool();
		case SEMICOLON:
			return new IConcTree.Exprbool();
		case BECOMES:
			return new IConcTree.Exprbool();
		case RPAREN:
			return new IConcTree.Exprbool();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IRepaddoprterm3 repADDOPRterm3() throws GrammarError {
		switch (terminal) {
		case ADDOPR:
			Base addopr = consume(Terminals.ADDOPR);
			ITerm3 term3 = term3();
			IRepaddoprterm3 repADDOPRterm3 = repADDOPRterm3();
			return new IConcTree.RepAddoprTerm3AddOpr((AddOpr) addopr, term3,
					repADDOPRterm3);
		case BOOLOR:
			return new IConcTree.RepAddoprTerm3();
		case BOOLAND:
			return new IConcTree.RepAddoprTerm3();
		case DO:
			return new IConcTree.RepAddoprTerm3();
		case THEN:
			return new IConcTree.RepAddoprTerm3();
		case ENDWHILE:
			return new IConcTree.RepAddoprTerm3();
		case ENDIF:
			return new IConcTree.RepAddoprTerm3();
		case ELSE:
			return new IConcTree.RepAddoprTerm3();
		case ENDPROC:
			return new IConcTree.RepAddoprTerm3();
		case ENDFUN:
			return new IConcTree.RepAddoprTerm3();
		case ENDPROGRAM:
			return new IConcTree.RepAddoprTerm3();
		case SEMICOLON:
			return new IConcTree.RepAddoprTerm3();
		case BECOMES:
			return new IConcTree.RepAddoprTerm3();
		case RPAREN:
			return new IConcTree.RepAddoprTerm3();
		case RELOPR:
			return new IConcTree.RepAddoprTerm3();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private ITerm3 term3() throws GrammarError {
		IFactor factor;
		IRepmultoprfactor repMULTOPRfactor;
		switch (terminal) {
		case LPAREN:
			factor = factor();
			repMULTOPRfactor = repMULTOPRfactor();
			return new IConcTree.Term3LParen(factor, repMULTOPRfactor);
		case ADDOPR:
			factor = factor();
			repMULTOPRfactor = repMULTOPRfactor();
			return new IConcTree.Term3Addopr(factor, repMULTOPRfactor);
		case NOT:
			factor = factor();
			repMULTOPRfactor = repMULTOPRfactor();
			return new IConcTree.Term3Not(factor, repMULTOPRfactor);
		case IDENT:
			factor = factor();
			repMULTOPRfactor = repMULTOPRfactor();
			return new IConcTree.Term3Ident(factor, repMULTOPRfactor);
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IRepmultoprfactor repMULTOPRfactor() throws GrammarError {
		switch (terminal) {
		case MULTOPR:
			MultOpr multopr = (MultOpr) consume(Terminals.MULTOPR);
			IFactor factor = factor();
			IRepmultoprfactor repmultoprfactor = repMULTOPRfactor();
			return new IConcTree.RepMultoprFactorMultOpr(multopr, factor,
					repmultoprfactor);
		case DO:
			return new IConcTree.RepMultoprFactor();
		case THEN:
			return new IConcTree.RepMultoprFactor();
		case ENDWHILE:
			return new IConcTree.RepMultoprFactor();
		case ENDIF:
			return new IConcTree.RepMultoprFactor();
		case ELSE:
			return new IConcTree.RepMultoprFactor();
		case ENDPROC:
			return new IConcTree.RepMultoprFactor();
		case ENDFUN:
			return new IConcTree.RepMultoprFactor();
		case ENDPROGRAM:
			return new IConcTree.RepMultoprFactor();
		case SEMICOLON:
			return new IConcTree.RepMultoprFactor();
		case BECOMES:
			return new IConcTree.RepMultoprFactor();
		case RPAREN:
			return new IConcTree.RepMultoprFactor();
		case RELOPR:
			return new IConcTree.RepMultoprFactor();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IFactor factor() throws GrammarError {
		IMonadicOpr monadicOpr;
		IFactor factor;
		switch (terminal) {
		case LPAREN:
			IToken lparen = consume(Terminals.LPAREN);
			IExpr expr = expr();
			IToken rparen = consume(Terminals.RPAREN);
			return new IConcTree.FactorLParen(lparen, expr, rparen);
		case ADDOPR:
			monadicOpr = monadicOpr();
			factor = factor();
			return new IConcTree.FactorAddopr(monadicOpr, factor);
		case NOT:
			monadicOpr = monadicOpr();
			factor = factor();
			return new IConcTree.FactorNot(monadicOpr, factor);
		case IDENT:
			Ident ident = (Ident) consume(Terminals.IDENT);
			IFactorop factorop = factorop();
			return new IConcTree.FactorIdent(ident, factorop);
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IFactorop factorop() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			IExprList exprList = exprList();
			return new IConcTree.FactorOpLParen(exprList);
		case ADDOPR:
			return new IConcTree.FactorOp();
		case BOOLOR:
			return new IConcTree.FactorOp();
		case BOOLAND:
			return new IConcTree.FactorOp();
		case MULTOPR:
			return new IConcTree.FactorOp();
		case DO:
			return new IConcTree.FactorOp();
		case THEN:
			return new IConcTree.FactorOp();
		case ENDWHILE:
			return new IConcTree.FactorOp();
		case ENDIF:
			return new IConcTree.FactorOp();
		case ELSE:
			return new IConcTree.FactorOp();
		case ENDPROC:
			return new IConcTree.FactorOp();
		case ENDFUN:
			return new IConcTree.FactorOp();
		case ENDPROGRAM:
			return new IConcTree.FactorOp();
		case SEMICOLON:
			return new IConcTree.FactorOp();
		case BECOMES:
			return new IConcTree.FactorOp();
		case RPAREN:
			return new IConcTree.FactorOp();
		case RELOPR:
			return new IConcTree.FactorOp();
		case INIT:
			Base init = consume(Terminals.INIT);
			return new IConcTree.FactorOpInit(init);
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private ITerm2 term2() throws GrammarError {
		ITerm3 term3;
		IRepaddoprterm3 repaddoprterm3;
		switch (terminal) {
		case LPAREN:
			term3 = term3();
			repaddoprterm3 = repADDOPRterm3();
			return new IConcTree.Term2LParen(term3, repaddoprterm3);
		case ADDOPR:
			term3 = term3();
			repaddoprterm3 = repADDOPRterm3();
			return new IConcTree.Term2Addopr(term3, repaddoprterm3);
		case NOT:
			term3 = term3();
			repaddoprterm3 = repADDOPRterm3();
			return new IConcTree.Term2Not(term3, repaddoprterm3);
		case IDENT:
			term3 = term3();
			repaddoprterm3 = repADDOPRterm3();
			return new IConcTree.Term2Ident(term3, repaddoprterm3);
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private ITerm2op term2op() throws GrammarError {

		switch (terminal) {
		case RELOPR:
			RelOpr relopr = (RelOpr) consume(Terminals.RELOPR);
			ITerm2 term2 = term2();
			ITerm2op term2op = term2op();
			return new IConcTree.Term2opRelOpr(relopr, term2, term2op);
		case DO:
			return new IConcTree.Term2op();
		case THEN:
			return new IConcTree.Term2op();
		case ENDWHILE:
			return new IConcTree.Term2op();
		case ENDIF:
			return new IConcTree.Term2op();
		case ELSE:
			return new IConcTree.Term2op();
		case ENDPROC:
			return new IConcTree.Term2op();
		case ENDFUN:
			return new IConcTree.Term2op();
		case ENDPROGRAM:
			return new IConcTree.Term2op();
		case SEMICOLON:
			return new IConcTree.Term2op();
		case BECOMES:
			return new IConcTree.Term2op();
		case RPAREN:
			return new IConcTree.Term2op();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private ITerm1 term1() throws GrammarError {
		ITerm2 term2;
		ITerm2op term2op;
		switch (terminal) {
		case LPAREN:
			term2 = term2();
			term2op = term2op();
			return new IConcTree.Term1LParen(term2, term2op);
		case ADDOPR:
			term2 = term2();
			term2op = term2op();
			return new IConcTree.Term1Addopr(term2, term2op);
		case NOT:
			term2 = term2();
			term2op = term2op();
			return new IConcTree.Term1Not(term2, term2op);
		case IDENT:
			term2 = term2();
			term2op = term2op();
			return new IConcTree.Term1Ident(term2, term2op);
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private ITerm1opor term1opor() throws GrammarError {

		switch (terminal) {
		case BOOLOR:
			BoolOr boolor = (BoolOr) consume(Terminals.BOOLOR);
			ITerm1 term1 = term1();
			ITerm1opor term1opor = term1opor();
			return new IConcTree.Term1OpOrBoolOr(boolor, term1, term1opor);
		case DO:
			return new IConcTree.Term1OpOr();
		case THEN:
			return new IConcTree.Term1OpOr();
		case ENDWHILE:
			return new IConcTree.Term1OpOr();
		case ENDIF:
			return new IConcTree.Term1OpOr();
		case ELSE:
			return new IConcTree.Term1OpOr();
		case ENDPROC:
			return new IConcTree.Term1OpOr();
		case ENDFUN:
			return new IConcTree.Term1OpOr();
		case ENDPROGRAM:
			return new IConcTree.Term1OpOr();
		case SEMICOLON:
			return new IConcTree.Term1OpOr();
		case BECOMES:
			return new IConcTree.Term1OpOr();
		case RPAREN:
			return new IConcTree.Term1OpOr();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private ITerm1opand term1opand() throws GrammarError {
		switch (terminal) {
		case BOOLAND:
			BoolAnd booland = (BoolAnd) consume(Terminals.BOOLAND);
			ITerm1 term1 = term1();
			ITerm1opand term1opand = term1opand();
			return new IConcTree.Term1OpAndBoolAnd(booland, term1, term1opand);
		case DO:
			return new IConcTree.Term1OpAnd();
		case THEN:
			return new IConcTree.Term1OpAnd();
		case ENDWHILE:
			return new IConcTree.Term1OpAnd();
		case ENDIF:
			return new IConcTree.Term1OpAnd();
		case ELSE:
			return new IConcTree.Term1OpAnd();
		case ENDPROC:
			return new IConcTree.Term1OpAnd();
		case ENDFUN:
			return new IConcTree.Term1OpAnd();
		case ENDPROGRAM:
			return new IConcTree.Term1OpAnd();
		case SEMICOLON:
			return new IConcTree.Term1OpAnd();
		case BECOMES:
			return new IConcTree.Term1OpAnd();
		case RPAREN:
			return new IConcTree.Term1OpAnd();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IExprList exprList() throws GrammarError {
		switch (terminal) {
		case LPAREN:
			Base lparen = consume(Terminals.LPAREN);
			IExprListop exprListop = exprListop();
			Base rparen = consume(Terminals.RPAREN);
			return new IConcTree.ExprListLParen(lparen, exprListop, rparen);
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IMonadicOpr monadicOpr() throws GrammarError {
		switch (terminal) {
		case ADDOPR:
			AddOpr addopr = (AddOpr) consume(Terminals.ADDOPR);
			return new IConcTree.MonadicOprAddOpr(addopr);
		case NOT:
			Base not = consume(Terminals.NOT);
			return new IConcTree.MonadicOprNot(not);
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IExprListop exprListop() throws GrammarError {
		IExpr expr;
		IExprListopop exprListopop;
		switch (terminal) {
		case LPAREN:
			expr = expr();
			exprListopop = exprListopop();
			return new IConcTree.ExprListOpLParen(expr, exprListopop);
		case ADDOPR:
			expr = expr();
			exprListopop = exprListopop();
			return new IConcTree.ExprListOpAddOpr(expr, exprListopop);
		case NOT:
			expr = expr();
			exprListopop = exprListopop();
			return new IConcTree.ExprListOpNot(expr, exprListopop);
		case IDENT:
			expr = expr();
			exprListopop = exprListopop();
			return new IConcTree.ExprListOpIdent(expr, exprListopop);
		case RPAREN:
			return new IConcTree.ExprListOp();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private IExprListopop exprListopop() throws GrammarError {
		switch (terminal) {
		case COMMA:
			Comma comma = (Comma) consume(Terminals.COMMA);
			IExpr expr = expr();
			IExprListopop exprListopop = exprListopop();
			return new IConcTree.ExprListOpOpComma(comma, expr, exprListopop);
		case RPAREN:
			return new IConcTree.ExprListOpOp();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

}
