// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class VarElem extends VarDeclList {

    private VarDeclElem VarDeclElem;

    public VarElem (VarDeclElem VarDeclElem) {
        this.VarDeclElem=VarDeclElem;
        if(VarDeclElem!=null) VarDeclElem.setParent(this);
    }

    public VarDeclElem getVarDeclElem() {
        return VarDeclElem;
    }

    public void setVarDeclElem(VarDeclElem VarDeclElem) {
        this.VarDeclElem=VarDeclElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclElem!=null) VarDeclElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclElem!=null) VarDeclElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclElem!=null) VarDeclElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarElem(\n");

        if(VarDeclElem!=null)
            buffer.append(VarDeclElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarElem]");
        return buffer.toString();
    }
}
