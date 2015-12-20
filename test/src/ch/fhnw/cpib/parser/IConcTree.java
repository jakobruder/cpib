package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.Comma;

public interface IConcTree {
	public interface IExpr {

	}

	public interface IExprbool {

	}

	public interface IRepaddoprterm3 {

	}

	public interface ITerm3 {

	}

	public interface IRepmultoprfactor {

	}

	public interface IFactor {

	}

	public interface IFactorop {

	}

	public interface ITerm2 {

	}

	public interface ITerm1 {

	}

	public interface IExprList {

	}

	public interface IMonadicOpr {

	}

	public interface ITerm2op {

	}

	public interface ITerm1opor {

	}

	public interface ITerm1opand {

	}

	public interface IEprListop {

	}

	public interface IExprListopop {

	}

	public interface IProgram {

	}

	public interface IProgParamList {

	}

	public interface IProgParamListop {

	}

	public interface IProgParamListopop {

	}

	public interface ICpsDecl {

	}

	public interface ICpsDeclop {

	}

	public interface ICpsCmd {

	}

	public interface ICpsCmdop {

	}

	public interface IProgramop {

	}

	public interface IDecl {

	}

	public interface IFunDecl {

	}

	public interface IFunDeclop1 {

	}

	public interface IFunDeclop2 {

	}

	public interface IProcDecl {

	}

	public interface IStoDecl {

	}

	public interface ITypedIdent {

	}

	public interface IParamList {

	}

	public interface IParamListop {

	}

	public interface IParamListopop {

	}

	public interface IGlobImp {

	}

	public interface IGlobImpop1 {

	}

	public interface IGlobImpop2 {

	}

	public interface IGlobImps {

	}

	public interface IGlobImpsop {

	}

	public interface ICpsStoDecl {

	}

	public interface ICpsStoDeclop {

	}

	public interface IProgParam {

	}

	public interface IParam {

	}

	public interface IParamop {

	}

	public interface ICmd {

	}

	public interface ICmdop {

	}

	public interface IGlobInits {

	}

	public interface IIdents {

	}

	public interface IIdentsop {

	}

	public class Program implements IProgram {
		private Ident ident;
		private IProgParamList progParamList;
		private IProgramop programop;
		private ICpsCmd cpsCmd;

		public Program(Ident ident, IProgParamList progParamList,
				IProgramop programop, ICpsCmd cpsCmd) {
			super();
			this.ident = ident;
			this.progParamList = progParamList;
			this.programop = programop;
			this.cpsCmd = cpsCmd;
		}

	}

	public class ProgParamList implements IProgParamList {
		private IProgParamListop progParamListop;

		public ProgParamList(IProgParamListop progParamListop) {
			this.progParamListop = progParamListop;
		}
	}

	public class ProgParamListop implements IProgParamListop {
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListop(IProgParam progParam,
				IProgParamListopop progParamListopop) {

			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

	}

	public class ProgParamListopopComma implements IProgParamListop {
		private Comma comma;
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListopopComma(Comma comma, IProgParam progParam,
				IProgParamListopop progParamListopop) {
			super();
			this.comma = comma;
			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

	}

	public class CpsDecl implements ICpsDecl {
		private IDecl decl;
		private ICpsDeclop cpsDeclop;

		public CpsDecl(IDecl decl, ICpsDeclop cpsDeclop) {
			super();
			this.decl = decl;
			this.cpsDeclop = cpsDeclop;
		}

	}

	public class CpsDeclopSemicolon implements ICpsDeclop {
		private IToken semicolon;
		private IDecl decl;
		private ICpsDeclop cpsDeclop;

		public CpsDeclopSemicolon(IToken semicolon, IDecl decl,
				ICpsDeclop cpsDeclop) {
			super();
			this.semicolon = semicolon;
			this.decl = decl;
			this.cpsDeclop = cpsDeclop;
		}

	}

	public class CpsDeclopDo implements ICpsDeclop {

	}

	public class CpsCmd implements ICpsCmd {
		private ICmd cmd;
		private ICpsCmdop cpsCmdop;

		public CpsCmd(ICmd cmd, ICpsCmdop cpsCmdop) {
			super();
			this.cmd = cmd;
			this.cpsCmdop = cpsCmdop;
		}

	}

