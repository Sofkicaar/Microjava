package rs.ac.bg.etf.pp1;


import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;

public class TabIzv extends Tab {
	
	static Struct booltype = new Struct(Struct.Bool);
	public static void init() {
		Tab.init();
		
		Scope scopeTr = Tab.currentScope();
		scopeTr.addToLocals(new Obj(Obj.Type, "bool", booltype));
	}
}
