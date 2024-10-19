package rs.ac.bg.etf.pp1;

import java.util.List;
import java.util.ArrayList;


import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.visitors.*;

public class SemanticPass extends VisitorAdaptor {
	
	private Object object;
	int printCallCount = 0;
	int varDeclCount = 0;
	boolean returnFound = false;
	Struct structType = null;
	Obj trenProm = null;
	Obj currentMethod = null;
	boolean errorDetected = false;
	 int nVars = 0; 
	public int nameVars = 0;
	Integer vrednostKonst = 0;
	int ulazimIzName = 0;
	String ime = "";
	String sad = "";
	boolean jeste = false;
	
	//posto bool nije podrzan
	
	
	Logger log = Logger.getLogger(getClass());
	
	public SemanticPass() {
		
	}
	 
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	
	
	
	
    //program
    
    public void visit(ProgName progName) {
    	
    	if(Tab.currentScope.findSymbol(progName.getProgName()) != null) {
    		report_error("Greska! Program sa datim imenom vec postoji", progName);
    	} 
    	
    	progName.obj = Tab.insert(Obj.Prog, progName.getProgName(), Tab.noType);
    	
    	Tab.openScope();
    	
    } 
    
    public void visit(Program program) {
    	nVars = Tab.currentScope.getnVars();
    	Obj mainObj = Tab.currentScope.findSymbol("main");
    //	report_info("Broj globalnih promenljivih : " + nVars, program);
		if(mainObj != null && mainObj.getKind()==Obj.Meth) {report_info(" doda se lepo", null);
    		
    		
		Tab.chainLocalSymbols(program.getProgName().obj);
		   
    	Tab.closeScope();
    	}
	
		else {
			report_error("program ne moze biti bez main funkcije", program);
			Tab.closeScope();
		}
    }
    
    
   //namespace -> po uzoru na klase 
    
    public void visit(NameSp sp) {
    	
    	
    	if(Tab.currentScope.findSymbol(sp.getNameSpace()) != null) {
    		report_error("Greska na liniji" + sp.getLine() + "namespace vec postoji", sp);
    	} 
    	 //da li treba da otvaram novi scope?
    	
    	sp.obj = Tab.insert(Obj.Elem, sp.getNameSpace(), new Struct(Struct.Class));
    	ulazimIzName = 1;
    	ime =  sp.getNameSpace();
  
    }
    
    public void visit(NameSpaceSamo n) {
    	ulazimIzName = 0;
    	
   
    }
    
  //tip
	
  
    public void visit(SamoType st) {
    	
    		Obj typeNode = Tab.find(st.getTypeName());
    		//	System.out.println(type.getTypeName());
    			if(st.getTypeName().equals("bool")) {
    				
    				typeNode = Tab.insert(Obj.Type, "bool", TabIzv.booltype);
    	    		st.struct=TabIzv.booltype;
    	    		structType = typeNode.getType();
    	    	}else {
    			if(typeNode == Tab.noObj) {
    				report_error("Nije pronadjen tip " + st.getTypeName() + " u tabeli simbola!", null);
    				st.struct = Tab.noType;
    			}
    			else {
    				if(Obj.Type == typeNode.getKind()) {
    					st.struct = typeNode.getType();
    					structType = st.struct;
    					
    				}
    				else {
    					report_error("Greska: Ime " + st.getTypeName() + " ne predstavlja tip!", st);
    					st.struct = Tab.noType;
    				}
    			}
    	    	}
    	
			
	}
    
    
    public void visit(TypeNiz tn) {
    	
    		Obj typeNode = Tab.find(tn.getTypeName());
    		//	System.out.println(type.getTypeName());
    			if(tn.getTypeName().equals("bool")) {
    				
    	    		typeNode = Tab.insert(Obj.Type, "bool", TabIzv.booltype);
    	    		tn.struct=TabIzv.booltype;
    	    		structType = typeNode.getType();
    	    	
    	    	}else
    	    	{
    			if(typeNode == Tab.noObj) {
    				report_error("Nije pronadjen tip " + tn.getTypeName() + " u tabeli simbola!", null);
    				tn.struct = Tab.noType;
    			}
    			else {
    				if(Obj.Type == typeNode.getKind()) {
    					tn.struct = typeNode.getType();
    					structType = tn.struct;
    				}
    				else {
    					report_error("Greska: Ime " + tn.getTypeName() + " ne predstavlja tip!", tn);
    					tn.struct = Tab.noType;
    				}
    			}
    	    	}
    	
    	
	
    }
    
    
    
	//promenljive 
    
