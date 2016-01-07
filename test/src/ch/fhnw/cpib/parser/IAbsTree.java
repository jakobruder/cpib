package ch.fhnw.cpib.parser;

import java.util.ArrayList;
import java.util.List;

import ch.fhnw.cpib.checker.ContextError;
import ch.fhnw.cpib.checker.Types;
import ch.fhnw.cpib.parser.IConcTree.ITypedIdent;
import ch.fhnw.cpib.parser.IConcTree.TypedIdent;
import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.symbols.ChangeModeToken;
import ch.fhnw.cpib.scanner.symbols.FlowModeToken;
import ch.fhnw.cpib.scanner.symbols.MechModeToken;

public interface IAbsTree {

	public interface IAbsExpr {

		Types check() throws ContextError;

	}

	public interface IAbsCmd {

	}

	public interface IAbsProgParam {

	}

	public interface IAbsDecl {

	}

	public interface IAbsParam {

	}

	public interface IAbsGlobalImp {

	}

	public class GlobalImpList implements IAbsGlobalImp {
		private ArrayList<IAbsGlobalImp> globalImpList;

		public GlobalImpList(ArrayList<IAbsGlobalImp> globalImpList) {
			super();
			this.globalImpList = globalImpList;
		}

	}

	public class GlobalImp implements IAbsGlobalImp {
		private Ident ident;
		private FlowModeToken flowmode;
		private ChangeModeToken changemode;

		public GlobalImp(Ident ident, FlowModeToken flowmode,
				ChangeModeToken changemode) {
			super();
			this.ident = ident;
			this.flowmode = flowmode;
			this.changemode = changemode;
		}

	}

	public class Program {
		private Ident ident;
		private IAbsProgParam progParam;
		private IAbsDecl decl;
		private IAbsCmd cmd;

		public Program(Ident ident, IAbsProgParam progParam, IAbsDecl decl) {
			super();
			this.ident = ident;
			this.progParam = progParam;
			this.decl = decl;
		}
	}

	public class ProgParam implements IAbsProgParam {
		private FlowModeToken flowmode;
		private ChangeModeToken changemode;
		private ITypedIdent typedIdent;

		public ProgParam(FlowModeToken flowmode, ChangeModeToken changemode,
				ITypedIdent typedIdent) {
			super();
			this.flowmode = flowmode;
			this.changemode = changemode;
			this.typedIdent = typedIdent;
		}

	}

	public class ProgParamList implements IAbsProgParam {
		private ArrayList<IAbsProgParam> progParamList;

		public ProgParamList(ArrayList<IAbsProgParam> progParamList) {
			super();
			this.progParamList = progParamList;
		}

	}

	public class StoDecl implements IAbsDecl {
		private ChangeModeToken changemode;
		private TypedIdent typedIdent;

		public StoDecl(ChangeModeToken changemode, TypedIdent typedIdent) {
			super();
			this.changemode = changemode;
			this.typedIdent = typedIdent;
		}

		public StoDecl(TypedIdent typedIdent) {
			super();
			this.typedIdent = typedIdent;
		}

	}

	public class FunDecl implements IAbsDecl {
		private Ident ident;
		private IAbsParam param;
		private IAbsDecl stoDecl;
		private IAbsGlobalImp globImp;
		private IAbsDecl stoDeclLocal;
		private IAbsCmd cmd;

		public FunDecl(Ident ident, IAbsParam param, IAbsDecl stoDecl,
				IAbsGlobalImp globImp, IAbsDecl stoDeclLocal, IAbsCmd cmd) {
			super();
			this.ident = ident;
			this.param = param;
			this.stoDecl = stoDecl;
			this.globImp = globImp;
			this.stoDeclLocal = stoDeclLocal;
			this.cmd = cmd;
		}

	}

	public class ProcDecl implements IAbsDecl {
		private Ident ident;
		private IAbsParam param;
		private IAbsDecl stoDecl;
		private IAbsGlobalImp globImp;
		private IAbsDecl stoDeclLocal;
		private IAbsCmd cmd;

		public ProcDecl(Ident ident, IAbsParam param, IAbsDecl stoDecl,
				IAbsGlobalImp globImp, IAbsDecl stoDeclLocal, IAbsCmd cmd) {
			super();
			this.ident = ident;
			this.param = param;
			this.stoDecl = stoDecl;
			this.globImp = globImp;
			this.stoDeclLocal = stoDeclLocal;
			this.cmd = cmd;
		}

	}

	public class CpsStoDecl implements IAbsDecl {
		private ArrayList<IAbsDecl> stoDecls;

		public CpsStoDecl(ArrayList<IAbsDecl> stoDecls) {
			super();
			this.stoDecls = stoDecls;
		}

	}

	public class CpsDecl implements IAbsDecl {
		private ArrayList<IAbsDecl> declList;

		public CpsDecl(ArrayList<IAbsDecl> declList) {
			super();
			this.declList = declList;
		}

	}

	public class Param implements IAbsParam {
		private FlowModeToken flowmode;
		private MechModeToken mechmode;
		private ChangeModeToken changemode;
		private TypedIdent typedIdent;

