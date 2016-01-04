package ch.fhnw.cpib.parser;

import java.util.List;

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
		private Ident identM;
		private List<IAbsExpr> expressions;
		public FunCallExpr(Ident identM, List<IAbsExpr> expressions) {
			super();
			this.identM = identM;
			this.expressions = expressions;
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
	}
	
	public class SkipCmd implements IAbsCmd {
		
	}
	
	public class AssiCmd implements IAbsCmd {
		
	}
	
	public class CpsCmd implements IAbsCmd {
		
	}
	
	public class CondCmd implements IAbsCmd {
		
	}
	
	public class WhileCmd implements IAbsCmd {
		
	}
	
	public class ProcCallCmd implements IAbsCmd {
		
	}
	
	public class InputCmd implements IAbsCmd {
		
	}
	
	public class OutputCmd implements IAbsCmd {
		
	}
}