    public void visit(VarArrayElem varElem){
    	
		//provera da li vec postoji
		
		if(ulazimIzName == 0) {
			if(Tab.currentScope.findSymbol(varElem.getVarElem()) != null) 
			{
				 report_error("Greska: niz sa datim imenom  "+ varElem.getVarElem() + "vec postoji" , varElem);
			}
			varDeclCount++;
			report_info("Deklarisana promenljiva "+ varElem.getVarElem(), varElem);
			Struct tip = new Struct(Struct.Array, structType);
		//	System.out.println(structType);
			Obj varNode = Tab.insert(Obj.Var, varElem.getVarElem(), tip);
		}
		else {
			
			if(Tab.currentScope.findSymbol(ime + "::" + varElem.getVarElem()) != null) {
				report_error("Promenljiva nizvnog tipa " + varElem.getVarElem() + " se vec nalazi u nejmu " + ime, varElem);
			}
			
			else {
				
				Struct tip = new Struct(Struct.Array, structType);
			  	Obj objekat = Tab.insert(Obj.Var, ime + "::" + varElem.getVarElem(), tip);
				varDeclCount++;
				report_info("Deklarisana promenljiva nizovnog tipa "+ varElem.getVarElem() + " u namespace-u", varElem);
				
			}
				
		
		}
	}
	

	
	public void visit(VarSingleElem varS){
		
		if(ulazimIzName == 0) {
			if(Tab.currentScope.findSymbol(varS.getVarElem()) != null) 
			{
				 report_error("Greska: promenljiva sa zadatim imenom  "+ varS.getVarElem() + " vec postoji" , varS);
			}
			varDeclCount++;
			report_info("Deklarisana promenljiva "+ varS.getVarElem() + " str " + structType.getKind(), varS);
			
			Obj varNode = Tab.insert(Obj.Var, varS.getVarElem(), structType);
			
		}
		else {
			   
			if(Tab.currentScope.findSymbol(ime + "::" + varS.getVarElem()) != null) {
				report_error("Promenljiva nizvnog tipa " + varS.getVarElem() + " se vec nalazi u nejmu " + ime, varS);
			}
			
			
		   else {
			    
			    varDeclCount++;
				report_info("Deklarisana promenljiva "+ varS.getVarElem() + " u namespace-u", varS);
				Obj objekat = Tab.insert(Obj.Var, ime + "::" + varS.getVarElem() , structType);
				
		   }
				
		
		}
	
		}
		
	
    
	
	
	//konstante
	
	
	public void visit(ConstElemm c) {
		if(ulazimIzName == 0) {
			Obj konst = Tab.currentScope.findSymbol(c.getConstName());
			
			if(konst != null) {
				report_error("Konstanta sa datim imenom vec postoji " + c.getConstName() + "!", c);
			}
			if(!(structType.equals(Tab.intType) || structType.equals(Tab.charType) || structType.equals(TabIzv.booltype))) {
				report_error("Konstanta datog imena " + c.getConstName() + " nije dobrog tipa!", c);
			}
			else {
				Obj obj1 = Tab.insert(Obj.Con,c.getConstName(), structType);
				report_info("Deklarisana konstanta imena " + c.getConstName(), c);
				obj1.setAdr(vrednostKonst);
			}
		}
		
		else {
			if(!(structType.equals(Tab.intType) || structType.equals(Tab.charType) || structType.equals(TabIzv.booltype))) {
				report_error("Konstanta datog imena " + c.getConstName() + " nije dobrog tipa!", c);
			}
			else {
				
				if(Tab.currentScope.findSymbol(ime + "::" + c.getConstName())!= null) {
					report_error("Konstanta datog imena " + c.getConstName() + " vec postoji u nejmu " + ime, c);
				}
				
				else {
					report_info("Deklarisana konstanta imena " + c.getConstName(), c);
					Obj objekat = Tab.insert(Obj.Con, ime + "::" + c.getConstName(), structType);
					
					
					objekat.setAdr(vrednostKonst);
				}
				
				
				
			}
		}
		
	}
	
	 public void visit(NConst nc) {
	    	if(!(structType.equals(Tab.intType))) {
	    		report_error("Greska na liniji " + nc.getLine() + " tip konstante nije int.", nc);
	    	}
	    	else 
	    	{
	    		nc.obj = new Obj(Obj.Con,"",Tab.intType);
	    		vrednostKonst = nc.getValue();
	    	}
	    }
	
