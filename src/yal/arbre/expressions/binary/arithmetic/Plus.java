package yal.arbre.expressions.binary.arithmetic;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;

/**
 * Class Plus pour l'addition
 */
public class Plus extends ArithmeticOperation {
    public Plus(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.PLUS, n);
    }

    /**
     * Fonction toMips pour générer le code toMips de l'addition
     * @return
     */
    @Override
    public String toMIPS() {
        String mips =  super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Addition:\n";
        mips += "\tadd $v0, $t8, $v0\n"; // Addition
        return mips;
    }
}
