// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class ErrorFormParCm extends FormalParamList {

    private FormalParamDecl FormalParamDecl;

    public ErrorFormParCm (FormalParamDecl FormalParamDecl) {
        this.FormalParamDecl=FormalParamDecl;
        if(FormalParamDecl!=null) FormalParamDecl.setParent(this);
    }

    public FormalParamDecl getFormalParamDecl() {
        return FormalParamDecl;
    }

    public void setFormalParamDecl(FormalParamDecl FormalParamDecl) {
        this.FormalParamDecl=FormalParamDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParamDecl!=null) FormalParamDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamDecl!=null) FormalParamDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamDecl!=null) FormalParamDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ErrorFormParCm(\n");

        if(FormalParamDecl!=null)
            buffer.append(FormalParamDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ErrorFormParCm]");
        return buffer.toString();
    }
}
