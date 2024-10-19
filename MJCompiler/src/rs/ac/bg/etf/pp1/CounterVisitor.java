package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.VDList;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.VarElem;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CounterVisitor extends VisitorAdaptor {
	protected int count;
	
	public int getCount(){
		return count;
	}
	
	public static class VarCounter extends CounterVisitor{
		
		public void visit(VarElem varDecl){
			count++;
		}
		public void visit(VDList vard) {
			count++;
		}
		
		public int getCount() {return count;}
	}
}
