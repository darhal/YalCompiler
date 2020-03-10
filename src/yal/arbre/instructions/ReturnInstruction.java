package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.declaration.TDS;
import yal.declaration.entries.FonctionEntry;

public class ReturnInstruction extends Instruction
{
    protected Expression exp;
    protected int fnBloc;

    public ReturnInstruction(Expression exp, int noLigne)
    {
        super(noLigne);
        this.exp = exp;
        this.fnBloc = -1;
    }

    public void setFnBloc(int bloc)
    {
        this.fnBloc = bloc;
    }

    @Override
    public void verifier() {
        exp.verifier();
        if (fnBloc == -1){ // return dans main function
            //TODO: throw an exception
        }
    }

    @Override
    public String toMIPS() {
        FonctionEntry entry = TDS.Instance().getBloc(fnBloc);
        String mips = exp.toMIPS();
        mips += "\tj "+entry.getIdentifier()+"_fin\n"; // Go back from where we finish
        return mips;
    }
}
