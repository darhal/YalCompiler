package yal.arbre.expressions;

import yal.analyse.CodesLexicaux;
import yal.arbre.OperatorsTypes;
import yal.exceptions.TypeMismatchException;

public class UnaryOperation extends Expression
{
    protected Expression exp;
    protected OperatorsTypes op;

    public UnaryOperation(Expression e, OperatorsTypes op, ExpressionType type, int n)
    {
        super(type, n);
        this.exp = e;
        this.op = op;
    }

    @Override
    public void verifier() {
        exp.verifier();

        if (type == ExpressionType.ARITHMETIC && exp.type != ExpressionType.ARITHMETIC){
            throw new TypeMismatchException(this.getNoLigne(),
                    "Attempt to perform an arithmetic unary operation on non arithmetic expression.");
        }else if (type == ExpressionType.LOGIC && exp.type != ExpressionType.LOGIC){
            throw new TypeMismatchException(this.getNoLigne(),
                    "Attempt to perform a logical unary operation on non logical expression.");
        }
    }

    @Override
    public String toMIPS() {
        String mips = exp.toMIPS();

        // Do the unary operation:
        switch(op){
            case NOT:
                mips += "\tnot $v0, $v0\n"; // Non
                break;
            case MINUS:
                mips += "\tsub $v0, $zero, $v0\n"; // -
                break;
            default:
                break;
        }

        return mips;
    }

    public OperatorsTypes getOp() {
        return op;
    }
}
