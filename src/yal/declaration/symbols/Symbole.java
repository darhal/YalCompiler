package yal.declaration.symbols;

import yal.declaration.Decltype;

public abstract class Symbole
{
    protected int offset;
    protected Decltype type;

    protected Symbole()
    {
        this.offset = 0;
        type = Decltype.VARIABLE;
    }

    protected Symbole(Decltype type)
    {
        this.offset = 0;
        this.type = type;
    }

    public int getOffset()
    {
        return offset;
    }

    public void incrementOffset(int incr)
    {
        offset = offset + incr;
    }

    public Decltype getType() {
        return type;
    }
}