		public Param(FlowModeToken flowmode, MechModeToken mechmode,
				ChangeModeToken changemode, TypedIdent typedIdent) {
			super();
			this.flowmode = flowmode;
			this.mechmode = mechmode;
			this.changemode = changemode;
			this.typedIdent = typedIdent;
		}

	}

	public class ParamList implements IAbsParam {
		private ArrayList<IAbsParam> params;

		public ParamList(ArrayList<IAbsParam> params) {
			super();
			this.params = params;
		}

	}

	public class LiteralExpr implements IAbsExpr {
		private Literal literal;

		public LiteralExpr(Literal literal) {
			super();
			this.literal = literal;
		}

		@Override
		public Types check() throws ContextError {
			return Types.LITERAL;
		}

	}

	public class StoreExpr implements IAbsExpr {
		private Ident ident;
		private boolean isInitialization;

		public StoreExpr(Ident ident, boolean isInitialization) {
			super();
			this.ident = ident;
			this.isInitialization = isInitialization;
		}

		@Override
		public Types check() throws ContextError {
			return Types.IDENT;
		}
	}

	public class FunCallExpr implements IAbsExpr {

		private Ident identM;
		private ArrayList<IAbsExpr> expressions;

		public FunCallExpr(Ident identM, ArrayList<IAbsExpr> expressions) {
			super();
			this.identM = identM;
			this.expressions = expressions;
		}

		@Override
		public Types check() throws ContextError {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class MonadicExpr implements IAbsExpr {

		private Operators operator;
		private IAbsExpr expression;

		public MonadicExpr(Operators operator, IAbsExpr expression) {
			super();
			this.operator = operator;
			this.expression = expression;
		}

		@Override
		public Types check() throws ContextError {
			Types type = expression.check();
			switch (operator) {
			case NOTOPR:
				if (type == Types.BOOL) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case MINUS:
				if (type == Types.INTEGER) {
					return Types.INTEGER;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			default:
				throw new RuntimeException();
			}
		}
	}

	public class DyadicExpr implements IAbsExpr {
		private Operators operator;
		private IAbsExpr expression1;
		private IAbsExpr expression2;

		public DyadicExpr(Operators operator, IAbsExpr expression1,
				IAbsExpr expression2) {
			super();
			this.operator = operator;
			this.expression1 = expression1;
			this.expression2 = expression2;
		}

		public Types check() throws ContextError {
			Types type1 = expression1.check();
			Types type2 = expression2.check();

			switch (operator) {
			case PLUS:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.INTEGER;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case MINUS:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.INTEGER;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case TIMES:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.INTEGER;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case DIV_E:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.INTEGER;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case MOD_E:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.INTEGER;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case CAND:
				if (type1 == Types.BOOL && type2 == Types.BOOL) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case COR:
				if (type1 == Types.BOOL && type2 == Types.BOOL) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case LT:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case LE:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case EQ:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.BOOL;
				}
				if (type1 == Types.BOOL && type2 == Types.BOOL) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case NE:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.BOOL;
				}
				if (type1 == Types.BOOL && type2 == Types.BOOL) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case GE:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case GT:
				if (type1 == Types.INTEGER && type2 == Types.INTEGER) {
					return Types.BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			default:
				throw new RuntimeException();
			}
		}
	}

	public class SkipCmd implements IAbsCmd {

	}

	public class AssiCmd implements IAbsCmd {
		private IAbsExpr expr1;
		private IAbsExpr expr2;

		public AssiCmd(IAbsExpr expr1, IAbsExpr expr2) {
			super();
			this.expr1 = expr1;
			this.expr2 = expr2;
		}

	}

	public class CpsCmd implements IAbsCmd {
		private ArrayList<IAbsCmd> cmdList;

		public CpsCmd(ArrayList<IAbsCmd> cmdList) {
			super();
			this.cmdList = cmdList;
		}

	}

	public class CondCmd implements IAbsCmd {
		private IAbsExpr expr;
		private IAbsCmd cmd1;
		private IAbsCmd cmd2;

		public CondCmd(IAbsExpr expr, IAbsCmd cmd1, IAbsCmd cmd2) {
			super();
			this.expr = expr;
			this.cmd1 = cmd1;
			this.cmd2 = cmd2;
		}

	}

	public class WhileCmd implements IAbsCmd {
		private IAbsExpr expr;
		private IAbsCmd cmd;

		public WhileCmd(IAbsExpr expr, IAbsCmd cmd) {
			super();
			this.expr = expr;
			this.cmd = cmd;
		}

	}

	public class ProcCallCmd implements IAbsCmd {
		private Ident ident;
		private ArrayList<IAbsExpr> exprListRoutine;
		private ArrayList<Ident> identList;

		public ProcCallCmd(Ident ident, ArrayList<IAbsExpr> exprListRoutine,
				ArrayList<Ident> identList) {
			super();
			this.ident = ident;
			this.exprListRoutine = exprListRoutine;
			this.identList = identList;
		}

	}

	public class InputCmd implements IAbsCmd {
		private IAbsExpr expr;

		public InputCmd(IAbsExpr expr) {
			super();
			this.expr = expr;
		}

	}

	public class OutputCmd implements IAbsCmd {
		private IAbsExpr expr;

		public OutputCmd(IAbsExpr expr) {
			super();
			this.expr = expr;
		}

	}
}
