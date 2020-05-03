package yal.arbre.expressions.binary.logical;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;

/**
 * Class Or
 */
public class Or extends LogicalOperation {
    public Or(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.OR, n);
    }

    /**
     * Fonction toMips pour générer le code toMips avec Or
     * @return
     */
    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Or:\n";
        mips += "\tor $v0, $t8, $v0\n"; // OR
        return mips;
    }
}