	 public void visit(CConst cc) {
		 if(!(structType.equals(Tab.charType))) {
	    		report_error("Greska na liniji " + cc.getLine() + " tip konstante nije char.", cc);
    	}
	    else {
	    	cc.obj = new Obj(Obj.Con,"",Tab.charType);
	    	vrednostKonst = (int) cc.getValue();
	    }
	 }
	
	 
	 public void visit(BBConst bc) {
		 if(!(structType.equals(TabIzv.booltype))) {
	    		report_error("Greska na liniji " + bc.getLine() + " tip konstante nije bool.", bc);
 	}
	    else {
	    	bc.obj = new Obj(Obj.Con,"",TabIzv.booltype);
	    	vrednostKonst = bc.getValue() ? 1: 0;
	    }
	 }
	 
	 
	 
	//metode
	    
    public void visit(MethodReturnType mtn) {
   	if(Tab.currentScope.findSymbol(mtn.getMethName()) == null) {
    		currentMethod = Tab.insert(Obj.Meth, mtn.getMethName(), mtn.getType().struct);
        	mtn.obj = currentMethod;
        	
        //	report_info("dodaooooooo"  + mtn.getMethName(), mtn); 
    	}
    	else {
    		currentMethod = Tab.noObj;
    		report_error("Metoda sa imenom " + mtn.getMethName() + " vec postoji u trenutnom scope-u", mtn);
    	} 
    	
    	
    	Tab.openScope();
    	
    	report_info("Obradjuje se fja sa " + mtn.getType().struct.getKind() +" povratnim tipom, "+ mtn.getMethName(), mtn); 
    	
    }
    public void visit(MethodReturnVoid mtn) {
    	if(Tab.currentScope.findSymbol(mtn.getMethName()) == null) {
    		currentMethod = Tab.insert(Obj.Meth, mtn.getMethName(), Tab.noType);
    		
        	mtn.obj = currentMethod;
    	}
    	else {
    		currentMethod = Tab.noObj;
    		report_error("Metoda sa imenom " + mtn.getMethName() + " vec postoji u trenutnom scope-u", mtn);
    	} 
    	
    	Tab.openScope();
    	report_info("Obradjuje se fja sa void povratnim tipom,  "+ mtn.getMethName(), mtn); 
        	
    }
    
 
    public void visit(MethodDeclarationForm md) {
    	report_info("promenljive u metodi ", null);
    	if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Funkcija " + currentMethod.getName() + " nema return iskaz, a nije void ", md);
		}
    	
    	//nVars += Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(currentMethod);
    	int a =  Tab.currentScope.getnVars();
    	System.out.println("prom " + a );
    	nVars += a;
    	
    	Tab.closeScope();
   
    	
    	currentMethod = null;
    	structType = null;
    }
   
    
    public void visit(MethodDeclaration md) {
    	
    	if (!returnFound && currentMethod.getType() != Tab.noType) {
			report_error("Funkcija " + currentMethod.getName() + " nema return iskaz, a nije void ", md);
		}
    	if (returnFound && currentMethod.getType() == Tab.noType) {
			report_error("Funkcija " + currentMethod.getName() + " ima return iskaz, a void je ", md);
		}
    	
    	Tab.chainLocalSymbols(currentMethod);
    	//nVars = Tab.currentScope.getnVars();
    	int a =  Tab.currentScope.getnVars();
    	
    	nVars += a;
    	System.out.println("nvars " + nVars );
    	Tab.closeScope();
   
    	returnFound = false;
    	currentMethod = null;
    	structType = null;
    }
	    
//statementi
    
    public void visit(ReadStat rs) {
		Obj obj = rs.getDesignator().obj;
		if(obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
			report_error("Designator mora biti promenljiva, element niza ili polje klase uz readStatement", rs);
		}
		if(obj.getType() != Tab.intType && obj.getType() != Tab.charType && obj.getType() !=  TabIzv.booltype)
			report_error("Designator mora biti tipa int, char ili bool uz readStatement", rs);
	}
    
    
    public void visit(ReturnExpr returnExpr){
    	returnFound = true;
    	Struct currMethType = currentMethod.getType();
    	report_info("aloooooooooo" + currMethType.getKind(), null); //1 int 
    	if(!currMethType.compatibleWith(returnExpr.getExpr().struct)){
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
    	}
    	
    }
    
    public void visit(PrintStmt print) {
		
		if(print.getExpr().struct != Tab.intType &&
				print.getExpr().struct != Tab.charType &&
				print.getExpr().struct !=  TabIzv.booltype) {
			report_error("Expr mora biti tipa int, char ili bool uz printStatement", print);
		}
	
		printCallCount++;
	}
    
