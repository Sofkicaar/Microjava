// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class SingleExpr extends CondFact {

    private Exp Exp;

    public SingleExpr (Exp Exp) {
        this.Exp=Exp;
        if(Exp!=null) Exp.setParent(this);
    }

    public Exp getExp() {
        return Exp;
    }

    public void setExp(Exp Exp) {
        this.Exp=Exp;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Exp!=null) Exp.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Exp!=null) Exp.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Exp!=null) Exp.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleExpr(\n");

        if(Exp!=null)
            buffer.append(Exp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleExpr]");
        return buffer.toString();
    }
}
