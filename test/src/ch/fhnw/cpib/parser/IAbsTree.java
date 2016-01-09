package ch.fhnw.cpib.parser;

import java.util.ArrayList;
import java.util.HashMap;

import ch.fhnw.cpib.checker.ContextError;
import ch.fhnw.cpib.checker.Types;
import ch.fhnw.cpib.parser.IAbsTree.Context.IdentState;
import ch.fhnw.cpib.parser.IConcTree.ITypedIdent;
import ch.fhnw.cpib.parser.IConcTree.TypedIdent;
import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.ChangeMode;
import ch.fhnw.cpib.scanner.enums.FlowMode;
import ch.fhnw.cpib.scanner.enums.MechMode;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.symbols.ChangeModeToken;
import ch.fhnw.cpib.scanner.symbols.FlowModeToken;
import ch.fhnw.cpib.scanner.symbols.MechModeToken;
import ch.fhnw.cpib.virtualMachine.CodeArray;
import ch.fhnw.cpib.virtualMachine.ICodeArray;
import ch.fhnw.cpib.virtualMachine.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.virtualMachine.IInstructions;
import ch.fhnw.cpib.virtualMachine.IInstructions.IInstr;
import ch.fhnw.cpib.virtualMachine.VirtualMachine;

public interface IAbsTree {

	public static final String GLOBAL_IDENT = "GLOBAL";
	public HashMap<String, Context> contexts = new HashMap<>();
	public HashMap<String, Integer> methods = new HashMap<>();
	public HashMap<String, Integer> variables = new HashMap<>();

	public class Context {
		String name;

		public Context(String name) {
			super();
			this.name = name;
		}

		int variableCounter = 0;
		int frameSize = 0;
		int paramCounter = 0;

		String returnIdent;
		boolean addReturnIdent = false;

		public class IdentState {

			boolean initialised;
			Types type;
			boolean directAccess;
			ChangeMode changemode;
			FlowMode flowmode;

			public IdentState(boolean initialised, boolean directAccess,
					ChangeMode constant, FlowMode flowmode) {
				super();
				this.initialised = initialised;
				this.directAccess = directAccess;
				this.changemode = changemode;
				this.flowmode = flowmode;
			}

		}

		HashMap<String, IdentState> idents = new HashMap<>();
		ArrayList<IdentState> inputParams = new ArrayList<>();

		boolean isStoreOk(String ident, boolean isInit) {
			IdentState out = idents.get(ident);
			return !(out == null || (!(out.initialised && isInit)));
		}

		Types getTypeForIdent(String ident) {
			IdentState out = idents.get(ident);
			if (out != null) {
				return out.type;
			}
			return null;
		}

