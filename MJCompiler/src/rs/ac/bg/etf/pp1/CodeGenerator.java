package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.CounterVisitor.*;
public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	private int number;
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void inic(String name) {
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		System.out.println("load_n");
		if(name.equals("len"))Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	public void visit(ProgName prog) {
		Tab.find("chr").setAdr(Code.pc);
		inic("chr");
		Tab.find("ord").setAdr(Code.pc);
		inic("ord");
		Tab.find("len").setAdr(Code.pc);
		inic("len");
	}
	
	
	//expression
	

	
	public void visit(PrintStmt print){
		
		if(print.getExpr().struct == Tab.charType){
			Code.loadConst(1);
			Code.put(Code.bprint);
		}else{
			Code.loadConst(5);
			Code.put(Code.print);
		}
	}
	

	public void visit(PrintStmtNum print) {
		if(print.getExpr().struct == Tab.intType || print.getExpr().struct == TabIzv.booltype) {
			
			
			Code.loadConst(5);
			Code.put(Code.print);
			
			if(number > 0) {
				Code.loadConst(number);
				Code.loadConst(5);
				Code.put(Code.print);
			}
			else {
				Code.loadConst(1);
				Code.loadConst(5);
				
				Code.put(Code.print);
			}
		}else{ //char je
			
			Code.loadConst(1);
			Code.put(Code.bprint);
			
			if(number > 0) {
				Code.loadConst(number);
				Code.loadConst(5);
				Code.put(Code.print);
			}
			else {
				Code.loadConst(1);
				Code.loadConst(5);
				
				Code.put(Code.print);
			}
		}
	}
	
	public void visit(NumList n) {
		number = n.getN1();
	}
	
	public void visit(ReadStat r) {
		Obj obj = r.getDesignator().obj;
		if(obj.getType() == Tab.charType)
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(obj);
	}
	
	

	public void visit(ReturnExpr ReturnExpr) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	//name
	
	public void visit(NameSpaceSamo n) {
		Code.load(n.getNameSp().obj);
	}
	
	
	//metode 
	
	
	public void visit(MethodReturnVoid methodTypeName) {
		methodTypeName.obj.setAdr(Code.pc);
		if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		
		SyntaxNode methodNode = methodTypeName.getParent();
		
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		System.out.println(varCnt.getCount());
		// Generate the entry.
		int a = 0;
		 a += varCnt.getCount();
		Code.put(Code.enter);
		Code.put(0);
		Code.put(a);
	}
	
	
	public void visit(MethodReturnType methodTypeName) {
		methodTypeName.obj.setAdr(Code.pc);
		if ("main".equalsIgnoreCase(methodTypeName.getMethName())) {
			mainPc = Code.pc;
		}
		SyntaxNode methodNode = methodTypeName.getParent();
		
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		System.out.println(varCnt.getCount());
		int a = 0;
		a += varCnt.getCount();
		// Generate the entry.
		Code.put(Code.enter);
		Code.put(0);
		Code.put(a);
	}
	
	
	
	
	public void visit(MethodDeclaration m) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	
	//faktor
	
	
	public void visit(NumF constFactor) {
		
		Code.loadConst(constFactor.getN1()); 
		
	}
	
	
	public void visit(CharF constFactor) {
	//	System.out.println("aloooo ne ovde jbt");
		Code.loadConst(constFactor.getC1()); 
	}
	
	
	public void visit(BoolF constFactor) {
		int i = 0;
		if(constFactor.getB1() == true) i = 1;
		Code.loadConst(i);
	}
	
	public void visit(NewExpr f) {
		Code.put(Code.newarray);
		if(f.getType().struct != Tab.charType)
			Code.put(1);	
		else
			Code.put(0);	
	}
	
	
	public void visit(FactDSingle f) {
		
		Code.load(f.getDesignator().obj);
	}
	
	
	
	//designatori
	public void visit(DesignatorUglaste d) {
		
		
		Code.load(d.getDesignator().obj);
		
		pom();
		
	}
	
	private void pom() {
		Code.put(Code.dup_x1);
		Code.put(Code.pop);
	}
	
	
	
	
	
	//desig statement
	public void visit(DesignatorStatementInc d) {
		Obj obj = d.getDesignator().obj;
		if(obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(obj);
		pomocna2();
		Code.store(obj);
	}
	private void pomocna2() {
		Code.loadConst(1);
		System.out.println("Ovdeee");
		Code.put(Code.add);
	}
	
	public void visit(DesignatorStatementDekr d) {
		Obj obj = d.getDesignator().obj;
		if(obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(obj);
		pomocna();
		Code.store(obj);
	}
	
	private void pomocna() {
		Code.loadConst(1);
		Code.put(Code.sub);
	}
	
 	public void visit(DesignatorStatementAssign d) {
 		Obj obj = d.getDesignator().obj;
 		System.out.println("designator jeeee " + d.getDesignator().obj.getName());
		Code.store(obj);
 	}
	
	
	//expr 
	
		public void visit(ExprList e) {
			if(e.getAddop() instanceof PlusOp) {
				Code.put(Code.add);
				
			}
			else Code.put(Code.sub);
		}
		
		
		public void visit(ExprMinus e) {
			Code.put(Code.neg);
		}
		
		public void visit(MulOpList m) {
			if(m.getMulop() instanceof MullOp) {
				Code.put(Code.mul);
			}
			else if(m.getMulop() instanceof DivOp) Code.put(Code.div);
			else Code.put(Code.rem); //mod je ovo
		} 
		
		
		
}



