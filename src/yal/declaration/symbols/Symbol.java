package yal.declaration.symbols;

public abstract class Symbol
{
    protected int offset;

    protected Symbol()
    {
        this.offset = 0;
    }

    public int getOffset()
    {
        return offset;
    }

    public void incrementOffset(int incr)
    {
        offset = offset + incr;
    }


}
