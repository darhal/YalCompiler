package yal.arbre.expressions.unary;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;

public class UnaryMinus extends UnaryOperation {
    public UnaryMinus(Expression e, int n) {
        super(e, OperatorsTypes.MINUS, ExpressionType.ARITHMETIC, n);
    }

    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        mips += "\tsub $v0, $zero, $v0\n"; // -
        return mips;
    }
}