		boolean initIdent(String ident) {
			IdentState out = idents.get(ident);
			if (out != null) {
				out.initialised = true;
				return true;
			}
			return false;
		}

<<<<<<< HEAD
		boolean addIdent(String ident, boolean directAccess, ChangeMode changemode, FlowMode flowmode,
				boolean isInputParam) {
			IdentState identState = new IdentState(false, directAccess, changemode, flowmode);
=======
		boolean addIdent(String ident, boolean directAccess,
				ChangeMode changemode, FlowMode flowmode, boolean isInputParam) {
			IdentState identState = new IdentState(false, directAccess,
					changemode, flowmode);
>>>>>>> bbcfa8bdcb6769a0988455e4fa24a741ccb6e4e8
			if (isInputParam) {
				inputParams.add(identState);
			}
			if (addReturnIdent) {
				addReturnIdent = false;
				returnIdent = ident;
			}
			return (idents.put(ident, identState) == null);

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

		boolean hasIdent(String ident) {
			IdentState out = idents.get(ident);
			if (out != null) {
				return true;
			}
			return false;
		}

	}

	public interface IAbsExpr {

		Types check(String ident) throws ContextError;

		public void generateCode(ArrayList<IInstructions.IInstr> codeArray,
				Context context, boolean isSave);

		boolean isLValue();

		boolean isRValue();

	}

	public interface IAbsCmd {

		void check(String ident) throws ContextError;

		public void generateCode(ArrayList<IInstructions.IInstr> codeArray,
				Context context);

	}

	public interface IAbsProgParam {
		void check() throws ContextError;

		public void generateCode(ArrayList<IInstructions.IInstr> codeArray,
				Context context);
	}

	public interface IAbsDecl {
		void check(String ident) throws ContextError;

		public void generateCode(ArrayList<IInstructions.IInstr> codeArray,
				Context context);
	}

	public interface IAbsParam {
		void check(String ident) throws ContextError;

		public void generateCode(ArrayList<IInstructions.IInstr> codeArray,
				Context context);
	}

	public interface IAbsGlobalImp {

		public boolean addToContext(String ident);

		public void generateCode(ArrayList<IInstructions.IInstr> codeArray,
				Context context);

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

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

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
			if (contexts.get(GLOBAL_IDENT).hasIdent(ident)) {
				Context currentContext = contexts.get(ident);
<<<<<<< HEAD
				return currentContext.addIdent(this.ident.getIdent(), true, changemode.getChangeMode(),
						flowmode.getFlowMode(), false);
=======
				return currentContext.addIdent(this.ident.getIdent(), true,
						changemode.getChangeMode(), flowmode.getFlowMode(),
						false);
>>>>>>> bbcfa8bdcb6769a0988455e4fa24a741ccb6e4e8
			}
			return false;
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

		}
	}

	public class Program {
		private Ident ident;
		private IAbsProgParam progParam;
		private IAbsDecl decl;
		private IAbsCmd cmd;

		public Program(Ident ident, IAbsProgParam progParam, IAbsDecl decl,
				IAbsCmd cmd) {
			super();
			this.ident = ident;
			this.progParam = progParam;
			this.decl = decl;
			this.cmd = cmd;
		}

		public void check() throws ContextError {
			contexts.put(GLOBAL_IDENT, new Context(GLOBAL_IDENT));
			progParam.check();
			decl.check(GLOBAL_IDENT);
			cmd.check(GLOBAL_IDENT);
		}

		public ICodeArray generateCode() throws CodeTooSmallError {
			ArrayList<IInstructions.IInstr> codeArray = new ArrayList<>();
			Context context = contexts.get(GLOBAL_IDENT);
			progParam.generateCode(codeArray, context);
			decl.generateCode(codeArray, context);
			cmd.generateCode(codeArray, context);
			CodeArray codeArrayOut = new CodeArray(codeArray.size());
			for (int i = 0; i < codeArray.size(); i++) {
				codeArrayOut.put(i, codeArray.get(i));
			}

			return codeArrayOut;
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
			Context context = contexts.get(GLOBAL_IDENT);
			String identName = typedIdent.getTypedIdent().getIdent().getIdent();
			if (context.addIdent(identName, true, changemode.getChangeMode(),
					flowmode.getFlowMode(), true)) {
				Types type;
				Terminals typeTerminal = typedIdent.getTypedIdent().getType()
						.getTerminal();
				if (typeTerminal == Terminals.BOOL) {
					type = Types.COND_BOOL;
				} else {
					type = Types.INTEGER;
				}
				context.setTypeForIdent(identName, type);
			} else {
				throw new ContextError("Error at progParam check");
			}
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			IInstructions.IInstr instruction = new IInstructions.LoadImInt(0);
			codeArray.add(instruction);
			variables.put(typedIdent.getTypedIdent().getIdent().getIdent(),
					context.variableCounter++);

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
			for (IAbsProgParam progParam : progParamList) {
				progParam.check();
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			for (IAbsProgParam progParam : progParamList) {
				progParam.generateCode(codeArray, context);
			}

		}

	}

