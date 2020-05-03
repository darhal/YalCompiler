package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.arbre.expressions.Variable;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Class ReturnInstruction
 */
public class ReturnInstruction extends Instruction
{
    protected Expression exp;
    protected int fnBloc;

    public ReturnInstruction(Expression exp, int fnBloc, int noLigne)
    {
        super(noLigne);
        this.exp = exp;
        this.fnBloc = fnBloc;
    }

    @Override
    public void verifier() {
        exp.verifier();

        if (fnBloc == 0){ // return dans main function
            throw new AnalyseSemantiqueException(this.noLigne, "la déclaration de retour ne peut pas être utilisée dans le programme principal.");
        }

        if (exp.getType() != ExpressionType.ARITHMETIC){
            throw new AnalyseSemantiqueException(this.noLigne, "le type de retour doit être une expression arithmétique.");
        }

        // verify that the return expression is not an array (idf is an array)!
        if (exp.getVariableType() == Expression.VariableType.IDENTIFIANT){
            Variable var = (Variable)exp;
            Entry e = var.getEntree();
            Symbole s = TDS.Instance().Identify(e);

            if (s.getType() == Decltype.ARRAY){
                throw new AnalyseSemantiqueException(this.noLigne, "le type de retour doit être un 'entier' et ne peut pas être un tableau.");
            }
        }
    }

    public ExpressionType getReturnType() {
        return exp.getType();
    }

    /**
     * Fonction toMips pour générer le code toMips
     * @return
     */
    @Override
    public String toMIPS() {
        FonctionEntry entry = TDS.Instance().getBloc(fnBloc).getFnEntry();
        String mips = exp.toMIPS();
        mips += "\tj "+entry.getIdentifier()+"_fin\n"; // Go back from where we finish
        return mips;
    }

    public boolean isReturnStatment() { return true; }
}
