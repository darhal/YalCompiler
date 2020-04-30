package yal.arbre.expressions.binary.arithmetic;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.arbre.expressions.binary.BinaryOperation;

public class ArithmeticOperation extends BinaryOperation {
    public ArithmeticOperation(Expression e1, Expression e2, OperatorsTypes op, int n) {
        super(e1, e2, op, ExpressionType.ARITHMETIC, n);
    }
}
