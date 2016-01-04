package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.scanner.Literal;

public interface IAbsTree {

	public interface IExpr {
		
	}
	
	public interface ICmd {
		
	}
	
	public class LiteralExpr implements IExpr {
		private Literal literal;

		public LiteralExpr(Literal literal) {
			super();
			this.literal = literal;
		}
		
		
	}
	
	public class StoreExpr implements IExpr {
		
	}
	
	public class FunCallExpr implements IExpr {
		
	}
	
	public class MonadicExpr implements IExpr {
		
	}
	
	public class DyadicExpr implements IExpr {
		
	}
	
	public class SkipCmd implements ICmd {
		
	}
	
	public class AssiCmd implements ICmd {
		
	}
	
	public class CpsCmd implements ICmd {
		
	}
	
	public class CondCmd implements ICmd {
		
	}
	
	public class WhileCmd implements ICmd {
		
	}
	
	public class ProcCallCmd implements ICmd {
		
	}
	
	public class InputCmd implements ICmd {
		
	}
	
	public class OutputCmd implements ICmd {
		
	}
}