	public class StoDecl implements IAbsDecl {
		private ChangeModeToken changemode;
		private FlowModeToken flowmode = new FlowModeToken(FlowMode.IN);
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
		public void check(String ident) throws ContextError {
			Context context = contexts.get(ident);
			String identName = typedIdent.getTypedIdent().getIdent().getIdent();
			if (context.addIdent(identName, true, changemode.getChangeMode(),
					flowmode.getFlowMode(), false)) {
				Types type;
				Terminals typeTerminal = typedIdent.getTypedIdent().getType()
						.getTerminal();
				if (typeTerminal == Terminals.BOOL) {
					type = Types.COND_BOOL;
				} else {
					type = Types.INTEGER;
				}
				context.setTypeForIdent(identName, type);
			} else {
				throw new ContextError("Error at progParam check");
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			IInstructions.IInstr instruction = new IInstructions.LoadImInt(0);
			codeArray.add(instruction);
			variables.put(typedIdent.getTypedIdent().getIdent().getIdent(),
					context.variableCounter++);

		}

	}

	public class FunDecl implements IAbsDecl {
		private Ident ident;
		private IAbsParam param;
		private IAbsDecl stoDecl;
		private IAbsGlobalImp globImp;
		private IAbsDecl stoDeclLocal;
		private IAbsCmd cmd;

		public FunDecl(Ident ident, IAbsParam param, IAbsDecl stoDecl, IAbsGlobalImp globImp, IAbsDecl stoDeclLocal,
				IAbsCmd cmd) {
			super();
			this.ident = ident;
			this.param = param;
			this.stoDecl = stoDecl;
			this.globImp = globImp;
			this.stoDeclLocal = stoDeclLocal;
			this.cmd = cmd;
		}

		@Override
		public void check(String ident) throws ContextError {
			contexts.put(this.ident.getIdent(),
					new Context(this.ident.getIdent()));
			param.check(this.ident.getIdent());
			contexts.get(this.ident.getIdent()).addReturnIdent = true;
			stoDecl.check(this.ident.getIdent());
			globImp.addToContext(this.ident.getIdent());
			stoDeclLocal.check(this.ident.getIdent());
			cmd.check(this.ident.getIdent());

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			Context funContext = contexts.get(ident.getIdent());
			funContext.variableCounter = context.variableCounter;
			funContext.frameSize = context.variableCounter;
			param.generateCode(codeArray, funContext);
			stoDecl.generateCode(codeArray, funContext);
			globImp.generateCode(codeArray, funContext);
			stoDeclLocal.generateCode(codeArray, funContext);
			int jumpPointer = codeArray.size();
			codeArray.add(new IInstructions.UncondJump(0));
			methods.put(ident.getIdent(), jumpPointer + 2);
			cmd.generateCode(codeArray, funContext);
			codeArray.set(jumpPointer,
					new IInstructions.UncondJump(codeArray.size()));

			context.variableCounter = funContext.variableCounter;

		}
	}

	public class ProcDecl implements IAbsDecl {
		private Ident ident;
		private IAbsParam param;
		private IAbsDecl stoDecl;
		private IAbsGlobalImp globImp;
		private IAbsDecl stoDeclLocal;
		private IAbsCmd cmd;

		public ProcDecl(Ident ident, IAbsParam param, IAbsDecl stoDecl, IAbsGlobalImp globImp, IAbsDecl stoDeclLocal,
				IAbsCmd cmd) {
			super();
			this.ident = ident;
			this.param = param;
			this.stoDecl = stoDecl;
			this.globImp = globImp;
			this.stoDeclLocal = stoDeclLocal;
			this.cmd = cmd;
		}

