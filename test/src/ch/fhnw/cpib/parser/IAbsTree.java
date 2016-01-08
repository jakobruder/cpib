package ch.fhnw.cpib.parser;

import java.util.ArrayList;
import java.util.HashMap;

import ch.fhnw.cpib.checker.ContextError;
import ch.fhnw.cpib.checker.Types;
import ch.fhnw.cpib.parser.IConcTree.ITypedIdent;
import ch.fhnw.cpib.parser.IConcTree.TypedIdent;
import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.ChangeMode;
import ch.fhnw.cpib.scanner.enums.FlowMode;
import ch.fhnw.cpib.scanner.enums.MechMode;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.symbols.ChangeModeToken;
import ch.fhnw.cpib.scanner.symbols.FlowModeToken;
import ch.fhnw.cpib.scanner.symbols.MechModeToken;
import ch.fhnw.cpib.virtualMachine.VirtualMachine;

public interface IAbsTree {

	public HashMap<String, Context> contexts = new HashMap<>();

	public class Context {
		private class IdentState {
			boolean initialised;
			Types type;
			boolean directAccess;

			public IdentState(boolean initialised, boolean directAccess) {
				super();
				this.initialised = initialised;
				this.directAccess = directAccess;
			}

		}

		private HashMap<String, IdentState> idents = new HashMap<>();

		boolean isStoreOk(String ident, boolean isInit, Types type) {
			IdentState out = idents.get(ident);
			return !(out == null || (!(out.initialised && isInit)) || type != out.type);
		}

		boolean addIdent(String ident, boolean directAccess) {
			return (idents.put(ident, new IdentState(false, directAccess)) == null);
		}

		boolean setTypeForIdent(String ident, Types type) {
			IdentState state = idents.get(ident);
			if (state != null) {
				state.type = type;
				return true;
			} else {
				return false;
			}
		}
	}

	public interface IAbsExpr {

		Types check() throws ContextError;

		public int generateCode(int loc, VirtualMachine vm, Context context);
		
		boolean isLValue();

	}

	public interface IAbsCmd {

		void check() throws ContextError;

		public int generateCode(int loc, VirtualMachine vm, Context context);

	}

	public interface IAbsProgParam {
		void check() throws ContextError;
	}

	public interface IAbsDecl {
		void check() throws ContextError;
	}

	public interface IAbsParam {
		void check() throws ContextError;
	}

	public interface IAbsGlobalImp {

		public boolean addToContext(String ident);

	}

	public class GlobalImpList implements IAbsGlobalImp {
		private ArrayList<IAbsGlobalImp> globalImpList;

		public GlobalImpList(ArrayList<IAbsGlobalImp> globalImpList) {
			super();
			this.globalImpList = globalImpList;
		}

		public boolean addToContext(String ident) {
			for (IAbsGlobalImp globalImp : globalImpList) {
				if (!globalImp.addToContext(ident)) {
					return false;
				}
			}
			return true;
		}
	}

	public class GlobalImp implements IAbsGlobalImp {
		private Ident ident;
		private FlowModeToken flowmode;
		private ChangeModeToken changemode;

		public GlobalImp(Ident ident, FlowModeToken flowmode, ChangeModeToken changemode) {
			super();
			this.ident = ident;
			if (flowmode == null) {
				this.flowmode = new FlowModeToken(FlowMode.IN);
			} else {
				this.flowmode = flowmode;
			}
			if (changemode == null) {
				this.changemode = new ChangeModeToken(ChangeMode.CONST);
			} else {
				this.changemode = changemode;
			}
		}

		@Override
		public boolean addToContext(String ident) {
			Context currentContext = contexts.get(ident);
			return currentContext.addIdent(this.ident.getIdent(), true);
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

		public void check() throws ContextError {
			progParam.check();
			decl.check();
			cmd.check();
		}
	}

	public class ProgParam implements IAbsProgParam {
		private FlowModeToken flowmode;
		private ChangeModeToken changemode;
		private ITypedIdent typedIdent;

