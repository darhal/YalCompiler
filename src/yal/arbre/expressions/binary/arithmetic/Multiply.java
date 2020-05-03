package yal.arbre.expressions.binary.arithmetic;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;


/**
 * Class Multiply pour la multiplication
 */
public class Multiply extends ArithmeticOperation {
    public Multiply(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.MULTIPLY, n);
    }

    /**
     * Fonction toMips pour générer le code toMips de la Multiplication
     * @return
     */
    @Override
    public String toMIPS() {
        String mips =  super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Multiplication:\n";
        mips += "\tmul $v0, $t8, $v0\n"; // Multiplication (No overflow!)
        return mips;
    }
}
