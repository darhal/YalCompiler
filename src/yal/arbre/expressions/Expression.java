package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

public abstract class Expression extends ArbreAbstrait
{
    protected ExpressionType type;

    protected Expression(ExpressionType type, int n) {
        super(n) ;
        this.type = type;
    }

    protected Expression(int n) {
        super(n) ;
        this.type = ExpressionType.ARITHMETIC;
    }

    public ExpressionType getType() {
        return type;
    }
}
