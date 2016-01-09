package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.parser.IConcTree.IConcExpr;
import ch.fhnw.cpib.parser.IConcTree.IExprList;
import ch.fhnw.cpib.parser.IConcTree.IExprListop;
import ch.fhnw.cpib.parser.IConcTree.IExprListopop;
import ch.fhnw.cpib.parser.IConcTree.IExprbool;
import ch.fhnw.cpib.parser.IConcTree.IFactor;
import ch.fhnw.cpib.parser.IConcTree.IFactorop;
import ch.fhnw.cpib.parser.IConcTree.IIdents;
import ch.fhnw.cpib.parser.IConcTree.IIdentsop;
import ch.fhnw.cpib.parser.IConcTree.IMonadicOpr;
import ch.fhnw.cpib.parser.IConcTree.IRepaddoprterm3;
import ch.fhnw.cpib.parser.IConcTree.IRepmultoprfactor;
import ch.fhnw.cpib.parser.IConcTree.ITerm1;
import ch.fhnw.cpib.parser.IConcTree.ITerm1opand;
import ch.fhnw.cpib.parser.IConcTree.ITerm1opor;
import ch.fhnw.cpib.parser.IConcTree.ITerm2;
import ch.fhnw.cpib.parser.IConcTree.ITerm2op;
import ch.fhnw.cpib.parser.IConcTree.ITerm3;
import ch.fhnw.cpib.parser.IConcTree.RepMultoprFactor;
import ch.fhnw.cpib.parser.interfaces.IParser;
import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.AddOpr;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.BoolAnd;
import ch.fhnw.cpib.scanner.symbols.BoolOr;
import ch.fhnw.cpib.scanner.symbols.ChangeModeToken;
import ch.fhnw.cpib.scanner.symbols.Comma;
import ch.fhnw.cpib.scanner.symbols.FlowModeToken;
import ch.fhnw.cpib.scanner.symbols.MechModeToken;
import ch.fhnw.cpib.scanner.symbols.MultOpr;
import ch.fhnw.cpib.scanner.symbols.RelOpr;
import ch.fhnw.cpib.scanner.symbols.Type;

