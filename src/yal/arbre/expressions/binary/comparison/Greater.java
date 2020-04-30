package yal.arbre.expressions.binary.comparison;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;

public class Greater extends ComparisonOperation {
    public Greater(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.GREATER, n);
    }

    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Greater\n";
        mips += "\tslt $v0, $v0, $t8\n"; // Greater then ($v0 is set to 1 if $t8 > $v0, 0 otherwise)
        return mips;
    }
}
