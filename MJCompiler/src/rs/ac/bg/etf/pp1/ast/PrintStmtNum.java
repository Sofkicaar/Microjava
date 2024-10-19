// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class PrintStmtNum extends Statement {

    private Expr Expr;
    private NumbList NumbList;

    public PrintStmtNum (Expr Expr, NumbList NumbList) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.NumbList=NumbList;
        if(NumbList!=null) NumbList.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public NumbList getNumbList() {
        return NumbList;
    }

    public void setNumbList(NumbList NumbList) {
        this.NumbList=NumbList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(NumbList!=null) NumbList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(NumbList!=null) NumbList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(NumbList!=null) NumbList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmtNum(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NumbList!=null)
            buffer.append(NumbList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStmtNum]");
        return buffer.toString();
    }
}
