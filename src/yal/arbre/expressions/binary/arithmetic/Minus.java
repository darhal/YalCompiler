package yal.arbre.expressions.binary.arithmetic;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;

public class Minus extends ArithmeticOperation {
    public Minus(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.MINUS, n);
    }

    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Substraction:\n";
        mips += "\tsub $v0, $t8, $v0\n"; // Subtraction
        return mips;
    }
}
