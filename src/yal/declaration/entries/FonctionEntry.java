package yal.declaration.entries;

import yal.declaration.Decltype;

public class FonctionEntry extends Entry
{
    public int nbReturn;

    public FonctionEntry()
    {
        super("N/A", Decltype.FUNCTION, 0);
        this.nbReturn = 0;
    }

    public FonctionEntry(String idf, int line)
    {
        super(idf, Decltype.FUNCTION, line);
        this.nbReturn = 0;
    }

    public void incrementNbReturn()
    {
        this.nbReturn += 1;
    }

    public int getNbReturn()
    {
        return this.nbReturn;
    }
}