	public class CpsCmdopSemicolon implements ICpsCmdop {
		private IToken semicolon;
		private ICmd cmd;
		private ICpsCmdop cpsCmdop;

		public CpsCmdopSemicolon(IToken semicolon, ICmd cmd, ICpsCmdop cpsCmdop) {
			super();
			this.semicolon = semicolon;
			this.cmd = cmd;
			this.cpsCmdop = cpsCmdop;
		}

	}

	public class CpsCmdop implements ICpsCmdop {

	}

	public class ProgramopGlobal implements IProgramop {
		private IToken global;
		private ICpsDecl cpsDecl;

		public ProgramopGlobal(IToken global, ICpsDecl cpsDecl) {
			super();
			this.global = global;
			this.cpsDecl = cpsDecl;
		}
	}

	public class ProgramopDo implements IProgramop {

	}

	public class DeclSto implements IDecl {
		private IStoDecl stoDecl;

		public DeclSto(IStoDecl stoDecl) {
			super();
			this.stoDecl = stoDecl;
		}

	}

	public class DeclFun implements IDecl {
		private IFunDecl funDecl;

		public DeclFun(IFunDecl funDecl) {
			super();
			this.funDecl = funDecl;
		}

	}

	public class DeclProc implements IDecl {
		private IProcDecl procDecl;

		public DeclProc(IProcDecl procDecl) {
			super();
			this.procDecl = procDecl;
		}

	}

	public class FunDecl implements IFunDecl {
		private IToken fun;
		private IToken Ident;
		private IParamList paramList;
		private IToken returns;
		private IStoDecl stoDecl;
		private IFunDeclop1 funDeclop1;
		private IFunDeclop2 funDeclop2;
		private IToken tokenDo;
		private ICpsCmd cpsCmd;
		private IToken endFun;

		public FunDecl(IToken fun, IToken ident, IParamList paramList,
				IToken returns, IStoDecl stoDecl, IFunDeclop1 funDeclop1,
				IFunDeclop2 funDeclop2, IToken tokenDo, ICpsCmd cpsCmd,
				IToken endFun) {
			super();
			this.fun = fun;
			Ident = ident;
			this.paramList = paramList;
			this.returns = returns;
			this.stoDecl = stoDecl;
			this.funDeclop1 = funDeclop1;
			this.funDeclop2 = funDeclop2;
			this.tokenDo = tokenDo;
			this.cpsCmd = cpsCmd;
			this.endFun = endFun;
		}

	}

	public class FunDeclop1Global implements IFunDeclop1 {
		private IToken global;
		private IGlobImps globImps;

		public FunDeclop1Global(IToken global, IGlobImps globImps) {
			super();
			this.global = global;
			this.globImps = globImps;
		}

	}

	public class FunDeclop1 implements IFunDeclop1 {

	}

	public class FunDeclop2Local implements IFunDeclop2 {
		private IToken local;
		private ICpsStoDecl cpsStoDecl;

		public FunDeclop2Local(IToken local, ICpsStoDecl cpsStoDecl) {
			super();
			this.local = local;
			this.cpsStoDecl = cpsStoDecl;
		}

	}

	public class FunDeclop2 implements IFunDeclop2 {

	}

	public class ProcDecl implements IProcDecl {
		private IToken proc;
		private IToken ident;
		private IParamList paramList;
		private IToken returns;
		private IStoDecl stoDecl;
		private IFunDeclop1 funDeclop1;
		private IFunDeclop2 funDeclop2;
		private IToken tokenDo;
		private ICpsCmd cpsCmd;
		private IToken endProc;

		public ProcDecl(IToken proc, IToken ident, IParamList paramList,
				IToken returns, IStoDecl stoDecl, IFunDeclop1 funDeclop1,
				IFunDeclop2 funDeclop2, IToken tokenDo, ICpsCmd cpsCmd,
				IToken endProc) {
			super();
			this.proc = proc;
			this.ident = ident;
			this.paramList = paramList;
			this.returns = returns;
			this.stoDecl = stoDecl;
			this.funDeclop1 = funDeclop1;
			this.funDeclop2 = funDeclop2;
			this.tokenDo = tokenDo;
			this.cpsCmd = cpsCmd;
			this.endProc = endProc;
		}

	}

	public class StoDeclChangemode implements IStoDecl {
		private IToken Changemode;
		private ITypedIdent typedIdent;

