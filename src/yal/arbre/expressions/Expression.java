package yal.arbre.expressions;

import yal.arbre.ArbreAbstrait;

/**
 * Class Abstraite Expression
 */
public abstract class Expression extends ArbreAbstrait
{
    public enum VariableType {
        IDENTIFIANT,
        ARRAY_ELEMENT,
        EXPRESSION
    }

    public ExpressionType type;

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

    public VariableType getVariableType() { return VariableType.EXPRESSION; }

    public boolean isConst()
    {
        return false;
    }
}
