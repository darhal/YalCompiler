package yal.declaration.entries;

import java.util.Objects;

public abstract class Entry
{
    protected String idf;

    protected Entry(String idf)
    {
        this.idf = idf;
    }

    public String getIdentifier()
    {
        return idf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(idf, entry.idf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idf);
    }
}
