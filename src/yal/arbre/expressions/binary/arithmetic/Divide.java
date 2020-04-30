package yal.arbre.expressions.binary.arithmetic;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;

public class Divide extends ArithmeticOperation {
    public Divide(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.DIVIDE, n);
    }

    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Division:\n";
        mips += "\t# test si on divise par 0\n";
        mips += "\tbeqz $v0, div_by_zero\n";
        mips += "\tdiv $t8, $v0\n"; // Division
        mips += "\tmflo $v0\n"; // Get quotient
        return mips;
    }
}
