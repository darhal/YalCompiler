package yal.declaration.entries;

import yal.declaration.Decltype;

public class FonctionEntry extends Entry
{
    public FonctionEntry(String idf, int line)
    {
        super(idf, Decltype.FUNCTION, line);
    }
}
