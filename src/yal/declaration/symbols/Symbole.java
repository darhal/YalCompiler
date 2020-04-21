package yal.declaration.symbols;

import yal.declaration.Decltype;

public abstract class Symbole
{
    protected int offset;
    protected int noBloc;
    protected Decltype type;

    protected Symbole()
    {
        this.offset = 0;
        this.noBloc = 0;
        type = Decltype.VARIABLE;
    }

    protected Symbole(Decltype type)
    {
        this.offset = 0;
        this.noBloc = 0;
        this.type = type;
    }

    public int getNoBloc() {
        return noBloc;
    }

    public boolean isVariable() {
        return type == Decltype.VARIABLE;
    }

    public boolean isParam() {
        return type == Decltype.PARAMETER;
    }

    public boolean isArray() {
        return type == Decltype.ARRAY;
    }

    public boolean isFunction() {
        return type == Decltype.FUNCTION;
    }

    public void setNoBloc(int noBloc) {
        this.noBloc = noBloc;
    }

    public int getOffset()
    {
        return offset;
    }

    public void setOffset(int incr)
    {
        offset = incr;
    }

    public Decltype getType() {
        return type;
    }
}
