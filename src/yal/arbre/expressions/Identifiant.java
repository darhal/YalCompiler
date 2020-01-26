package yal.arbre.expressions;

public abstract class Identifiant extends Expression
{
    protected String idf;

    public Identifiant(String idf, int line)
    {
        super(line);
        this.idf = idf;
    }
}
