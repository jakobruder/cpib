package ch.fhnw.cpib.parser;

import java.util.ArrayList;

import ch.fhnw.cpib.parser.IAbsTree.DyadicExpr;
import ch.fhnw.cpib.parser.IAbsTree.IAbsCmd;
import ch.fhnw.cpib.parser.IAbsTree.IAbsExpr;
import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.Literal;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.AddOpr;
import ch.fhnw.cpib.scanner.symbols.BoolAnd;
import ch.fhnw.cpib.scanner.symbols.BoolOr;
import ch.fhnw.cpib.scanner.symbols.Comma;
import ch.fhnw.cpib.scanner.symbols.MultOpr;
import ch.fhnw.cpib.scanner.symbols.RelOpr;

public interface IConcTree {

	public interface IConcExpr {

		public IAbsExpr toAbs();

	}

	public interface IExprbool {

		public IAbsExpr toAbs(IAbsExpr expr);

	}

	public interface IRepaddoprterm3 {

		public IAbsExpr toAbs(IAbsExpr expr);
	}

	public interface ITerm3 {
		public IAbsExpr toAbs();
	}

	public interface IRepmultoprfactor {

		public IAbsExpr toAbs(IAbsExpr expr);
	}

	public interface IFactor {
		public IAbsExpr toAbs();

	}

	public interface IFactorop {

		public IAbsExpr toAbs(Ident ident);
	}

	public interface ITerm2 {
		public IAbsExpr toAbs();

	}

	public interface ITerm1 {
		public IAbsExpr toAbs();

	}

	public interface IExprList {
		public ArrayList<IAbsExpr> toAbs();

	}

	public interface IMonadicOpr {
		public Operators getOperator();
	}

	public interface ITerm2op {

		public IAbsExpr toAbs(IAbsExpr expr);
	}

	public interface ITerm1opor {

		public IAbsExpr toAbs(IAbsExpr expr);
	}

	public interface ITerm1opand {

		public IAbsExpr toAbs(IAbsExpr expr);
	}

