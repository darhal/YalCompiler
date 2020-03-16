package yal.declaration.symbols;

import yal.declaration.Decltype;

public abstract class Symbole
{
    protected int offset;
    protected int noBloc;
    protected Decltype type;
    protected boolean isParam;

    protected Symbole()
    {
        this.offset = 0;
        this.noBloc = 0;
        type = Decltype.VARIABLE;
        isParam = false;
    }

    protected Symbole(Decltype type)
    {
        this.offset = 0;
        this.noBloc = 0;
        this.type = type;
        isParam = false;
    }

    public int getNoBloc() {
        return noBloc;
    }

    public boolean isParam() {
        return isParam;
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
