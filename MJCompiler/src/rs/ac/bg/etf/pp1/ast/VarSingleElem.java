// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class VarSingleElem extends VarDeclElem {

    private String varElem;

    public VarSingleElem (String varElem) {
        this.varElem=varElem;
    }

    public String getVarElem() {
        return varElem;
    }

    public void setVarElem(String varElem) {
        this.varElem=varElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarSingleElem(\n");

        buffer.append(" "+tab+varElem);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarSingleElem]");
        return buffer.toString();
    }
}
