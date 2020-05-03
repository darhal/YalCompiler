package yal.arbre.expressions;

/**
 * Class Abstraite Identifiant
 */
public abstract class Identifiant extends Expression
{
    protected String idf;

    public Identifiant(String idf, int line)
    {
        super(line);
        this.idf = idf;
    }
}
