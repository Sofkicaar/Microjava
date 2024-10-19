// generated with ast extension for cup
// version 0.8
// 7/1/2024 10:21:16


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclarationForm extends MethodDecl {

    private MethodTypeName MethodTypeName;
    private FormPars FormPars;
    private DesnaZagrada DesnaZagrada;
    private VarDeclLista VarDeclLista;
    private StatementList StatementList;

    public MethodDeclarationForm (MethodTypeName MethodTypeName, FormPars FormPars, DesnaZagrada DesnaZagrada, VarDeclLista VarDeclLista, StatementList StatementList) {
        this.MethodTypeName=MethodTypeName;
        if(MethodTypeName!=null) MethodTypeName.setParent(this);
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
        this.DesnaZagrada=DesnaZagrada;
        if(DesnaZagrada!=null) DesnaZagrada.setParent(this);
        this.VarDeclLista=VarDeclLista;
        if(VarDeclLista!=null) VarDeclLista.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodTypeName getMethodTypeName() {
        return MethodTypeName;
    }

    public void setMethodTypeName(MethodTypeName MethodTypeName) {
        this.MethodTypeName=MethodTypeName;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public DesnaZagrada getDesnaZagrada() {
        return DesnaZagrada;
    }

    public void setDesnaZagrada(DesnaZagrada DesnaZagrada) {
        this.DesnaZagrada=DesnaZagrada;
    }

    public VarDeclLista getVarDeclLista() {
        return VarDeclLista;
    }

    public void setVarDeclLista(VarDeclLista VarDeclLista) {
        this.VarDeclLista=VarDeclLista;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodTypeName!=null) MethodTypeName.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
        if(DesnaZagrada!=null) DesnaZagrada.accept(visitor);
        if(VarDeclLista!=null) VarDeclLista.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodTypeName!=null) MethodTypeName.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
        if(DesnaZagrada!=null) DesnaZagrada.traverseTopDown(visitor);
        if(VarDeclLista!=null) VarDeclLista.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodTypeName!=null) MethodTypeName.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        if(DesnaZagrada!=null) DesnaZagrada.traverseBottomUp(visitor);
        if(VarDeclLista!=null) VarDeclLista.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclarationForm(\n");

        if(MethodTypeName!=null)
            buffer.append(MethodTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesnaZagrada!=null)
            buffer.append(DesnaZagrada.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclLista!=null)
            buffer.append(VarDeclLista.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclarationForm]");
        return buffer.toString();
    }
}
