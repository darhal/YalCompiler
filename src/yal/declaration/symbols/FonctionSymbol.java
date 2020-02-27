package yal.declaration.symbols;

import yal.arbre.ArbreAbstrait;

public class FonctionSymbol extends Symbol
{
    protected Fonction fonction;

    public FonctionSymbol(Fonction fonction)
    {
        super();
        this.fonction = fonction;
    }

    public ArbreAbstrait getFonction() {
        return fonction;
    }

    @Override
    public void incrementOffset(int incr)
    {
    }
}
