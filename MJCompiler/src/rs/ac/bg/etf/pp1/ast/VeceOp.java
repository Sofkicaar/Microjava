// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class VeceOp extends Relop {

    public VeceOp () {
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
        buffer.append("VeceOp(\n");

        buffer.append(tab);
        buffer.append(") [VeceOp]");
        return buffer.toString();
    }
}
