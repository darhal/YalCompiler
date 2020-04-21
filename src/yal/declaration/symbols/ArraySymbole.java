package yal.declaration.symbols;

import yal.arbre.expressions.Expression;
import yal.declaration.Decltype;

public class ArraySymbole extends Symbole
{
    private Expression exp;

    public ArraySymbole(Expression exp)
    {
        super(Decltype.ARRAY);
        this.exp = exp;
    }

    public Expression getExpression()
    {
        return exp;
    }
}
