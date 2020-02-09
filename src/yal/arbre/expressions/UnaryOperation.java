package yal.arbre.expressions;

import yal.analyse.CodesLexicaux;

public class UnaryOperation extends Expression
{
    protected Expression exp;
    protected int op;

    public UnaryOperation(Expression e, int op, int n)
    {
        super(n);
        this.exp = e;
        this.op = op;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        String mips = exp.toMIPS();

        // Do the unary operation:
        switch(op){
            case CodesLexicaux.OP_NOT:
                mips += "\txori $v0, $v0, -1\n"; // Non
                break;
            case CodesLexicaux.OP_MINUS:
                mips += "\tsub $v0, $zero, $v0\n"; // -
                break;
            default:
                break;
        }

        return mips;
    }
}
