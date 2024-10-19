// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class NameSpaceL extends NameSpaceList {

    private NameSpaceList NameSpaceList;
    private NameSpace NameSpace;

    public NameSpaceL (NameSpaceList NameSpaceList, NameSpace NameSpace) {
        this.NameSpaceList=NameSpaceList;
        if(NameSpaceList!=null) NameSpaceList.setParent(this);
        this.NameSpace=NameSpace;
        if(NameSpace!=null) NameSpace.setParent(this);
    }

    public NameSpaceList getNameSpaceList() {
        return NameSpaceList;
    }

    public void setNameSpaceList(NameSpaceList NameSpaceList) {
        this.NameSpaceList=NameSpaceList;
    }

    public NameSpace getNameSpace() {
        return NameSpace;
    }

    public void setNameSpace(NameSpace NameSpace) {
        this.NameSpace=NameSpace;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NameSpaceList!=null) NameSpaceList.accept(visitor);
        if(NameSpace!=null) NameSpace.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NameSpaceList!=null) NameSpaceList.traverseTopDown(visitor);
        if(NameSpace!=null) NameSpace.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NameSpaceList!=null) NameSpaceList.traverseBottomUp(visitor);
        if(NameSpace!=null) NameSpace.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("NameSpaceL(\n");

        if(NameSpaceList!=null)
            buffer.append(NameSpaceList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NameSpace!=null)
            buffer.append(NameSpace.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NameSpaceL]");
        return buffer.toString();
    }
}
