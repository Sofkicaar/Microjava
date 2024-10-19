// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class FormalPDecl extends FormalParamDecl {

    private FormParamElem FormParamElem;

    public FormalPDecl (FormParamElem FormParamElem) {
        this.FormParamElem=FormParamElem;
        if(FormParamElem!=null) FormParamElem.setParent(this);
    }

    public FormParamElem getFormParamElem() {
        return FormParamElem;
    }

    public void setFormParamElem(FormParamElem FormParamElem) {
        this.FormParamElem=FormParamElem;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParamElem!=null) FormParamElem.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParamElem!=null) FormParamElem.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParamElem!=null) FormParamElem.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalPDecl(\n");

        if(FormParamElem!=null)
            buffer.append(FormParamElem.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalPDecl]");
        return buffer.toString();
    }
}
