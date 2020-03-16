package yal.declaration.entries;

import yal.declaration.Decltype;

import java.util.Objects;

public class FonctionEntry extends Entry
{
    public int nbReturn;
    public int nbParam;

    public FonctionEntry()
    {
        super("N/A", Decltype.FUNCTION, 0);
        this.nbReturn = 0;
        this.nbParam = 0;
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

    public void setNbParam(int nb) { nbParam = nb; }

    public int getNbParam() {
        return nbParam;
    }

    @Override
    public String getIdentifier()
    {
        return idf+"_p"+nbParam;
    }

    public String getFunctionName()
    {
        String type = "entier,";
        type = type.repeat(nbParam);
        type = type.substring(0, type.length() - 1);
        return idf+"("+type+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FonctionEntry that = (FonctionEntry) o;
        return nbParam == that.nbParam;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nbParam);
    }
}