public class Parser implements IParser {
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
	public IConcTree.IProgram parse() throws GrammarError {

		IConcTree.IProgram program = program();

		consume(Terminals.SENTINEL);
		return program;
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
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IProgParamList progParamList() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			IToken lparen = consume(Terminals.LPAREN);
			IConcTree.IProgParamListop progParamListOp = progParamListop();
			IToken rparen = consume(Terminals.RPAREN);
			return new IConcTree.ProgParamList(lparen, progParamListOp, rparen);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IProgParamListop progParamListop() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IProgParam progParam = progParam();
			IConcTree.IProgParamListopop progParamListOpOp = progParamListopop();
			return new IConcTree.ProgParamListopIdent(progParam,
					progParamListOpOp);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IProgParam progParam = progParam();
			IConcTree.IProgParamListopop progParamListOpOp = progParamListopop();
			return new IConcTree.ProgParamListopChangemode(progParam,
					progParamListOpOp);
		} else if (terminal == Terminals.FLOWMODE) {
			IConcTree.IProgParam progParam = progParam();
			IConcTree.IProgParamListopop progParamListOpOp = progParamListopop();
			return new IConcTree.ProgParamListopFlowmode(progParam,
					progParamListOpOp);
		} else if (terminal == Terminals.RPAREN) {
			return new IConcTree.ProgParamListop();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IProgParamListopop progParamListopop()
			throws GrammarError {
		if (terminal == Terminals.COMMA) {
			Comma comma = (Comma) consume(Terminals.COMMA);
			IConcTree.IProgParam progParam = progParam();
			IConcTree.IProgParamListopop progParamListOpOp = progParamListopop();
			return new IConcTree.ProgParamListopopComma(comma, progParam,
					progParamListOpOp);
		} else if (terminal == Terminals.RPAREN) {
			return new IConcTree.ProgParamListopop();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IProgParam progParam() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IGlobImpop1 globImpOp1 = globImpop1();
			IConcTree.IGlobImpop2 globImpOp2 = globImpop2();
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.ProgParamIdent(globImpOp1, globImpOp2,
					typedIdent);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IGlobImpop1 globImpOp1 = globImpop1();
			IConcTree.IGlobImpop2 globImpOp2 = globImpop2();
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.ProgParamChangemode(globImpOp1, globImpOp2,
					typedIdent);
		} else if (terminal == Terminals.FLOWMODE) {
			IConcTree.IGlobImpop1 globImpOp1 = globImpop1();
			IConcTree.IGlobImpop2 globImpOp2 = globImpop2();
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.ProgParamFlowmode(globImpOp1, globImpOp2,
					typedIdent);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ICpsDecl cpsDecl() throws GrammarError {
		if (terminal == Terminals.PROC) {
			IConcTree.IDecl decl = decl();
			IConcTree.ICpsDeclop cpsDeclop = cpsDeclop();
			return new IConcTree.CpsDecl(decl, cpsDeclop);
		} else if (terminal == Terminals.FUN) {
			IConcTree.IDecl decl = decl();
			IConcTree.ICpsDeclop cpsDeclop = cpsDeclop();
			return new IConcTree.CpsDecl(decl, cpsDeclop);
		} else if (terminal == Terminals.IDENT) {
			IConcTree.IDecl decl = decl();
			IConcTree.ICpsDeclop cpsDeclop = cpsDeclop();
			return new IConcTree.CpsDecl(decl, cpsDeclop);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IDecl decl = decl();
			IConcTree.ICpsDeclop cpsDeclop = cpsDeclop();
			return new IConcTree.CpsDecl(decl, cpsDeclop);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ICpsDeclop cpsDeclop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			Base semicolon = consume(Terminals.SEMICOLON);
			IConcTree.IDecl decl = decl();
			IConcTree.ICpsDeclop cpsDeclOp = cpsDeclop();
			return new IConcTree.CpsDeclopSemicolon(semicolon, decl, cpsDeclOp);
		} else if (terminal == Terminals.DO) {
			return new IConcTree.CpsDeclopDo();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ICpsCmd cpsCmd() throws GrammarError {
		if (terminal == Terminals.DEBUGOUT) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.DEBUGIN) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.CALL) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.WHILE) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.IF) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.LPAREN) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.ADDOPR) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.NOT) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.IDENT) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else if (terminal == Terminals.SKIP) {
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmd(cmd, cpsCmdOp);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ICpsCmdop cpsCmdop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			Base semicolon = consume(Terminals.SEMICOLON);
			IConcTree.IConcCmd cmd = cmd();
			IConcTree.ICpsCmdop cpsCmdOp = cpsCmdop();
			return new IConcTree.CpsCmdopSemicolon(semicolon, cmd, cpsCmdOp);
		} else if (terminal == Terminals.ENDWHILE) {
			return new IConcTree.CpsCmdop();
		} else if (terminal == Terminals.ENDIF) {
			return new IConcTree.CpsCmdop();
		} else if (terminal == Terminals.ELSE) {
			return new IConcTree.CpsCmdop();
		} else if (terminal == Terminals.ENDPROC) {
			return new IConcTree.CpsCmdop();
		} else if (terminal == Terminals.ENDFUN) {
			return new IConcTree.CpsCmdop();
		} else if (terminal == Terminals.ENDPROGRAM) {
			return new IConcTree.CpsCmdop();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
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
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	// TODO:
	private IConcTree.IDecl decl() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IStoDecl stoDecl = stoDecl();
			return new IConcTree.DeclIdent(stoDecl);
		} else if (terminal == Terminals.CHANGEMODE) {
			return new IConcTree.DeclChangemode(stoDecl());
		} else if (terminal == Terminals.FUN) {
			return new IConcTree.DeclFun(funDecl());
		} else if (terminal == Terminals.PROC) {
			return new IConcTree.DeclProc(procDecl());
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IFunDecl funDecl() throws GrammarError {
		if (terminal == Terminals.FUN) {
			IToken fun = consume(Terminals.FUN);
			Ident ident = (Ident) consume(Terminals.IDENT);
			IConcTree.IParamList paramList = paramList();
			IToken returns = consume(Terminals.RETURNS);
			IConcTree.IStoDecl stoDecl = stoDecl();
			IConcTree.IFunDeclop1 funDeclop1 = funDeclop1();
			IConcTree.IFunDeclop2 funDeclop2 = funDeclop2();
			IToken tokenDo = consume(Terminals.DO);
			IConcTree.ICpsCmd cpsCmd = cpsCmd();
			IToken endfun = consume(Terminals.ENDFUN);
			return new IConcTree.FunDecl(fun, ident, paramList, returns,
					stoDecl, funDeclop1, funDeclop2, tokenDo, cpsCmd, endfun);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IFunDeclop1 funDeclop1() throws GrammarError {
		if (terminal == Terminals.GLOBAL) {
			IToken global = consume(Terminals.GLOBAL);
			IConcTree.IGlobImps globImps = globImps();
			return new IConcTree.FunDeclop1Global(global, globImps);
		} else if (terminal == Terminals.DO) {
			return new IConcTree.FunDeclop1();
		} else if (terminal == Terminals.LOCAL) {
			return new IConcTree.FunDeclop1();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IFunDeclop2 funDeclop2() throws GrammarError {
		if (terminal == Terminals.LOCAL) {
			IToken local = consume(Terminals.LOCAL);
			IConcTree.ICpsStoDecl cpsStoDecl = cpsStoDecl();
			return new IConcTree.FunDeclop2Local(local, cpsStoDecl);
		} else if (terminal == Terminals.DO) {
			return new IConcTree.FunDeclop2();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IProcDecl procDecl() throws GrammarError {
		if (terminal == Terminals.PROC) {
			IToken proc = consume(Terminals.PROC);
			Ident ident = (Ident) consume(Terminals.IDENT);
			IConcTree.IParamList paramList = paramList();
			IConcTree.IFunDeclop1 funDeclOp1 = funDeclop1();
			IConcTree.IFunDeclop2 funDeclOp2 = funDeclop2();
			IToken tokenDo = consume(Terminals.DO);
			IConcTree.ICpsCmd cpsCmd = cpsCmd();
			IToken endProc = consume(Terminals.ENDPROC);
			return new IConcTree.ProcDecl(proc, ident, paramList, funDeclOp1,
					funDeclOp2, tokenDo, cpsCmd, endProc);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IStoDecl stoDecl() throws GrammarError {
		if (terminal == Terminals.CHANGEMODE) {
			ChangeModeToken changeMode = (ChangeModeToken) consume(Terminals.CHANGEMODE);
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.StoDeclChangemode(changeMode, typedIdent);
		} else if (terminal == Terminals.IDENT) {
			return new IConcTree.StoDeclIdent(typedIdent());
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ITypedIdent typedIdent() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			return new IConcTree.TypedIdent((Ident) consume(Terminals.IDENT),
					consume(Terminals.COLON), (Type) consume(Terminals.TYPE));
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IParamList paramList() throws GrammarError {
		if (terminal == Terminals.LPAREN) {
			IToken lparen = consume(Terminals.LPAREN);
			IConcTree.IParamListop paramListOp = paramListop();
			IToken rparen = consume(Terminals.RPAREN);
			return new IConcTree.ParamList(lparen, paramListOp, rparen);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IParamListop paramListop() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IParam param = param();
			IConcTree.IParamListopop paramListOpOp = paramListopop();
			return new IConcTree.ParamListop(param, paramListOpOp);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IParam param = param();
			IConcTree.IParamListopop paramListOpOp = paramListopop();
			return new IConcTree.ParamListop(param, paramListOpOp);
		} else if (terminal == Terminals.MECHMODE) {
			IConcTree.IParam param = param();
			IConcTree.IParamListopop paramListOpOp = paramListopop();
			return new IConcTree.ParamListop(param, paramListOpOp);
		} else if (terminal == Terminals.FLOWMODE) {
			IConcTree.IParam param = param();
			IConcTree.IParamListopop paramListOpOp = paramListopop();
			return new IConcTree.ParamListop(param, paramListOpOp);
		} else if (terminal == Terminals.RPAREN) {
			return new IConcTree.ParamListopRparen();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IParamListopop paramListopop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			IToken comma = consume(Terminals.COMMA);
			IConcTree.IParam param = param();
			IConcTree.IParamListopop paramListOpOp = paramListopop();
			return new IConcTree.ParamListopopComma(comma, param, paramListOpOp);
		} else if (terminal == Terminals.RPAREN) {
			return new IConcTree.ParamListopopRparen();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IGlobImp globImp() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IGlobImpop1 globImpOp1 = globImpop1();
			IConcTree.IGlobImpop2 globImpOp2 = globImpop2();
			Ident ident = (Ident) consume(Terminals.IDENT);
			return new IConcTree.GlobImp(globImpOp1, globImpOp2, ident);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IGlobImpop1 globImpOp1 = globImpop1();
			IConcTree.IGlobImpop2 globImpOp2 = globImpop2();
			Ident ident = (Ident) consume(Terminals.IDENT);
			return new IConcTree.GlobImp(globImpOp1, globImpOp2, ident);
		} else if (terminal == Terminals.FLOWMODE) {
			IConcTree.IGlobImpop1 globImpOp1 = globImpop1();
			IConcTree.IGlobImpop2 globImpOp2 = globImpop2();
			Ident ident = (Ident) consume(Terminals.IDENT);
			return new IConcTree.GlobImp(globImpOp1, globImpOp2, ident);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IGlobImpop1 globImpop1() throws GrammarError {
		if (terminal == Terminals.FLOWMODE) {
			return new IConcTree.GlobImpop1Flowmode(
					(FlowModeToken) consume(Terminals.FLOWMODE));
		} else if (terminal == Terminals.MECHMODE) {
			return new IConcTree.GlobImpop1();
		} else if (terminal == Terminals.IDENT) {
			return new IConcTree.GlobImpop1();
		} else if (terminal == Terminals.CHANGEMODE) {
			return new IConcTree.GlobImpop1();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IGlobImpop2 globImpop2() throws GrammarError {
		if (terminal == Terminals.CHANGEMODE) {
			return new IConcTree.GlobImpop2Changemode(
					(ChangeModeToken) consume(Terminals.CHANGEMODE));
		} else if (terminal == Terminals.IDENT) {
			return new IConcTree.GlobImpop2Ident();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IGlobImps globImps() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IGlobImp globImp = globImp();
			IConcTree.IGlobImpsop globImpsop = globImpsop();
			return new IConcTree.GlobImps(globImp, globImpsop);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IGlobImp globImp = globImp();
			IConcTree.IGlobImpsop globImpsop = globImpsop();
			return new IConcTree.GlobImps(globImp, globImpsop);
		} else if (terminal == Terminals.FLOWMODE) {
			IConcTree.IGlobImp globImp = globImp();
			IConcTree.IGlobImpsop globImpsop = globImpsop();
			return new IConcTree.GlobImps(globImp, globImpsop);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IGlobImpsop globImpsop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			IToken comma = consume(Terminals.COMMA);
			IConcTree.IGlobImp globImp = globImp();
			IConcTree.IGlobImpsop globImpsop = globImpsop();
			return new IConcTree.GlobImpsopComma(comma, globImp, globImpsop);
		} else if (terminal == Terminals.DO) {
			return new IConcTree.GlobImpsop();
		} else if (terminal == Terminals.LOCAL) {
			return new IConcTree.GlobImpsop();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ICpsStoDecl cpsStoDecl() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IStoDecl stoDecl = stoDecl();
			IConcTree.ICpsStoDeclop cpsStoDeclop = cpsStoDeclop();
			return new IConcTree.CpsStoDecl(stoDecl, cpsStoDeclop);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IStoDecl stoDecl = stoDecl();
			IConcTree.ICpsStoDeclop cpsStoDeclop = cpsStoDeclop();
			return new IConcTree.CpsStoDecl(stoDecl, cpsStoDeclop);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ICpsStoDeclop cpsStoDeclop() throws GrammarError {
		if (terminal == Terminals.SEMICOLON) {
			IToken semicolon = consume(Terminals.SEMICOLON);
			IConcTree.IStoDecl stoDecl = stoDecl();
			IConcTree.ICpsStoDeclop cpsStoDeclop = cpsStoDeclop();
			return new IConcTree.CpsStoDeclopSemicolon(semicolon, stoDecl,
					cpsStoDeclop);
		} else if (terminal == Terminals.DO) {
			return new IConcTree.CpsStoDeclopDo();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IParam param() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			IConcTree.IGlobImpop1 globImpop1 = globImpop1();
			IConcTree.IParamop paramOp = paramop();
			IConcTree.IGlobImpop2 globImpop2 = globImpop2();
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.Param(globImpop1, paramOp, globImpop2,
					typedIdent);
		} else if (terminal == Terminals.CHANGEMODE) {
			IConcTree.IGlobImpop1 globImpop1 = globImpop1();
			IConcTree.IParamop paramOp = paramop();
			IConcTree.IGlobImpop2 globImpop2 = globImpop2();
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.Param(globImpop1, paramOp, globImpop2,
					typedIdent);
		} else if (terminal == Terminals.MECHMODE) {
			IConcTree.IGlobImpop1 globImpop1 = globImpop1();
			IConcTree.IParamop paramOp = paramop();
			IConcTree.IGlobImpop2 globImpop2 = globImpop2();
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.Param(globImpop1, paramOp, globImpop2,
					typedIdent);
		} else if (terminal == Terminals.FLOWMODE) {
			IConcTree.IGlobImpop1 globImpop1 = globImpop1();
			IConcTree.IParamop paramOp = paramop();
			IConcTree.IGlobImpop2 globImpop2 = globImpop2();
			IConcTree.ITypedIdent typedIdent = typedIdent();
			return new IConcTree.Param(globImpop1, paramOp, globImpop2,
					typedIdent);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IParamop paramop() throws GrammarError {
		if (terminal == Terminals.MECHMODE) {
			return new IConcTree.ParamopMechmode(
					(MechModeToken) consume(Terminals.MECHMODE));
		} else if (terminal == Terminals.IDENT) {
			return new IConcTree.Paramop();
		} else if (terminal == Terminals.CHANGEMODE) {
			return new IConcTree.Paramop();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IConcCmd cmd() throws GrammarError {
		if (terminal == Terminals.SKIP) {
			IToken skip = consume(Terminals.SKIP);
			return new IConcTree.CmdSkip(skip);
		} else if (terminal == Terminals.LPAREN) {
			IConcTree.IConcExpr expr1 = expr();
			IToken becomes = consume(Terminals.BECOMES);
			IConcTree.IConcExpr expr2 = expr();
			return new IConcTree.CmdBecomes(expr1, becomes, expr2);
		} else if (terminal == Terminals.ADDOPR) {
			IConcTree.IConcExpr expr1 = expr();
			IToken becomes = consume(Terminals.BECOMES);
			IConcTree.IConcExpr expr2 = expr();
			return new IConcTree.CmdBecomes(expr1, becomes, expr2);
		} else if (terminal == Terminals.NOT) {
			IConcTree.IConcExpr expr1 = expr();
			IToken becomes = consume(Terminals.BECOMES);
			IConcTree.IConcExpr expr2 = expr();
			return new IConcTree.CmdBecomes(expr1, becomes, expr2);
		} else if (terminal == Terminals.IDENT) {
			IConcTree.IConcExpr expr1 = expr();
			IToken becomes = consume(Terminals.BECOMES);
			IConcTree.IConcExpr expr2 = expr();
			return new IConcTree.CmdBecomes(expr1, becomes, expr2);
		} else if (terminal == Terminals.IF) {
			IToken ifToken = consume(Terminals.IF);
			IConcTree.IConcExpr expr = expr();
			IToken then = consume(Terminals.THEN);
			IConcTree.ICpsCmd cpsCmd1 = cpsCmd();
			IToken elseToken = consume(Terminals.ELSE);
			IConcTree.ICpsCmd cpsCmd2 = cpsCmd();
			IToken endif = consume(Terminals.ENDIF);
			return new IConcTree.CmdIf(ifToken, expr, then, cpsCmd1, elseToken,
					cpsCmd2, endif);
		} else if (terminal == Terminals.WHILE) {
			IToken tokenWhile = consume(Terminals.WHILE);
			IConcTree.IConcExpr expr = expr();
			IToken tokenDo = consume(Terminals.DO);
			IConcTree.ICpsCmd cpsCmd = cpsCmd();
			IToken endWhile = consume(Terminals.ENDWHILE);
			return new IConcTree.CmdWhile(tokenWhile, expr, tokenDo, cpsCmd,
					endWhile);
		} else if (terminal == Terminals.CALL) {
			IToken call = consume(Terminals.CALL);
			Ident ident = (Ident) consume(Terminals.IDENT);
			IConcTree.IExprList exprList = exprList();
			IConcTree.ICmdop cmdOp = cmdop();
			return new IConcTree.CmdCall(call, ident, exprList, cmdOp);
		} else if (terminal == Terminals.DEBUGIN) {
			IToken debugin = consume(Terminals.DEBUGIN);
			IConcTree.IConcExpr expr = expr();
			return new IConcTree.CmdDebugin(debugin, expr);
		} else if (terminal == Terminals.DEBUGOUT) {
			IToken debugout = consume(Terminals.DEBUGOUT);
			IConcTree.IConcExpr expr = expr();
			return new IConcTree.CmdDebugout(debugout, expr);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.ICmdop cmdop() throws GrammarError {
		if (terminal == Terminals.INIT) {
			IConcTree.IGlobInits globInits = globInits();
			return new IConcTree.CmdopInit(globInits);
		} else if (terminal == Terminals.ENDWHILE) {
			return new IConcTree.Cmdop();
		} else if (terminal == Terminals.ENDIF) {
			return new IConcTree.Cmdop();
		} else if (terminal == Terminals.ELSE) {
			return new IConcTree.Cmdop();
		} else if (terminal == Terminals.ENDPROC) {
			return new IConcTree.Cmdop();
		} else if (terminal == Terminals.ENDFUN) {
			return new IConcTree.Cmdop();
		} else if (terminal == Terminals.ENDPROGRAM) {
			return new IConcTree.Cmdop();
		} else if (terminal == Terminals.SEMICOLON) {
			return new IConcTree.Cmdop();
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IConcTree.IGlobInits globInits() throws GrammarError {
		if (terminal == Terminals.INIT) {
			IToken init = consume(Terminals.INIT);
			IConcTree.IIdents idents = idents();
			return new IConcTree.GlobInits(init, idents);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IIdents idents() throws GrammarError {
		if (terminal == Terminals.IDENT) {
			Ident ident = (Ident) consume(Terminals.IDENT);
			IIdentsop identsop = identsop();
			return new IConcTree.Idents(ident, identsop);
		} else {
			throw new GrammarError("Wrong token found " + terminal);
		}
	}

	private IIdentsop identsop() throws GrammarError {
		if (terminal == Terminals.COMMA) {
			Base comma = consume(Terminals.COMMA);
			Ident ident = (Ident) consume(Terminals.IDENT);
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
			throw new GrammarError("Wrong token found " + terminal);
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

	private IConcExpr expr() throws GrammarError {
		ITerm1 term1;
		IExprbool exprbool;
		switch (terminal) {
		case LITERAL:
			term1 = term1();
			exprbool = exprbool();
			return new IConcTree.ExprLiteral(term1, exprbool);
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
		case COMMA:
			return new IConcTree.Exprbool();
		case SENTINEL:
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
		case COMMA:
			return new IConcTree.RepAddoprTerm3();
		case SENTINEL:
			return new IConcTree.RepAddoprTerm3();
		default:
			throw new GrammarError("Unexpected token: " + terminal);
		}
	}

	private ITerm3 term3() throws GrammarError {
		IFactor factor;
		IRepmultoprfactor repMULTOPRfactor;
		switch (terminal) {
		case LITERAL:
			factor = factor();
			repMULTOPRfactor = repMULTOPRfactor();
			return new IConcTree.Term3Literal(factor, repMULTOPRfactor);
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
		case ADDOPR:
			return new IConcTree.RepMultoprFactor();
		case COMMA:
			return new IConcTree.RepMultoprFactor();
		case SENTINEL:
			return new IConcTree.RepMultoprFactor();
		case BOOLAND:
			return new IConcTree.RepMultoprFactor();
		case BOOLOR:
			return new IConcTree.RepMultoprFactor();
		default:
			throw new GrammarError("Unexpected token: " + terminal);
		}
	}

	private IFactor factor() throws GrammarError {
		IMonadicOpr monadicOpr;
		IFactor factor;
		switch (terminal) {
		case LPAREN:
			IToken lparen = consume(Terminals.LPAREN);
			IConcExpr expr = expr();
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
		case LITERAL:
			Literal literal = (Literal) consume(Terminals.LITERAL);
			return new IConcTree.FactorLiteral(literal);
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
		case COMMA:
			return new IConcTree.FactorOp();
		case INIT:
			Base init = consume(Terminals.INIT);
			return new IConcTree.FactorOpInit(init);
		case SENTINEL:
			return new IConcTree.FactorOp();
		default:
			throw new GrammarError("Unexpected token: " + terminal);
		}
	}

	private ITerm2 term2() throws GrammarError {
		ITerm3 term3;
		IRepaddoprterm3 repaddoprterm3;
		switch (terminal) {
		case LITERAL:
			term3 = term3();
			repaddoprterm3 = repADDOPRterm3();
			return new IConcTree.Term2Literal(term3, repaddoprterm3);
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
		case COMMA:
			return new IConcTree.Term2op();
		case SENTINEL:
			return new IConcTree.Term2op();
		case BOOLAND:
			return new IConcTree.Term2op();
		case BOOLOR:
			return new IConcTree.Term2op();
		default:
			throw new GrammarError("Unexpected token");
		}
	}

	private ITerm1 term1() throws GrammarError {
		ITerm2 term2;
		ITerm2op term2op;
		switch (terminal) {
		case LITERAL:
			term2 = term2();
			term2op = term2op();
			return new IConcTree.Term1Literal(term2, term2op);
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
		IConcExpr expr;
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
			IToken comma = consume(Terminals.COMMA);
			IConcExpr expr = expr();
			IExprListopop exprListopop = exprListopop();
			return new IConcTree.ExprListOpOpComma(comma, expr, exprListopop);
		case RPAREN:
			return new IConcTree.ExprListOpOp();
		default:
			throw new GrammarError("Unexpected token");
		}
	}
}