// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class NameSpaceSamo extends NameSpace {

    private NameSp NameSp;
    private DeclList DeclList;
    private MethodName MethodName;

    public NameSpaceSamo (NameSp NameSp, DeclList DeclList, MethodName MethodName) {
        this.NameSp=NameSp;
        if(NameSp!=null) NameSp.setParent(this);
        this.DeclList=DeclList;
        if(DeclList!=null) DeclList.setParent(this);
        this.MethodName=MethodName;
        if(MethodName!=null) MethodName.setParent(this);
    }

    public NameSp getNameSp() {
        return NameSp;
    }

    public void setNameSp(NameSp NameSp) {
        this.NameSp=NameSp;
    }

    public DeclList getDeclList() {
        return DeclList;
    }

    public void setDeclList(DeclList DeclList) {
        this.DeclList=DeclList;
    }

    public MethodName getMethodName() {
        return MethodName;
    }

    public void setMethodName(MethodName MethodName) {
        this.MethodName=MethodName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NameSp!=null) NameSp.accept(visitor);
        if(DeclList!=null) DeclList.accept(visitor);
        if(MethodName!=null) MethodName.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NameSp!=null) NameSp.traverseTopDown(visitor);
        if(DeclList!=null) DeclList.traverseTopDown(visitor);
        if(MethodName!=null) MethodName.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NameSp!=null) NameSp.traverseBottomUp(visitor);
        if(DeclList!=null) DeclList.traverseBottomUp(visitor);
        if(MethodName!=null) MethodName.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NameSpaceSamo(\n");

        if(NameSp!=null)
            buffer.append(NameSp.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DeclList!=null)
            buffer.append(DeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodName!=null)
            buffer.append(MethodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NameSpaceSamo]");
        return buffer.toString();
    }
}
