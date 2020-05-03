package yal.arbre.expressions.binary.comparison;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.binary.BinaryOperation;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;

/**
 * Class Less
 */
public class Less extends ComparisonOperation {
    public Less(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.LESS, n);
    }


    /**
     * Fonction toMips pour générer le code toMips avec Less qui est la comparaison  "plus petit"
     * @return
     */
    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Less:\n";
        mips += "\tslt $v0, $t8, $v0\n"; // Less then ($v0 is set to 1 if $t8 < $v0, 0 otherwise)
        return mips;
    }
}
