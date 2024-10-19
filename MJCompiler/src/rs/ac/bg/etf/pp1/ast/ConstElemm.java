// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class ConstElemm extends ConstElem {

    private String ConstName;
    private ConstSam ConstSam;

    public ConstElemm (String ConstName, ConstSam ConstSam) {
        this.ConstName=ConstName;
        this.ConstSam=ConstSam;
        if(ConstSam!=null) ConstSam.setParent(this);
    }

    public String getConstName() {
        return ConstName;
    }

    public void setConstName(String ConstName) {
        this.ConstName=ConstName;
    }

    public ConstSam getConstSam() {
        return ConstSam;
    }

    public void setConstSam(ConstSam ConstSam) {
        this.ConstSam=ConstSam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstSam!=null) ConstSam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstSam!=null) ConstSam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstSam!=null) ConstSam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstElemm(\n");

        buffer.append(" "+tab+ConstName);
        buffer.append("\n");

        if(ConstSam!=null)
            buffer.append(ConstSam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstElemm]");
        return buffer.toString();
    }
}