		public ProgParam(FlowModeToken flowmode, ChangeModeToken changemode, ITypedIdent typedIdent) {
			super();
			if (flowmode == null) {
				this.flowmode = new FlowModeToken(FlowMode.IN);
			} else {
				this.flowmode = flowmode;
			}
			if (changemode == null) {
				this.changemode = new ChangeModeToken(ChangeMode.CONST);
			} else {
				this.changemode = changemode;
			}
			this.typedIdent = typedIdent;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class ProgParamList implements IAbsProgParam {
		private ArrayList<IAbsProgParam> progParamList;

		public ProgParamList(ArrayList<IAbsProgParam> progParamList) {
			super();
			this.progParamList = progParamList;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class StoDecl implements IAbsDecl {
		private ChangeModeToken changemode;
		private TypedIdent typedIdent;

		public StoDecl(ChangeModeToken changemode, TypedIdent typedIdent) {
			super();
			if (changemode == null) {
				this.changemode = new ChangeModeToken(ChangeMode.CONST);
			} else {
				this.changemode = changemode;
			}
			this.typedIdent = typedIdent;
		}

		public StoDecl(TypedIdent typedIdent) {
			super();
			this.typedIdent = typedIdent;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class FunDecl implements IAbsDecl {
		private Ident ident;
		private IAbsParam param;
		private IAbsDecl stoDecl;
		private IAbsGlobalImp globImp;
		private IAbsDecl stoDeclLocal;
		private IAbsCmd cmd;

		public FunDecl(Ident ident, IAbsParam param, IAbsDecl stoDecl, IAbsGlobalImp globImp, IAbsDecl stoDeclLocal, IAbsCmd cmd) {
			super();
			this.ident = ident;
			this.param = param;
			this.stoDecl = stoDecl;
			this.globImp = globImp;
			this.stoDeclLocal = stoDeclLocal;
			this.cmd = cmd;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class ProcDecl implements IAbsDecl {
		private Ident ident;
		private IAbsParam param;
		private IAbsDecl stoDecl;
		private IAbsGlobalImp globImp;
		private IAbsDecl stoDeclLocal;
		private IAbsCmd cmd;

		public ProcDecl(Ident ident, IAbsParam param, IAbsDecl stoDecl, IAbsGlobalImp globImp, IAbsDecl stoDeclLocal, IAbsCmd cmd) {
			super();
			this.ident = ident;
			this.param = param;
			this.stoDecl = stoDecl;
			this.globImp = globImp;
			this.stoDeclLocal = stoDeclLocal;
			this.cmd = cmd;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class CpsStoDecl implements IAbsDecl {
		private ArrayList<IAbsDecl> stoDecls;

		public CpsStoDecl(ArrayList<IAbsDecl> stoDecls) {
			super();
			this.stoDecls = stoDecls;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class CpsDecl implements IAbsDecl {
		private ArrayList<IAbsDecl> declList;

		public CpsDecl(ArrayList<IAbsDecl> declList) {
			super();
			this.declList = declList;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class Param implements IAbsParam {
		private FlowModeToken flowmode;
		private MechModeToken mechmode;
		private ChangeModeToken changemode;
		private TypedIdent typedIdent;

		public Param(FlowModeToken flowmode, MechModeToken mechmode, ChangeModeToken changemode, TypedIdent typedIdent) {
			super();
			if (flowmode == null) {
				this.flowmode = new FlowModeToken(FlowMode.IN);
			} else {
				this.flowmode = flowmode;
			}
			if (mechmode == null) {
				this.mechmode = new MechModeToken(MechMode.COPY);
			} else {
				this.mechmode = mechmode;
			}
			if (changemode == null) {
				this.changemode = new ChangeModeToken(ChangeMode.CONST);
			} else {
				this.changemode = changemode;
			}
			this.typedIdent = typedIdent;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

	}

	public class ParamList implements IAbsParam {
		private ArrayList<IAbsParam> params;

		public ParamList(ArrayList<IAbsParam> params) {
			super();
			this.params = params;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

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

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
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

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
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

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
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
				if (type == Types.COND_BOOL) {
					return Types.COND_BOOL;
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

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	public class DyadicExpr implements IAbsExpr {
		private Operators operator;
		private IAbsExpr expression1;
		private IAbsExpr expression2;

		public DyadicExpr(Operators operator, IAbsExpr expression1, IAbsExpr expression2) {
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
				if (type1 == Types.COND_BOOL && type2 == Types.COND_BOOL) {
					return Types.COND_BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case COR:
				if (type1 == Types.COND_BOOL && type2 == Types.COND_BOOL) {
					return Types.COND_BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case LT:
				if (type1 == Types.INTEGER || type1 == Types.LESSEQUAL_BOOL || type1 == Types.EQUAL_BOOL) {
					if (type2 == Types.INTEGER || type2 == Types.LESSEQUAL_BOOL || type2 == Types.EQUAL_BOOL) {
						return Types.LESSEQUAL_BOOL;
					}
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case LE:
				if (type1 == Types.INTEGER || type1 == Types.LESSEQUAL_BOOL || type1 == Types.EQUAL_BOOL) {
					if (type2 == Types.INTEGER || type2 == Types.LESSEQUAL_BOOL || type2 == Types.EQUAL_BOOL) {
						return Types.LESSEQUAL_BOOL;
					}
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case EQ:
				if (type1 == Types.EQUAL_BOOL || type1 == Types.INTEGER) {
					if (type2 == Types.EQUAL_BOOL || type2 == Types.INTEGER) {
						return Types.EQUAL_BOOL;
					} else if (type2 == Types.LESSEQUAL_BOOL) {
						return Types.LESSEQUAL_BOOL;
					} else if (type2 == Types.GREATEREQUAL_BOOL) {
						return Types.GREATEREQUAL_BOOL;
					} else if (type2 == Types.NOT_EQUAL_BOOL) {
						return Types.NOT_EQUAL_BOOL;
					}
				} else if (type1 == Types.LESSEQUAL_BOOL) {
					if (type2 == Types.LESSEQUAL_BOOL || type2 == Types.INTEGER || type2 == Types.EQUAL_BOOL) {
						return Types.LESSEQUAL_BOOL;
					}
				} else if (type1 == Types.GREATEREQUAL_BOOL) {
					if (type2 == Types.GREATEREQUAL_BOOL || type2 == Types.INTEGER || type2 == Types.EQUAL_BOOL) {
						return Types.GREATEREQUAL_BOOL;
					}
				} else if (type1 == Types.NOT_EQUAL_BOOL) {
					if (type2 == Types.EQUAL_BOOL || type2 == Types.NOT_EQUAL_BOOL || type2 == Types.INTEGER) {
						return Types.NOT_EQUAL_BOOL;
					}
				}

				if (type1 == Types.COND_BOOL && type2 == Types.COND_BOOL) {
					return Types.COND_BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case NE:
				if (type1 == Types.INTEGER || type1 == Types.EQUAL_BOOL) {
					if (type2 == Types.INTEGER || type2 == Types.EQUAL_BOOL) {
						return Types.NOT_EQUAL_BOOL;
					}
				}
				if (type1 == Types.COND_BOOL && type2 == Types.COND_BOOL) {
					return Types.COND_BOOL;
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case GE:
				if (type1 == Types.INTEGER || type1 == Types.GREATEREQUAL_BOOL || type1 == Types.EQUAL_BOOL) {
					if (type2 == Types.INTEGER || type2 == Types.GREATEREQUAL_BOOL || type2 == Types.EQUAL_BOOL) {
						return Types.GREATEREQUAL_BOOL;
					}
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case GT:
				if (type1 == Types.INTEGER || type1 == Types.GREATEREQUAL_BOOL || type1 == Types.EQUAL_BOOL) {
					if (type2 == Types.INTEGER || type2 == Types.GREATEREQUAL_BOOL || type2 == Types.EQUAL_BOOL) {
						return Types.GREATEREQUAL_BOOL;
					}
				}
				throw new ContextError("Type error in operator " + operator + ".");
			default:
				throw new RuntimeException();
			}
		}

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	public class SkipCmd implements IAbsCmd {
		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			return loc;
		}
	}

	public class AssiCmd implements IAbsCmd {
		private IAbsExpr expr1;
		private IAbsExpr expr2;

		public AssiCmd(IAbsExpr expr1, IAbsExpr expr2) {
			super();
			this.expr1 = expr1;
			this.expr2 = expr2;
		}

		@Override
		public void check() throws ContextError {
			if (!expr1.isLValue()) {
				throw new ContextError("The expression on the left side is not a left hand expression.");
			}
			if (expr2.isLValue()) {
				throw new ContextError("The expression on the right is not a right hand expression.");
			}
			if (expr1.check() != expr2.check()) {
				throw new ContextError("Expressions are not the same type");
			}
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public class CpsCmd implements IAbsCmd {
		private ArrayList<IAbsCmd> cmdList;

		public CpsCmd(ArrayList<IAbsCmd> cmdList) {
			super();
			this.cmdList = cmdList;
		}

		@Override
		public void check() throws ContextError {
			for (IAbsCmd cmd : cmdList) {
				cmd.check();
			}

		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
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

		@Override
		public void check() throws ContextError {
			if (expr.check() != Types.COND_BOOL) {
				throw new ContextError("Expression is not boolean");
			}
			if (expr.isLValue()) {
				throw new ContextError("Expression is not a right hand expression");
			}
			cmd1.check();
			cmd2.check();

		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
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

		@Override
		public void check() throws ContextError {
			if (expr.check() != Types.COND_BOOL) {
				throw new ContextError("Expression is not boolean");
			}
			if (expr.isLValue()) {
				throw new ContextError("Expression is not a right hand expression");
			}
			cmd.check();
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			//TODO:
			return 0;		}

	}

	public class ProcCallCmd implements IAbsCmd {
		private Ident ident;
		private ArrayList<IAbsExpr> exprListRoutine;
		private ArrayList<Ident> identList;

		public ProcCallCmd(Ident ident, ArrayList<IAbsExpr> exprListRoutine, ArrayList<Ident> identList) {
			super();
			this.ident = ident;
			this.exprListRoutine = exprListRoutine;
			this.identList = identList;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub

		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public class InputCmd implements IAbsCmd {
		private IAbsExpr expr;

		public InputCmd(IAbsExpr expr) {
			super();
			this.expr = expr;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

	public class OutputCmd implements IAbsCmd {
		private IAbsExpr expr;

		public OutputCmd(IAbsExpr expr) {
			super();
			this.expr = expr;
		}

		@Override
		public void check() throws ContextError {
			// TODO Auto-generated method stub
		}

		@Override
		public int generateCode(int loc, VirtualMachine vm, Context context) {
			// TODO Auto-generated method stub
			return 0;
		}

	}

}
