package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.declaration.TDS;
import yal.declaration.entries.FonctionEntry;

public class FunctionCall extends Expression
{
    private FonctionEntry entree;

    public FunctionCall(FonctionEntry entree, int ligne)
    {
        super(ExpressionType.FUNCTION, ligne);
        this.entree = entree;
    }

    @Override
    public void verifier() {
        TDS.Instance().Identify(entree);
    }

    @Override
    public String toMIPS() {
        String mips = "";
        mips += "\n\tjal "+entree.getIdentifier()+"\n";
        return mips;
    }
}
