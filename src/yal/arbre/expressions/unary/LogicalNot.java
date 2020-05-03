package yal.arbre.expressions.unary;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;

/**
 * Class LogicalNot
 */
public class LogicalNot extends UnaryOperation
{
    public LogicalNot(Expression e, int n) {
        super(e, OperatorsTypes.NOT, ExpressionType.LOGIC, n);
    }

 /**
 * Fonction toMips pour générer le code toMips
 * @return
 */
    @Override
    public String toMIPS() {
        String mips =  super.toMIPS();
        mips += "\tseq $v0, $zero, $v0\n"; // Non
        return mips;
    }
}
