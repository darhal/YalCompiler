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

    public FonctionSymbole()
    {
        super(Decltype.FUNCTION);
        this.fonction = null;
    }

    public void setFonction(Fonction fn) {
        fonction = fn;
    }

    public Fonction getFonction() {
        return fonction;
    }

    @Override
    public void setOffset(int incr)
    {
    }
}