public void visit(PrintStmtNum print) {
		
		if(print.getExpr().struct != Tab.intType &&
				print.getExpr().struct != Tab.charType &&
				print.getExpr().struct !=  TabIzv.booltype) {
			report_error("Expr mora biti tipa int, char ili bool uz printStatement", print);
		}
	
		printCallCount++;
	}
    
    public void visit(ReturnNoExpr rt) {
		if(currentMethod.getType() != Tab.noType) {
			report_error("Povratna vrednost funkcije " + currentMethod.getName() + " mora biti VOID", rt);
		}
	}
    
    
    
    
  //faktori 
	
	
  	public void visit(FactDSingle fs ) {
  		
  		fs.struct = fs.getDesignator().obj.getType();
  	}
  	
  	public void visit(CharF fc ) {
  	//	System.out.println("ej kod char sam");
  		fc.struct = Tab.charType;
  	}
  	public void visit(NumF fn ) {
  	//	System.out.println("ej kod numsam sam");
  		fn.struct = Tab.intType;
  	}
  	public void visit(BoolF fb) {
  		//System.out.println("ej kod boolfb sam");
  		fb.struct =  TabIzv.booltype;
  	}
      
  	public void visit(NewExpr fn) {
  		
  		if(fn.getExpr().struct!=Tab.intType) {
  			report_error("Za indeksiranje niza mora se koristiti int!", fn);
  		}
  		fn.struct = new Struct(Struct.Array, structType);
  	}
  	
  	public void visit(NewNoPars fp ) {
  		fp.struct = fp.getType().struct;
  	}
  	
  	public void visit(FactorExpression fe ) {
  		fe.struct = fe.getExpr().struct;
  	}
  	
  	public void visit(NewActPars fa) {
  		fa.struct = fa.getType().struct;
  	}
  	
  	
  	
  //expr
    
	
  	public void visit(ExprList addExpr) {
  		
  	
  		Struct t1 = addExpr.getExpr().struct;
  		Struct t2 = addExpr.getTerm().struct;
  		
  		if (t1 == Tab.intType && t2 == Tab.intType)
  			addExpr.struct = Tab.intType;
  		else {
  			report_error("Nekompatibilni tipovi u izrazu uz addop expression", addExpr);
  			addExpr.struct = Tab.noType;
  		} 
  	}
  	
  	
  	public void visit(ExprS termExpr) {
  		termExpr.struct = termExpr.getTerm().struct;
  		//System.out.println("expr je " + termExpr.getTerm().struct.getKind());
  	}
  	
  	
  	public void visit(ExprMinus mT) {
  		Struct t1 = mT.getTerm().struct;
  		if(t1 == Tab.intType)
  			mT.struct = Tab.intType;
  		else {
  			report_error("Nekompatibilan tip u izrazu uz minus term expression", mT);
  			mT.struct = Tab.noType;
  		}
  	}
  	
  	
 //term
    
    public void visit(MulOpList ml) {
    	Struct t1 = ml.getTerm().struct;
		Struct t2 = ml.getFactor().struct;
		if (t1 == Tab.intType && t2 == Tab.intType)
			ml.struct = Tab.intType;
		else {
			report_error("Nekompatibilni tipovi u izrazu uz MulOp", ml);
			ml.struct = Tab.noType;
		} 
    }
    
    public void visit(TermFactL tl) {
    //	System.out.println("usao u factor");
    	tl.struct = tl.getFactor().struct;
    }
    
    