		public StoDeclChangemode(IToken changemode, ITypedIdent typedIdent) {
			super();
			Changemode = changemode;
			this.typedIdent = typedIdent;
		}

	}

	public class StoDeclIdent implements IStoDecl {
		private ITypedIdent typedIdent;

		public StoDeclIdent(ITypedIdent typedIdent) {
			super();
			this.typedIdent = typedIdent;
		}

	}

	public class TypedIdent implements ITypedIdent {
		private IToken ident;
		private IToken colon;
		private IToken type;

		public TypedIdent(IToken ident, IToken colon, IToken type) {
			super();
			this.ident = ident;
			this.colon = colon;
			this.type = type;
		}

	}

	public class ParamList implements IParamList {
		private IToken lParen;
		private IParamListop paramListop;
		private IToken rParen;

		public ParamList(IToken lParen, IParamListop paramListop, IToken rParen) {
			super();
			this.lParen = lParen;
			this.paramListop = paramListop;
			this.rParen = rParen;
		}

	}

	public class ParamListop implements IParamListop {
		private IParam param;
		private IParamListopop paramListopop;

		public ParamListop(IParam param, IParamListopop paramListopop) {
			super();
			this.param = param;
			this.paramListopop = paramListopop;
		}

	}

	public class ParamListopRparen implements IParamListop {

	}

	public class ParamListopopComma implements IParamListopop {
		private IToken comma;
		private IParam param;
		private IParamListopop paramListopop;

		public ParamListopopComma(IToken comma, IParam param,
				IParamListopop paramListopop) {
			super();
			this.comma = comma;
			this.param = param;
			this.paramListopop = paramListopop;
		}

	}

	public class ParamListopopRparen implements IParamListopop {

	}

	public class GlobImp implements IGlobImp {
		private IGlobImpop1 globImpop1;
		private IGlobImpop2 globImpop2;
		private IToken ident;

		public GlobImp(IGlobImpop1 globImpop1, IGlobImpop2 globImpop2,
				IToken ident) {
			super();
			this.globImpop1 = globImpop1;
			this.globImpop2 = globImpop2;
			this.ident = ident;
		}

	}

	public class GlobImpop1Flowmode implements IGlobImpop1 {
		private IToken flowmode;

		public GlobImpop1Flowmode(IToken flowmode) {
			super();
			this.flowmode = flowmode;
		}

	}

	public class GlobImpop1 implements IGlobImpop1 {

	}

	public class GlobImpop2Changemode implements IGlobImpop2 {
		private IToken changemode;

		public GlobImpop2Changemode(IToken changemode) {
			super();
			this.changemode = changemode;
		}

	}

	public class GlobImpop2Ident implements IGlobImpop2 {

	}

	public class GlobImps implements IGlobImps {
		private IGlobImp globImp;
		private IGlobImpsop globImpsop;

		public GlobImps(IGlobImp globImp, IGlobImpsop globImpsop) {
			super();
			this.globImp = globImp;
			this.globImpsop = globImpsop;
		}

	}

	public class GlobImpsop implements IGlobImpsop {
		private IToken comma;
		private IGlobImp globImp;

		public GlobImpsop(IToken comma, IGlobImp globImp) {
			super();
			this.comma = comma;
			this.globImp = globImp;
		}

	}

	public class CpsStoDecl implements ICpsStoDecl {
		private IStoDecl stoDecl;
		private ICpsStoDeclop cpsStoDeclop;

		public CpsStoDecl(IStoDecl stoDecl, ICpsStoDeclop cpsStoDeclop) {
			super();
			this.stoDecl = stoDecl;
			this.cpsStoDeclop = cpsStoDeclop;
		}

	}

	public class CpsStoDeclopSemicolon implements ICpsStoDeclop {
		private IToken semicolon;
		private IStoDecl stoDecl;
		private ICpsStoDeclop cpsStoDeclop;

		public CpsStoDeclopSemicolon(IToken semicolon, IStoDecl stoDecl,
				ICpsStoDeclop cpsStoDeclop) {
			super();
			this.semicolon = semicolon;
			this.stoDecl = stoDecl;
			this.cpsStoDeclop = cpsStoDeclop;
		}

	}

	public class CpsStoDeclopDo implements ICpsStoDeclop {

	}