	public interface IExprListop {

		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList);
	}

	public interface IExprListopop {
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList);
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
		public IAbsCmd toAbs(IAbsCmd cmd);
	}

	public interface ICpsCmdop {
		public IAbsCmd toAbs(IAbsCmd cmd);
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

	public interface IConcCmd {
		public IAbsCmd toAbs();
	}

	public interface ICmdop {
		public IAbsCmd toAbs(IAbsCmd cmd);
	}

	public interface IGlobInits {
		public IAbsCmd toAbs(IAbsCmd cmd);
	}

	public interface IIdents {
		public IAbsCmd toAbs(IAbsCmd cmd);
	}

	public interface IIdentsop {
		public IAbsCmd toAbs(IAbsCmd cmd);
	}

	public class Expr implements IConcExpr {

		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

		public IAbsExpr toAbs(IAbsExpr e) {
			// TODO Auto-generated method stub
			return e;
		}

	}

	public class ExprLParen implements IConcExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprLParen(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IAbsExpr toAbs() {
			return exprbool.toAbs(term1.toAbs());

		}
	}

	public class ExprAddopr implements IConcExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprAddopr(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IAbsExpr toAbs() {
			return exprbool.toAbs(term1.toAbs());
		}

	}

	public class ExprNot implements IConcExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprNot(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IAbsExpr toAbs() {
			return exprbool.toAbs(term1.toAbs());
		}

	}

	public class ExprIdent implements IConcExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprIdent(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IAbsExpr toAbs() {
			return exprbool.toAbs(term1.toAbs());
		}

	}

	public class ExprBoolOr implements IConcExpr {
		private BoolOr or;
		private ITerm1 term1;
		private ITerm1opor term1opor;

		public ExprBoolOr(BoolOr or, ITerm1 term1, ITerm1opor term1opor) {
			super();
			this.or = or;
			this.term1 = term1;
			this.term1opor = term1opor;
		}

		@Override
		public IAbsExpr toAbs() {
			return term1opor.toAbs(term1.toAbs());
		}

	}

	public class ExprBoolAnd implements IConcExpr {
		private BoolAnd and;
		private ITerm1 term1;
		private ITerm1opand term1opand;

		public ExprBoolAnd(BoolAnd and, ITerm1 term1, ITerm1opand term1opand) {
			super();
			this.and = and;
			this.term1 = term1;
			this.term1opand = term1opand;
		}

		@Override
		public IAbsExpr toAbs() {
			return term1opand.toAbs(term1.toAbs());
		}

	}

	public class Exprbool implements IExprbool {

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return expr;
		}

	}

	public class ExprboolBoolor implements IExprbool {
		private BoolOr or;
		private ITerm1 term1;
		private ITerm1opor term1opor;

		public ExprboolBoolor(BoolOr or, ITerm1 term1, ITerm1opor term1opor) {
			super();
			this.or = or;
			this.term1 = term1;
			this.term1opor = term1opor;
		}

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			return term1opor.toAbs(new IAbsTree.DyadicExpr(Operators.COR, expr,
					term1.toAbs()));
		}

	}

	public class ExprboolBoolAnd implements IExprbool {
		private BoolAnd and;
		private ITerm1 term1;
		private ITerm1opand term1opand;

		public ExprboolBoolAnd(BoolAnd and, ITerm1 term1, ITerm1opand term1opand) {
			super();
			this.and = and;
			this.term1 = term1;
			this.term1opand = term1opand;
		}

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			return term1opand.toAbs(new IAbsTree.DyadicExpr(Operators.CAND,
					expr, term1.toAbs()));
		}

	}

	public class RepAddoprTerm3 implements IRepaddoprterm3 {

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return expr;
		}

	}

	public class RepAddoprTerm3AddOpr implements IRepaddoprterm3 {
		private AddOpr addopr;
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public RepAddoprTerm3AddOpr(AddOpr addopr, ITerm3 term3,
				IRepaddoprterm3 repaddoprterm3) {
			super();
			this.addopr = addopr;
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			return repaddoprterm3.toAbs(new IAbsTree.DyadicExpr(addopr
					.getOperator(), expr, term3.toAbs()));
		}

	}

	public class Term3LParen implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3LParen(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IAbsExpr toAbs() {
			return repmultoprfactor.toAbs(factor.toAbs());
		}

	}

	public class Term3Addopr implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3Addopr(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return repmultoprfactor.toAbs(factor.toAbs());
		}

	}

	public class Term3Not implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3Not(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return repmultoprfactor.toAbs(factor.toAbs());
		}

	}

	public class Term3Ident implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3Ident(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return repmultoprfactor.toAbs(factor.toAbs());
		}

	}

	public class RepMultoprFactor implements IRepmultoprfactor {

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return expr;
		}
	}

	public class RepMultoprFactorMultOpr implements IRepmultoprfactor {
		private MultOpr multopr;
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public RepMultoprFactorMultOpr(MultOpr multopr, IFactor factor,
				IRepmultoprfactor repmultoprfactor) {
			super();
			this.multopr = multopr;
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return repmultoprfactor.toAbs(new DyadicExpr(multopr.getOperator(),
					expr, factor.toAbs()));
		}

	}

	public class FactorLiteral implements IFactor {
		private Literal literal;

		public FactorLiteral(Literal literal) {
			super();
			this.literal = literal;
		}

		@Override
		public IAbsExpr toAbs() {
			return new IAbsTree.LiteralExpr(literal);
		}

	}

	public class FactorLParen implements IFactor {
		private IToken lparen;
		private IConcExpr expr;
		private IToken rparen;

		public FactorLParen(IToken lparen, IConcExpr expr, IToken rparen) {
			super();
			this.lparen = lparen;
			this.expr = expr;
			this.rparen = rparen;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return expr.toAbs();
		}

	}

	public class FactorAddopr implements IFactor {
		private IMonadicOpr monadicOpr;
		private IFactor factor;

		public FactorAddopr(IMonadicOpr monadicOpr, IFactor factor) {
			super();
			this.monadicOpr = monadicOpr;
			this.factor = factor;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return new IAbsTree.MonadicExpr(monadicOpr.getOperator(),
					factor.toAbs());
		}

	}

	public class FactorNot implements IFactor {
		private IMonadicOpr monadicOpr;
		private IFactor factor;

		public FactorNot(IMonadicOpr monadicOpr, IFactor factor) {
			super();
			this.monadicOpr = monadicOpr;
			this.factor = factor;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return new IAbsTree.MonadicExpr(monadicOpr.getOperator(),
					factor.toAbs());
		}

	}

	public class FactorIdent implements IFactor {
		private Ident ident;
		private IFactorop factorop;

		public FactorIdent(Ident ident, IFactorop factorop) {
			super();
			this.ident = ident;
			this.factorop = factorop;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return factorop.toAbs(ident);
		}

	}

	public class FactorOp implements IFactorop {

		@Override
		public IAbsExpr toAbs(Ident ident) {
			// TODO Auto-generated method stub
			return new IAbsTree.StoreExpr(ident, false);
		}

	}

	public class FactorOpLParen implements IFactorop {
		private IExprList exprList;

		public FactorOpLParen(IExprList exprList) {
			super();
			this.exprList = exprList;
		}

		@Override
		public IAbsExpr toAbs(Ident ident) {
			// TODO Auto-generated method stub
			return new IAbsTree.FunCallExpr(ident, exprList.toAbs());
		}
	}

	public class FactorOpInit implements IFactorop {
		private IToken init;

		public FactorOpInit(IToken init) {
			super();
			this.init = init;
		}

		@Override
		public IAbsExpr toAbs(Ident ident) {
			// TODO Auto-generated method stub
			return new IAbsTree.StoreExpr(ident, true);
		}

	}

	public class Term2LParen implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2LParen(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return repaddoprterm3.toAbs(term3.toAbs());
		}

	}

	public class Term2Addopr implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2Addopr(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return repaddoprterm3.toAbs(term3.toAbs());
		}

	}

	public class Term2Not implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2Not(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return repaddoprterm3.toAbs(term3.toAbs());
		}

	}

	public class Term2Ident implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2Ident(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return repaddoprterm3.toAbs(term3.toAbs());
		}

	}

	public class Term2op implements ITerm2op {

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return expr;
		}

	}

	public class Term2opRelOpr implements ITerm2op {
		private RelOpr relopr;
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term2opRelOpr(RelOpr relopr, ITerm2 term2, ITerm2op term2Op) {
			super();
			this.relopr = relopr;
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return term2Op.toAbs(new IAbsTree.DyadicExpr(relopr.getRelOpr(),
					expr, term2.toAbs()));
		}

	}

	public class Term1LParen implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1LParen(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return term2Op.toAbs(term2.toAbs());
		}

	}

	public class Term1Addopr implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1Addopr(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return term2Op.toAbs(term2.toAbs());
		}

	}

	public class Term1Not implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1Not(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return term2Op.toAbs(term2.toAbs());
		}

	}

	public class Term1Ident implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1Ident(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IAbsExpr toAbs() {
			// TODO Auto-generated method stub
			return term2Op.toAbs(term2.toAbs());
		}

	}

	public class Term1OpOr implements ITerm1opor {

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return expr;
		}

	}

	public class Term1OpOrBoolOr implements ITerm1opor {
		private BoolOr or;
		private ITerm1 term1;
		private ITerm1opor term1OpOr;

		public Term1OpOrBoolOr(BoolOr or, ITerm1 term1, ITerm1opor term1OpOr) {
			super();
			this.or = or;
			this.term1 = term1;
			this.term1OpOr = term1OpOr;
		}

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return term1OpOr.toAbs(new IAbsTree.DyadicExpr(or.getOperator(),
					expr, term1.toAbs()));
		}

	}

	public class Term1OpAnd implements ITerm1opand {

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return expr;
		}

	}

	public class Term1OpAndBoolAnd implements ITerm1opand {
		private BoolAnd and;
		private ITerm1 term1;
		private ITerm1opand term1OpAnd;

		public Term1OpAndBoolAnd(BoolAnd and, ITerm1 term1,
				ITerm1opand term1OpAnd) {
			super();
			this.and = and;
			this.term1 = term1;
			this.term1OpAnd = term1OpAnd;
		}

		@Override
		public IAbsExpr toAbs(IAbsExpr expr) {
			// TODO Auto-generated method stub
			return term1OpAnd.toAbs(new IAbsTree.DyadicExpr(and.getOperator(),
					expr, term1.toAbs()));
		}

	}

	public class ExprListLParen implements IExprList {
		private IToken lparen;
		private IExprListop exprListOp;
		private IToken rparen;

		public ExprListLParen(IToken lparen, IExprListop exprListOp,
				IToken rparen) {
			super();
			this.exprListOp = exprListOp;
			this.lparen = lparen;
			this.rparen = rparen;
		}

		@Override
		public ArrayList<IAbsExpr> toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class MonadicOprAddOpr implements IMonadicOpr {
		private AddOpr add;

		public MonadicOprAddOpr(AddOpr add) {
			super();
			this.add = add;
		}

		@Override
		public Operators getOperator() {
			return add.getOperator();
		}

	}

	public class MonadicOprNot implements IMonadicOpr {
		private IToken not;

		public MonadicOprNot(IToken not) {
			super();
			this.not = not;
		}

		@Override
		public Operators getOperator() {
			// TODO Auto-generated method stub
			return Operators.NOTOPR;
		}

	}

	public class ExprListOp implements IExprListop {

		@Override
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList) {
			// TODO Auto-generated method stub
			return exprList;
		}

	}

	public class ExprListOpLParen implements IExprListop {
		private IConcExpr expr;
		private IExprListopop exprListopop;

		public ExprListOpLParen(IConcExpr expr, IExprListopop exprListopop) {
			super();
			this.expr = expr;
			this.exprListopop = exprListopop;
		}

		@Override
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList) {
			exprList.add(expr.toAbs());
			exprListopop.toAbs(exprList);
			return exprList;
		}

	}

	public class ExprListOpAddOpr implements IExprListop {
		private IConcExpr expr;
		private IExprListopop exprListopop;

		public ExprListOpAddOpr(IConcExpr expr, IExprListopop exprListopop) {
			super();
			this.expr = expr;
			this.exprListopop = exprListopop;
		}

		@Override
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList) {
			exprList.add(expr.toAbs());
			exprListopop.toAbs(exprList);
			return exprList;
		}

	}

	public class ExprListOpNot implements IExprListop {
		private IConcExpr expr;
		private IExprListopop exprListopop;

		public ExprListOpNot(IConcExpr expr, IExprListopop exprListopop) {
			super();
			this.expr = expr;
			this.exprListopop = exprListopop;
		}

		@Override
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList) {
			exprList.add(expr.toAbs());
			exprListopop.toAbs(exprList);
			return exprList;
		}

	}

	public class ExprListOpIdent implements IExprListop {
		private IConcExpr expr;
		private IExprListopop exprListopop;

		public ExprListOpIdent(IConcExpr expr, IExprListopop exprListopop) {
			super();
			this.expr = expr;
			this.exprListopop = exprListopop;
		}

		@Override
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList) {
			exprList.add(expr.toAbs());
			exprListopop.toAbs(exprList);
			return exprList;
		}

	}

	public class ExprListOpOp implements IExprListopop {

		@Override
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList) {
			// TODO Auto-generated method stub
			return exprList;
		}

	}

	public class ExprListOpOpComma implements IExprListopop {
		private Comma comma;
		private IConcExpr expr;
		private IExprListopop exprListOpOp;

		public ExprListOpOpComma(Comma comma, IConcExpr expr,
				IExprListopop exprListOpOp) {
			super();
			this.comma = comma;
			this.expr = expr;
			this.exprListOpOp = exprListOpOp;
		}

		@Override
		public ArrayList<IAbsExpr> toAbs(ArrayList<IAbsExpr> exprList) {
			exprList.add(expr.toAbs());
			exprListOpOp.toAbs(exprList);
			return exprList;
		}

	}

	// TODO: IIdents???
	public class Program implements IProgram {
		private Ident ident;
		private IProgParamList progParamList;
		private IProgramop programop;
		private ICpsCmd cpsCmd;
		private IToken program, dotoken, endprogram;

		public Program(IToken program, Ident ident,
				IProgParamList progParamList, IProgramop programop,
				IToken dotoken, ICpsCmd cpsCmd, IToken endprogram) {
			super();
			this.ident = ident;
			this.progParamList = progParamList;
			this.programop = programop;
			this.cpsCmd = cpsCmd;
			this.program = program;
			this.dotoken = dotoken;
			this.endprogram = endprogram;
		}

	}

	public class ProgParamList implements IProgParamList {
		private IToken lparen;
		private IProgParamListop progParamListop;
		private IToken rparen;

		public ProgParamList(IToken lparen, IProgParamListop progParamListop,
				IToken rparen) {
			this.progParamListop = progParamListop;
			this.lparen = lparen;
			this.rparen = rparen;
		}

	}

	public class ProgParamListop implements IProgParamListop {

		public ProgParamListop() {

		}

	}

	public class ProgParamListopIdent implements IProgParamListop {
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListopIdent(IProgParam progParam,
				IProgParamListopop progParamListopop) {
			super();
			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

	}

	public class ProgParamListopChangemode implements IProgParamListop {
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListopChangemode(IProgParam progParam,
				IProgParamListopop progParamListopop) {
			super();
			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

	}

	public class ProgParamListopFlowmode implements IProgParamListop {
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListopFlowmode(IProgParam progParam,
				IProgParamListopop progParamListopop) {
			super();
			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

	}

	public class ProgParamListopop implements IProgParamListopop {

	}

	public class ProgParamListopopComma implements IProgParamListopop {
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

	public class ProgParamIdent implements IProgParam {
		IGlobImpop1 globImpop1;
		IGlobImpop2 globImpop2;
		ITypedIdent typedIdent;

		public ProgParamIdent(IGlobImpop1 globImpop1, IGlobImpop2 globImpop2,
				ITypedIdent typedIdent) {
			super();
			this.globImpop1 = globImpop1;
			this.globImpop2 = globImpop2;
			this.typedIdent = typedIdent;
		}

	}

	public class ProgParamChangemode implements IProgParam {
		IGlobImpop1 globImpop1;
		IGlobImpop2 globImpop2;
		ITypedIdent typedIdent;

		public ProgParamChangemode(IGlobImpop1 globImpop1,
				IGlobImpop2 globImpop2, ITypedIdent typedIdent) {
			super();
			this.globImpop1 = globImpop1;
			this.globImpop2 = globImpop2;
			this.typedIdent = typedIdent;
		}

	}

	public class ProgParamFlowmode implements IProgParam {
		IGlobImpop1 globImpop1;
		IGlobImpop2 globImpop2;
		ITypedIdent typedIdent;

		public ProgParamFlowmode(IGlobImpop1 globImpop1,
				IGlobImpop2 globImpop2, ITypedIdent typedIdent) {
			super();
			this.globImpop1 = globImpop1;
			this.globImpop2 = globImpop2;
			this.typedIdent = typedIdent;
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

	// TODO: Nothing?
	public class CpsDeclopDo implements ICpsDeclop {

		// empty
	}

	public class CpsCmd implements ICpsCmd {
		private IConcCmd cmd;
		private ICpsCmdop cpsCmdop;

		public CpsCmd(IConcCmd cmd, ICpsCmdop cpsCmdop) {
			super();
			this.cmd = cmd;
			this.cpsCmdop = cpsCmdop;
		}

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CpsCmdopSemicolon implements ICpsCmdop {
		private IToken semicolon;
		private IConcCmd cmd;
		private ICpsCmdop cpsCmdop;

		public CpsCmdopSemicolon(IToken semicolon, IConcCmd cmd,
				ICpsCmdop cpsCmdop) {
			super();
			this.semicolon = semicolon;
			this.cmd = cmd;
			this.cpsCmdop = cpsCmdop;
		}

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class CpsCmdop implements ICpsCmdop {

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
		}

		// empty
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

	// TODO:
	public class ProgramopDo implements IProgramop {

		// Empty
	}

	public class DeclIdent implements IDecl {
		private IStoDecl stoDecl;

		public DeclIdent(IStoDecl stoDecl) {
			super();
			this.stoDecl = stoDecl;
		}

	}

	public class DeclChangemode implements IDecl {
		private IStoDecl stoDecl;

		public DeclChangemode(IStoDecl stoDecl) {
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

	// TODO:
	public class FunDeclop1 implements IFunDeclop1 {

		// empty?
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

	// TODO:
	public class FunDeclop2 implements IFunDeclop2 {

		// empty?
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

	// TODO:
	public class ParamListopRparen implements IParamListop {

		// empty?
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

	// TODO:
	public class ParamListopopRparen implements IParamListopop {

		// empty?
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

	// TODO:
	public class GlobImpop1 implements IGlobImpop1 {

		// empty?
	}

	public class GlobImpop2Changemode implements IGlobImpop2 {
		private IToken changemode;

		public GlobImpop2Changemode(IToken changemode) {
			super();
			this.changemode = changemode;
		}

	}

	// TODO:
	public class GlobImpop2Ident implements IGlobImpop2 {

		// empty
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

	// TODO:
	public class CpsStoDeclopDo implements ICpsStoDeclop {

		// empty
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

	// TODO:
	public class Paramop implements IParamop {

		// empty
	}

	public class CmdSkip implements IConcCmd {
		private IToken skip;

		public CmdSkip(IToken skip) {
			super();
			this.skip = skip;
		}

		@Override
		public IAbsCmd toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdBecomes implements IConcCmd {
		private IConcExpr expr1;
		private IToken becomes;
		private IConcExpr expr2;

		public CmdBecomes(IConcExpr expr1, IToken becomes, IConcExpr expr2) {
			super();
			this.expr1 = expr1;
			this.becomes = becomes;
			this.expr2 = expr2;
		}

		@Override
		public IAbsCmd toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdIf implements IConcCmd {
		private IToken tokenIf;
		private IConcExpr expr;
		private IToken then;
		private ICpsCmd cpsCmd1;
		private IToken tokenElse;
		private ICpsCmd cpsCmd2;
		private IToken endif;

		public CmdIf(IToken tokenIf, IConcExpr expr, IToken then,
				ICpsCmd cpsCmd1, IToken tokenElse, ICpsCmd cpsCmd2, IToken endif) {
			super();
			this.tokenIf = tokenIf;
			this.expr = expr;
			this.then = then;
			this.cpsCmd1 = cpsCmd1;
			this.tokenElse = tokenElse;
			this.cpsCmd2 = cpsCmd2;
			this.endif = endif;
		}

		@Override
		public IAbsCmd toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdWhile implements IConcCmd {
		private IToken tokenWhile;
		private IConcExpr expr;
		private IToken tokenDo;
		private ICpsCmd cpsCmd;
		private IToken endWhile;

		public CmdWhile(IToken tokenWhile, IConcExpr expr, IToken tokenDo,
				ICpsCmd cpsCmd, IToken endWhile) {
			super();
			this.tokenWhile = tokenWhile;
			this.expr = expr;
			this.tokenDo = tokenDo;
			this.cpsCmd = cpsCmd;
			this.endWhile = endWhile;
		}

		@Override
		public IAbsCmd toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdCall implements IConcCmd {
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

		@Override
		public IAbsCmd toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdDebugin implements IConcCmd {
		private IToken debugin;
		private IConcExpr expr;

		public CmdDebugin(IToken debugin, IConcExpr expr) {
			super();
			this.debugin = debugin;
			this.expr = expr;
		}

		@Override
		public IAbsCmd toAbs() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdDebugout implements IConcCmd {
		private IToken debugout;
		private IConcExpr expr;

		public CmdDebugout(IToken debugout, IConcExpr expr) {
			super();
			this.debugout = debugout;
			this.expr = expr;
		}

		@Override
		public IAbsCmd toAbs() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class CmdopInit implements ICmdop {
		private IGlobInits globInits;

		public CmdopInit(IGlobInits globInits) {
			super();
			this.globInits = globInits;
		}

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class Cmdop implements ICmdop {

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
		}

		// empty
	}

	public class GlobInits implements IGlobInits {
		private IToken init;
		private IIdents idents;

		public GlobInits(IToken init, IIdents idents) {
			super();
			this.init = init;
			this.idents = idents;
		}

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
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

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
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

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO: empty
	public class Identsop implements IIdentsop {

		@Override
		public IAbsCmd toAbs(IAbsCmd cmd) {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