//designatoristatmeent
    
    public void visit(DesignatorStatementAssign d) {
    	Obj obj = d.getDesignator().obj;
    	
    	if(obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
    		report_error("Greska na liniji " + d.getLine() + "designator nije dozvoljenog tipa ", d);
    	}
    	//System.out.println("expr je " + d.getExpr().struct.getKind() + " a desig je " + d.getDesignator().obj.getType().getKind());
    	if(!d.getExpr().struct.assignableTo(d.getDesignator().obj.getType())) {
    		report_error("Greska na liniji " + d.getLine() + " nekompatibilni tipovi pri dodeli ", d);
    	}
    } 
    
    public void visit(DesignatorStatementInc d) {
    	Obj obj = d.getDesignator().obj;
    	
    	if(obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
    		report_error("Greska na liniji " + d.getLine() + "designator inkrement nije dozvoljenog tipa ", d);
    	}
    	else {
    		 if(d.getDesignator().obj.getType()!=Tab.intType) {
      			 report_error("Greska na liniji " + d.getLine() + " designator mora da bude tipa int ", d);
      		   }
    	}
    }
    
    public void visit(DesignatorStatementDekr d) {
    	Obj obj = d.getDesignator().obj;
    	
    	if(obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem && obj.getKind() != Obj.Fld) {
    		report_error("Greska na liniji " + d.getLine() + "designator dekrement nije dozvoljenog tipa ", d);
    	}
    	else {
    		 if(d.getDesignator().obj.getType()!=Tab.intType) {
      			 report_error("Greska na liniji " + d.getLine() + " designator mora da bude tipa int ", d);
      		   }
    	}
    } 
    
    public void visit(UsingIdent u) {
    	Obj obj = Tab.find(u.getI1());
    	
    	if(obj == Tab.noObj) {
    		report_error("greska!", u);
    	}
    	else {
    		jeste = true;
    		sad = u.getI1();
    	}
    }
  //designatori
    public void visit(DesigSamo d){
		Obj obj = Tab.find(d.getName());
	
		if (obj == Tab.noObj) { 
			
			report_error("Greska na liniji " + d.getLine() + " detektovano koriscenje desig koji nije u tabeli simbola" + d.getName(), d);
		} else {
			if(obj.getKind() == Obj.Var) {
				DumpSymbolTableVisitor dumpVar = new DumpSymbolTableVisitor();
				dumpVar.visitObjNode(obj);
				if(obj.getLevel() == 0) { // globalna
					
					report_info("Detektovano koriscenje globalne promenljive " + dumpVar.getOutput(), d);
				} else { // lokalna
					report_info("Detektovano koriscenje lokalne promenljive " + dumpVar.getOutput(), d);
				}
			}
		}
		d.obj = obj;
	}
    
    public void visit(DesignatorUglaste d) {
    	
		Obj designatorArrayObj = d.getDesignator().obj;	// simbol za niz
		boolean errorFound = false;
		//System.out.println("DSSSSSSSSSSSSSSS JEEEEEEEEEEE" + designatorArrayObj.getType().getKind());
		if(designatorArrayObj.getType().getKind() != Struct.Array ) {
			errorFound = true;
			d.obj = Tab.noObj;
			report_error("Greska na liniji " + d.getLine() + ", expr mora biti tipa array", d);
		}
		if(d.getExpr().struct != Tab.intType) {
			errorFound = true;
			d.obj = Tab.noObj;
			report_error("Greska na liniji " + d.getLine() + ", expr mora biti tipa int", d);
		}
		if(!errorFound) {
			d.obj = new Obj(Obj.Elem, d.getDesignator().obj.getName(), designatorArrayObj.getType().getElemType());
		//	d.obj.setFpPos(designatorArrayObj.getFpPos()); // da znamo jel elem niza final
			DumpSymbolTableVisitor dumpVar = new DumpSymbolTableVisitor();
			dumpVar.visitObjNode(designatorArrayObj);
			report_info("Detektovan pristup elementu niza " + dumpVar.getOutput(), d);
		}
	}
    
   public void visit(DesigNiz d) {
	 //  report_info("usaooooooooooo u desniz", d);
	   Obj obj = Tab.find(d.getI1());
	 
	   if(obj == Tab.noObj) {
		   report_error("Greska na liniji " + d.getLine() + "detektovano koriscenje namespace-a koji nije u tabeli simbola" + d.getName(), d);
	   }
	   else {
		   String s = d.getI1() + "::" + d.getName();
		   Obj obj2 = Tab.find(s);
		   
		   
		   
		 /*  for(int i = 0; i<listaNejmm.size(); i++) {
			  
			   if(listaNejmm.get(i).dohvatiIme().equals(d.getI1())) {
				   System.out.println("usao");
				   for(Obj obj1 : listaNejmm.get(i).dohvatiListu()) {
					   
					   String s = d.getI1() + "::" + obj1.getName();
					   String s2 = d.getI1() + "::" + d.getName();
					   System.out.println(s2 + s);
					   if(s.equals(s2)) {
						   obj2 = obj1;
					   }
				   }
			   }
		   } */
		   
		   
		   
		  
		   if(obj2 == Tab.noObj ) {
			   report_error("Pristup prom " + d.getName() + " koja ne postoji u datom nejmspejsu " + d.getI1(), d);
		   }
		   
		   report_info("Pristup promenljivoj " + d.getName() + " iz namespace-a " + d.getI1(), d);
		   d.obj = obj2;
		   
		   }
	   
	   
   }
    
    public boolean passed() {
    	return !errorDetected;
    	
    }   
     
    
}
