package rs.ac.bg.etf.pp1;

import java.util.List;
import java.util.ArrayList;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.Obj;

public class NejmSpejs {
	
	private String ime;
	private List<Obj> lista;
	
	public NejmSpejs(String imee) {
		ime = imee;
		lista = new ArrayList<>();
	}
		
	public String dohvatiIme() {
		return ime;
	}
	
	public List<Obj> dohvatiListu() {
		return lista;
	}
	
	public void setLista(List<Obj> l) {
		lista = l;
	}
	public void setIme(String i) {ime = i;}
}