	public class Param implements IParam {
		private IGlobImpop1 globImpop1;
		private IParamop paramop;
		private IGlobImpop2 globImpop2;
		private ITypedIdent typedIdent;

		public Param(IGlobImpop1 globImpop1, IParamop paramop,
				IGlobImpop2 globImpop2, ITypedIdent typedIdent) {
			super();
			this.globImpop1 = globImpop1;
			this.paramop = paramop;
			this.globImpop2 = globImpop2;
			this.typedIdent = typedIdent;
		}

	}

	public class ParamopMechmode implements IParamop {
		private IToken mechmode;

		public ParamopMechmode(IToken mechmode) {
			super();
			this.mechmode = mechmode;
		}

	}

	public class Paramop implements IParamop {

	}

	public class CmdSkip implements ICmd {
		private IToken skip;

		public CmdSkip(IToken skip) {
			super();
			this.skip = skip;
		}

	}

	public class CmdBecomes implements ICmd {
		private IExpr expr1;
		private IToken becomes;
		private IExpr expr2;

		public CmdBecomes(IExpr expr1, IToken becomes, IExpr expr2) {
			super();
			this.expr1 = expr1;
			this.becomes = becomes;
			this.expr2 = expr2;
		}

	}

	public class CmdIf implements ICmd {
		private IToken tokenIf;
		private IExpr expr;
		private IToken then;
		private ICpsCmd cpsCmd1;
		private IToken tokenElse;
		private ICpsCmd cpsCmd2;
		private IToken endif;

		public CmdIf(IToken tokenIf, IExpr expr, IToken then, ICpsCmd cpsCmd1,
				IToken tokenElse, ICpsCmd cpsCmd2, IToken endif) {
			super();
			this.tokenIf = tokenIf;
			this.expr = expr;
			this.then = then;
			this.cpsCmd1 = cpsCmd1;
			this.tokenElse = tokenElse;
			this.cpsCmd2 = cpsCmd2;
			this.endif = endif;
		}

	}

	public class CmdWhile implements ICmd {
		private IToken tokenWhile;
		private IExpr expr;
		private IToken tokenDo;
		private ICpsCmd cpsCmd;
		private IToken endWhile;

		public CmdWhile(IToken tokenWhile, IExpr expr, IToken tokenDo,
				ICpsCmd cpsCmd, IToken endWhile) {
			super();
			this.tokenWhile = tokenWhile;
			this.expr = expr;
			this.tokenDo = tokenDo;
			this.cpsCmd = cpsCmd;
			this.endWhile = endWhile;
		}

	}

	public class CmdCall implements ICmd {
		private IToken call;
		private IToken ident;
		private IExprList exprList;
		private ICmdop cmdop;

		public CmdCall(IToken call, IToken ident, IExprList exprList,
				ICmdop cmdop) {
			super();
			this.call = call;
			this.ident = ident;
			this.exprList = exprList;
			this.cmdop = cmdop;
		}

	}

	public class CmdDebugin implements ICmd {
		private IToken debugin;

		public CmdDebugin(IToken debugin) {
			super();
			this.debugin = debugin;
		}

	}

	public class CmdDebugout implements ICmd {
		private IToken debugout;

		public CmdDebugout(IToken debugout) {
			super();
			this.debugout = debugout;
		}

	}

	public class CmdopInit implements ICmdop {
		private IGlobInits globInits;

		public CmdopInit(IGlobInits globInits) {
			super();
			this.globInits = globInits;
		}

	}

	public class Cmdop implements ICmdop {

	}

	public class GlobInits implements IGlobInits {
		private IToken init;
		private IIdents idents;

		public GlobInits(IToken init, IIdents idents) {
			super();
			this.init = init;
			this.idents = idents;
		}

	}

	public class Idents implements IIdents {
		private IToken ident;
		private IIdentsop identsop;

		public Idents(IToken ident, IIdentsop identsop) {
			super();
			this.ident = ident;
			this.identsop = identsop;
		}

	}

	public class IdentsopComma implements IIdentsop {
		private IToken comma;
		private IToken ident;
		private IIdentsop identsop;

		public IdentsopComma(IToken comma, IToken ident, IIdentsop identsop) {
			super();
			this.comma = comma;
			this.ident = ident;
			this.identsop = identsop;
		}

	}

	public class Identsop implements IIdentsop {

	}
}