		@Override
		public void check(String ident) throws ContextError {
			contexts.put(this.ident.getIdent(),
					new Context(this.ident.getIdent()));
			param.check(this.ident.getIdent());
			stoDecl.check(this.ident.getIdent());
			globImp.addToContext(this.ident.getIdent());
			stoDeclLocal.check(this.ident.getIdent());
			cmd.check(this.ident.getIdent());

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			Context procContext = contexts.get(ident.getIdent());
			procContext.variableCounter = context.variableCounter;
			procContext.frameSize = context.variableCounter;
			param.generateCode(codeArray, procContext);
			stoDecl.generateCode(codeArray, procContext);
			globImp.generateCode(codeArray, procContext);
			stoDeclLocal.generateCode(codeArray, procContext);
			int jumpPointer = codeArray.size();
			codeArray.add(new IInstructions.UncondJump(0));
			methods.put(ident.getIdent(), jumpPointer + 2);
			cmd.generateCode(codeArray, procContext);
			codeArray.set(jumpPointer,
					new IInstructions.UncondJump(codeArray.size()));
			context.variableCounter = procContext.variableCounter;

		}
	}

	public class CpsStoDecl implements IAbsDecl {
		private ArrayList<IAbsDecl> stoDecls;

		public CpsStoDecl(ArrayList<IAbsDecl> stoDecls) {
			super();
			this.stoDecls = stoDecls;
		}

		@Override
		public void check(String ident) throws ContextError {
			for (IAbsDecl decl : stoDecls) {
				decl.check(ident);
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			for (IAbsDecl decl : stoDecls) {
				decl.generateCode(codeArray, context);
			}

		}

	}

	public class CpsDecl implements IAbsDecl {
		private ArrayList<IAbsDecl> declList;

		public CpsDecl(ArrayList<IAbsDecl> declList) {
			super();
			this.declList = declList;
		}

		@Override
		public void check(String ident) throws ContextError {
			for (IAbsDecl decl : declList) {
				decl.check(ident);
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			for (IAbsDecl decl : declList) {
				decl.generateCode(codeArray, context);
			}

		}

	}

	public class Param implements IAbsParam {
		private FlowModeToken flowmode;
		private MechModeToken mechmode;
		private ChangeModeToken changemode;
		private TypedIdent typedIdent;

