package yal.declaration.entries;

import yal.declaration.Decltype;

public class FonctionEntry extends Entry
{
    public FonctionEntry()
    {
        super("N/A", Decltype.FUNCTION, 0);
    }

    public FonctionEntry(String idf, int line)
    {
        super(idf, Decltype.FUNCTION, line);
    }


}
