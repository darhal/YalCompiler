package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.declaration.TDS;
import yal.declaration.entries.FonctionEntry;
import yal.exceptions.AnalyseSemantiqueException;

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

        if (fnBloc == -1){ // return dans main function
            throw new AnalyseSemantiqueException(this.noLigne, "return statement is being used in the main program");
        }

        if (exp.getType() != ExpressionType.ARITHMETIC){
            throw new AnalyseSemantiqueException(this.noLigne, "return type must be an arithmetic expression");
        }
    }

    public ExpressionType getReturnType() {
        return exp.getType();
    }

    @Override
    public String toMIPS() {
        FonctionEntry entry = TDS.Instance().getBloc(fnBloc);
        String mips = exp.toMIPS();
        mips += "\tj "+entry.getIdentifier()+"_fin\n"; // Go back from where we finish
        return mips;
    }
}