		public Param(FlowModeToken flowmode, MechModeToken mechmode, ChangeModeToken changemode,
				TypedIdent typedIdent) {
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
		public void check(String ident) throws ContextError {
			Context context = contexts.get(ident);
			String identName = typedIdent.getIdent().getIdent();
			boolean directAccess = false;
			Types type;
			if (typedIdent.getType().getTerminal() == Terminals.BOOL) {
				type = Types.COND_BOOL;
			} else {
				type = Types.INTEGER;
			}
			if (mechmode.getMechMode() == MechMode.COPY) {
				directAccess = true;
			}
			if (context != null) {
				context.addIdent(identName, directAccess,
						changemode.getChangeMode(), flowmode.getFlowMode(),
						true);
				context.setTypeForIdent(identName, type);
			} else {
				throw new ContextError("Error at param");
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			IInstructions.IInstr instruction = new IInstructions.LoadImInt(0);
			codeArray.add(instruction);
			variables.put(typedIdent.getTypedIdent().getIdent().getIdent(),
					context.variableCounter);
			variables.put(context.name + context.paramCounter++,
					context.variableCounter++);

		}
	}

	public class ParamList implements IAbsParam {
		private ArrayList<IAbsParam> params;

		public ParamList(ArrayList<IAbsParam> params) {
			super();
			this.params = params;
		}

		@Override
		public void check(String ident) throws ContextError {
			for (IAbsParam param : params) {
				param.check(ident);
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			for (IAbsParam param : params) {
				param.generateCode(codeArray, context);
			}

		}

	}

	public class LiteralExpr implements IAbsExpr {
		private Literal literal;

		public LiteralExpr(Literal literal) {
			super();
			this.literal = literal;
		}

		@Override
		public Types check(String ident) throws ContextError {
			return Types.LITERAL;
		}

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isRValue() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context,
				boolean isSave) {
			IInstructions.IInstr instruction = new IInstructions.LoadImInt(
					literal.getValue());
			codeArray.add(instruction);

		}

	}

	public class StoreExpr implements IAbsExpr {
		private Ident ident;
		private boolean isInitialization;
		private boolean isConstInit = false;

		public StoreExpr(Ident ident, boolean isInitialization) {
			super();
			this.ident = ident;
			this.isInitialization = isInitialization;
		}

		@Override
		public Types check(String ident) throws ContextError {
			Context context = contexts.get(ident);

			if (context.isStoreOk(this.ident.getIdent(), isInitialization)) {
				IdentState state = context.idents.get(this.ident.getIdent());
				if (state != null && state.changemode == ChangeMode.CONST) {
					isConstInit = true;
				}
				if (isInitialization) {
					context.initIdent(this.ident.getIdent());
				}
				return context.getTypeForIdent(this.ident.getIdent());
			}
			throw new ContextError("Param not allowed");
		}

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return !isConstInit;
		}

		@Override
		public boolean isRValue() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context,
				boolean isSave) {
			if (isSave) {
				IInstructions.IInstr instructionAdd = new IInstructions.LoadImInt(
						variables.get(ident.getIdent()));
				codeArray.set(codeArray.size() - 2, instructionAdd);
				IInstructions.IInstr store = new IInstructions.Store();
				codeArray.add(store);

			} else {
				IInstructions.IInstr instruction = new IInstructions.LoadImInt(
						variables.get(ident.getIdent()));
				codeArray.add(instruction);
				instruction = new IInstructions.Deref();
				codeArray.add(instruction);
			}

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
		public Types check(String ident) throws ContextError {
			Context context = contexts.get(identM.getIdent());
			if (context != null) {
				if (expressions.size() != context.inputParams.size()) {
					throw new ContextError("Wrong number of input params");
				}
				for (int i = 0; i < context.inputParams.size(); i++) {
					IdentState state = context.inputParams.get(i);
					IAbsExpr expr = expressions.get(i);
					if (state.type != expr.check(ident)) {
						throw new ContextError("Param is not the right type");
					}
					if (state.flowmode == FlowMode.IN && !expr.isRValue()) {
						throw new ContextError(
								"Input param is not a right hand expression");
					}
					if (state.flowmode == FlowMode.INOUT
							&& !(expr.isRValue() && expr.isLValue())) {
						throw new ContextError(
								"Inout param is not right and left hand expression");
					}
					if (state.flowmode == FlowMode.OUT && !expr.isLValue()) {
						throw new ContextError(
								"Out param is not Left hand expression");
					}

				}
				return context.getTypeForIdent(context.returnIdent);
			} else {
				throw new ContextError("Function does not exist");
			}
		}

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isRValue() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context,
				boolean isSave) {
			IInstructions.IInstr call = new IInstructions.Call(
					methods.get(identM.getIdent()));
			codeArray.add(call);

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
		public Types check(String ident) throws ContextError {
			Types type = expression.check(ident);
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
		public boolean isRValue() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context,
				boolean isSave) {
			expression.generateCode(codeArray, context, isSave);
			if (operator == Operators.NOTOPR) {
				IInstructions.IInstr instruction = new IInstructions.CondJump(
						codeArray.size() + 3);
				codeArray.add(instruction);
				instruction = new IInstructions.LoadImInt(1);
				codeArray.add(instruction);
				instruction = new IInstructions.UncondJump(codeArray.size() + 1);
				codeArray.add(instruction);
				instruction = new IInstructions.LoadImInt(0);
				codeArray.add(instruction);
			} else {
				IInstructions.IInstr instruction = new IInstructions.NegInt();
				codeArray.add(instruction);
			}

		}
	}

	public class DyadicExpr implements IAbsExpr {
		private Operators operator;
		private IAbsExpr expression1;
		private IAbsExpr expression2;

		public IAbsExpr getExpression1() {
			return expression1;
		}

