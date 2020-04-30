package yal.arbre.expressions.binary.logical;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;

public class And extends LogicalOperation {
    public And(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.AND, n);
    }

    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# And:\n";
        mips += "\tand $v0, $t8, $v0\n"; // AND
        return mips;
    }
}
