package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.Fonction;
import yal.declaration.symbols.FonctionSymbole;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.IdentifiantNonDeclarerException;

import java.util.ArrayList;

public class FunctionCall extends Expression
{
    private FonctionEntry entree;
    private ArrayList<Expression> args;

    public FunctionCall(FonctionEntry entree, int ligne)
    {
        super(ExpressionType.FUNCTION, ligne);
        this.entree = entree;
        this.args = new ArrayList<>();
        this.entree.setNbParam(this.args.size());
    }

    public FunctionCall(FonctionEntry entree, ArrayList<Expression> args, int ligne)
    {
        super(ExpressionType.FUNCTION, ligne);
        this.entree = entree;
        this.args = args;
        this.entree.setNbParam(this.args.size());
    }

    @Override
    public void verifier() {
        FonctionSymbole s = TDS.Instance().IdentifyFunction(entree);

        if (s.getType() == Decltype.FUNCTION){
            FonctionSymbole fs = s;
            Fonction fn = fs.getFonction();
            this.type = fn.getReturnType();
        }else{
            throw new IdentifiantNonDeclarerException(noLigne, entree.getFunctionName());
        }

        for (Expression e : args){
            e.verifier();

            if (e.getType() != ExpressionType.ARITHMETIC){
                throw new AnalyseSemantiqueException(e.getNoLigne(), entree.getFunctionName()+" arguments must be of the type 'entier'");
            }
        }
    }

    @Override
    public String toMIPS() {
        String mips = "";

        int i = 0;
        for (Expression e : args) {
            mips += e.toMIPS();
            int offset = i*4;
            mips += "\tsw $v0, -"+offset+"($sp)\n";
            i++;
        }
        mips += "\taddi $sp, $sp, -"+i * 4+"\n";

        mips += "\n\tjal "+entree.getIdentifier()+"\n";
        return mips;
    }
}
