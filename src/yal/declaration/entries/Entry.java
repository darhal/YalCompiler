package yal.declaration.entries;

import yal.declaration.Decltype;

import java.util.Objects;

public abstract class Entry
{
    protected String idf;
    protected int line;
    protected Decltype type;

    protected Entry(String idf, int line)
    {
        this.idf = idf;
        this.line = line;
        this.type = Decltype.VARIABLE;
    }

    protected Entry(String idf, Decltype type, int line)
    {
        this.idf = idf;
        this.line = line;
        this.type = type;
    }

    public String getIdentifier()
    {
        return idf;
    }

    public int getLine() {
        return line;
    }

    public Decltype getDecltype() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return idf.equals(entry.idf) &&
                type == entry.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idf, type);
    }
}
