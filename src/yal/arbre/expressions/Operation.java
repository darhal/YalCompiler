package yal.arbre.expressions;

import yal.analyse.CodesLexicaux;

public class Operation extends Expression
{
    protected Expression exp1;
    protected Expression exp2;
    protected CodesLexicaux op;

    public Operation(Expression e1, Expression e2, CodesLexicaux op, int n)
    {
        super(n);
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = op;
    }

    @Override
    public void verifier() {
        exp1.verifier();
        exp2.verifier();
    }

    @Override
    public String toMIPS() {
        String mips = "";
        return mips;
    }
}
