// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class ConstElemSingle extends ConstDeclList {

    private ConstElem ConstElem;

    public ConstElemSingle (ConstElem ConstElem) {
        this.ConstElem=ConstElem;
        if(ConstElem!=null) ConstElem.setParent(this);
    }

    public ConstElem getConstElem() {
        return ConstElem;
    }

    public void setConstElem(ConstElem ConstElem) {
        this.ConstElem=ConstElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstElem!=null) ConstElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstElem!=null) ConstElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstElem!=null) ConstElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstElemSingle(\n");

        if(ConstElem!=null)
            buffer.append(ConstElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstElemSingle]");
        return buffer.toString();
    }
}
