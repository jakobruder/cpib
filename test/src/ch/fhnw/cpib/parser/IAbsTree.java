package ch.fhnw.cpib.parser;

<<<<<<< HEAD
import java.util.List;
=======
import java.util.ArrayList;
>>>>>>> a5926690d91ec06b2c1f061ba6eeda39db98af59

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.Operators;

public interface IAbsTree {

	public interface IAbsExpr {

	}

	public interface IAbsCmd {

	}

	public class LiteralExpr implements IAbsExpr {
		private Literal literal;

		public LiteralExpr(Literal literal) {
			super();
			this.literal = literal;
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
	}

	public class FunCallExpr implements IAbsExpr {
<<<<<<< HEAD
		private Ident identM;
		private List<IAbsExpr> expressions;
		public FunCallExpr(Ident identM, List<IAbsExpr> expressions) {
			super();
			this.identM = identM;
			this.expressions = expressions;
		}
=======

>>>>>>> a5926690d91ec06b2c1f061ba6eeda39db98af59
	}

	public class MonadicExpr implements IAbsExpr {
<<<<<<< HEAD
		private Operators operator;
		private IAbsExpr expression;
		public MonadicExpr(Operators operator, IAbsExpr expression) {
			super();
			this.operator = operator;
			this.expression = expression;
		}
=======

>>>>>>> a5926690d91ec06b2c1f061ba6eeda39db98af59
	}

	public class DyadicExpr implements IAbsExpr {
<<<<<<< HEAD
		private Operators operator;
		private IAbsExpr expression1;
		private IAbsExpr expression2;

		public DyadicExpr(Operators operator, IAbsExpr expression1, IAbsExpr expression2) {
			super();
			this.operator = operator;
			this.expression1 = expression1;
			this.expression2 = expression2;
		}
=======

>>>>>>> a5926690d91ec06b2c1f061ba6eeda39db98af59
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

		public WhileCmd(IAbsExpr expr) {
			super();
			this.expr = expr;
		}

	}

	public class ProcCallCmd implements IAbsCmd {
		private Ident ident;
		private Ident identRoutine;
		private ArrayList<IAbsExpr> exprListRoutine;
		private ArrayList<Ident> identList;

		public ProcCallCmd(Ident ident, Ident identRoutine,
				ArrayList<IAbsExpr> exprListRoutine, ArrayList<Ident> identList) {
			super();
			this.ident = ident;
			this.identRoutine = identRoutine;
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
