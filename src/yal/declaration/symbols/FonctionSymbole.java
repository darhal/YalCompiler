package yal.declaration.symbols;

import yal.arbre.ArbreAbstrait;
import yal.declaration.Decltype;

public class FonctionSymbole extends Symbole
{
    protected Fonction fonction;

    public FonctionSymbole(Fonction fonction)
    {
        super(Decltype.FUNCTION);
        this.fonction = fonction;
    }

    public Fonction getFonction() {
        return fonction;
    }

    @Override
    public void incrementOffset(int incr)
    {
    }
}
