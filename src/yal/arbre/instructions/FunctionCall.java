package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.Fonction;
import yal.declaration.symbols.FonctionSymbole;
import yal.declaration.symbols.Symbole;
import yal.exceptions.IdentifiantNonDeclarerException;

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
        Symbole s = TDS.Instance().IdentifyFunction(entree);

        if (s.getType() == Decltype.FUNCTION){
            FonctionSymbole fs = (FonctionSymbole)s;
            Fonction fn = fs.getFonction();
            this.type = fn.getReturnType();
        }else{
            throw new IdentifiantNonDeclarerException(noLigne);
        }
    }

    @Override
    public String toMIPS() {
        String mips = "";
        mips += "\n\tjal "+entree.getIdentifier()+"\n";
        return mips;
    }
}
