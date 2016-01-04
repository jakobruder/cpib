package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;

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
		
	}
	
	public class MonadicExpr implements IAbsExpr {
		
	}
	
	public class DyadicExpr implements IAbsExpr {
		
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