		public IAbsExpr getExpression2() {
			return expression2;
		}

		public DyadicExpr(Operators operator, IAbsExpr expression1, IAbsExpr expression2) {
			super();
			this.operator = operator;
			this.expression1 = expression1;
			this.expression2 = expression2;
		}

		public Types check(String ident) throws ContextError {
			Types type1 = expression1.check(ident);
			Types type2 = expression2.check(ident);

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
						// TODO:
						if (type1 == Types.INTEGER && type2 == Types.INTEGER)
							;
						else if (type1 == Types.INTEGER
								&& (type2 == Types.LESSEQUAL_BOOL || type2 == Types.EQUAL_BOOL)) {
							expression1 = leftIntCANDDyadicExpr(operator, expression1, expression2);
							operator = Operators.CAND;
						} else if ((type1 == Types.LESSEQUAL_BOOL || type1 == Types.EQUAL_BOOL)
								&& type2 == Types.INTEGER) {
							expression2 = rightIntCANDDyadicExpr(operator, expression1, expression2);
							operator = Operators.CAND;
						}
						return Types.LESSEQUAL_BOOL;
					}
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case EQ:
				if (type1 == Types.EQUAL_BOOL || type1 == Types.INTEGER) {
					if (type2 == Types.EQUAL_BOOL || type2 == Types.INTEGER) {
						// TODO:
						return Types.EQUAL_BOOL;
					} else if (type2 == Types.LESSEQUAL_BOOL) {
						// TODO:
						return Types.LESSEQUAL_BOOL;
					} else if (type2 == Types.GREATEREQUAL_BOOL) {
						// TODO:
						return Types.GREATEREQUAL_BOOL;
					} else if (type2 == Types.NOT_EQUAL_BOOL) {
						// TODO:
						return Types.NOT_EQUAL_BOOL;
					}
				} else if (type1 == Types.LESSEQUAL_BOOL) {
					if (type2 == Types.LESSEQUAL_BOOL || type2 == Types.INTEGER || type2 == Types.EQUAL_BOOL) {
						// TODO:
						return Types.LESSEQUAL_BOOL;
					}
				} else if (type1 == Types.GREATEREQUAL_BOOL) {
					if (type2 == Types.GREATEREQUAL_BOOL || type2 == Types.INTEGER || type2 == Types.EQUAL_BOOL) {
						// TODO:
						return Types.GREATEREQUAL_BOOL;
					}
				} else if (type1 == Types.NOT_EQUAL_BOOL) {
					if (type2 == Types.EQUAL_BOOL || type2 == Types.NOT_EQUAL_BOOL || type2 == Types.INTEGER) {
						// TODO:
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
						// TODO:
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
						// TODO:
						return Types.GREATEREQUAL_BOOL;
					}
				}
				throw new ContextError("Type error in operator " + operator + ".");
			case GT:
				if (type1 == Types.INTEGER || type1 == Types.GREATEREQUAL_BOOL || type1 == Types.EQUAL_BOOL) {
					if (type2 == Types.INTEGER || type2 == Types.GREATEREQUAL_BOOL || type2 == Types.EQUAL_BOOL) {
						// TODO:
						return Types.GREATEREQUAL_BOOL;
					}
				}
				throw new ContextError("Type error in operator " + operator + ".");
			default:
				throw new RuntimeException();
			}
		}

		private IAbsExpr leftIntCANDDyadicExpr(Operators op, IAbsExpr ex1, IAbsExpr ex2) {
			return new DyadicExpr(op, ex1, ((DyadicExpr)ex2).getExpression1());
		}
		
		private IAbsExpr rightIntCANDDyadicExpr(Operators op, IAbsExpr ex1, IAbsExpr ex2) {
			return new DyadicExpr(op, ex1, ((DyadicExpr)ex2).getExpression1());
		}

