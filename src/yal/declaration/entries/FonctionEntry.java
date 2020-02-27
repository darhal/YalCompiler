package yal.declaration.entries;

public class FonctionEntry extends Entry
{
    public FonctionEntry(String idf, int line)
    {
        super(idf, line);
    }

    public int getEntryType() {
        return 0;
    }
}