		@Override
		public boolean isLValue() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isRValue() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context,
				boolean isSave) {
			switch (operator) {
			case PLUS:
				
			case MINUS:
				
			case TIMES:
				
			case DIV_E:
				
			case MOD_E:
				
			case CAND:
				
			case COR:
				
			case LT:
				
			case LE:
				
			case EQ:
				
			case NE:
				
			case GE:
				
			case GT:
				
			default:
				

		}
	}

	public class SkipCmd implements IAbsCmd {

		@Override
		public void check(String ident) throws ContextError {
			// TODO Auto-generated method stub

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

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
		public void check(String ident) throws ContextError {
			if (!expr1.isLValue()) {
				throw new ContextError("The expression on the left side is not a left hand expression.");
			}
			if (!expr2.isRValue()) {
				throw new ContextError("The expression on the right is not a right hand expression.");
			}
			if (expr1.check(ident) != expr2.check(ident)) {
				throw new ContextError("Expressions are not the same type");
			}
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

		}

	}

	public class CpsCmd implements IAbsCmd {
		private ArrayList<IAbsCmd> cmdList;

		public CpsCmd(ArrayList<IAbsCmd> cmdList) {
			super();
			this.cmdList = cmdList;
		}

		@Override
		public void check(String ident) throws ContextError {
			for (IAbsCmd cmd : cmdList) {
				cmd.check(ident);
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

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
		public void check(String ident) throws ContextError {
			if (expr.check(ident) != Types.COND_BOOL) {
				throw new ContextError("Expression is not boolean");
			}
			if (!expr.isRValue()) {
				throw new ContextError("Expression is not a right hand expression");
			}
			cmd1.check(ident);
			cmd2.check(ident);

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

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
		public void check(String ident) throws ContextError {
			if (expr.check(ident) != Types.COND_BOOL) {
				throw new ContextError("Expression is not boolean");
			}
			if (expr.isLValue()) {
				throw new ContextError("Expression is not a right hand expression");
			}
			cmd.check(ident);
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

		}

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
		public void check(String ident) throws ContextError {
			Context context = contexts.get(this.ident.getIdent());
			if (context != null) {
				if (exprListRoutine.size() != context.inputParams.size()) {
					throw new ContextError("Wrong number of input params");
				}
				for (int i = 0; i < context.inputParams.size(); i++) {
					IdentState state = context.inputParams.get(i);
					IAbsExpr expr = exprListRoutine.get(i);
					if (state.type != expr.check(ident)) {
						throw new ContextError("Param is not the right type");
					}
					if (state.flowmode == FlowMode.IN && !expr.isRValue()) {
						throw new ContextError("Input param is not a right hand expression");
					}
					if (state.flowmode == FlowMode.INOUT && !(expr.isRValue() && expr.isLValue())) {
						throw new ContextError("Inout param is not right and left hand expression");
					}
					if (state.flowmode == FlowMode.OUT && !expr.isLValue()) {
						throw new ContextError("Out param is not Left hand expression");
					}

				}
			} else {
				throw new ContextError("Function does not exist");
			}

		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

		}

	}

	public class InputCmd implements IAbsCmd {
		private IAbsExpr expr;

		public InputCmd(IAbsExpr expr) {
			super();
			this.expr = expr;
		}

		@Override
		public void check(String ident) throws ContextError {
			expr.check(ident);
			if (!expr.isLValue()) {
				throw new ContextError("Expression is not a left hand expression");
			}
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

		}

	}

	public class OutputCmd implements IAbsCmd {
		private IAbsExpr expr;

		public OutputCmd(IAbsExpr expr) {
			super();
			this.expr = expr;
		}

		@Override
		public void check(String ident) throws ContextError {
			expr.check(ident);
			if (!expr.isRValue()) {
				throw new ContextError("Expression is not a right hand expression");
			}
		}

		@Override
		public void generateCode(ArrayList<IInstr> codeArray, Context context) {
			// TODO Auto-generated method stub

		}

	}

}
