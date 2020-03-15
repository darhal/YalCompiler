package yal.declaration.symbols;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.ExpressionType;
import yal.arbre.instructions.ReturnInstruction;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.exceptions.AnalyseSemantiqueException;

public class Fonction extends ArbreAbstrait
{
    protected BlocDInstructions inst;
    protected FonctionEntry entree;
    protected ExpressionType returnType;
    protected int nbParameters;
    protected int noBloc;

    public Fonction(int nbBloc, FonctionEntry entree, BlocDInstructions inst, Integer nbParameters, ExpressionType type, int ligne)
    {
        super(ligne);
        this.inst = inst;
        this.entree = entree;
        this.returnType = type;
        this.noBloc = nbBloc;
        this.nbParameters = nbParameters;
    }

    public ExpressionType getReturnType() {
        return returnType;
    }

    @Override
    public void verifier() {
        TDS.Instance().EnterBloc(noBloc);
        if (entree.getNbReturn() == 0){
            throw new AnalyseSemantiqueException(entree.getLine(),
                    "Functions must have at least one return statement, the function '"+entree.getIdentifier()+"' doesn't have any return statement"
            );
        }
        inst.verifier();
        TDS.Instance().LeaveBloc();
    }

    @Override
    public String toMIPS() {
        TDS.Instance().EnterBloc(noBloc);
        int param_sz = nbParameters;
        int func_env = (param_sz+1+1)*4;
        String mips = "\t# Declaration of function: "+ entree.getIdentifier()+"\n";
        mips += entree.getIdentifier()+":\n";
        mips += "\t# Pushing in the function environments (Creating the stack frame)\n";
        mips += "\taddi $sp, $sp, -"+func_env+"\n"; // param, adr retour, no region
        mips += "\tmove $s2, $sp\n";
        // Function enviroment
        mips += "\tsw $ra, "+4*2+"($s2)\n";
        mips += inst.toMIPS();
        mips += "\t# End of the function routine :\n";
        mips += entree.getIdentifier()+"_fin:\n";
        mips += "\t# Popping out the function environments (Popping the stack frame)\n";
        mips += "\tlw $ra, "+4*2+"($sp)\n";
        mips += "\taddi $sp, $sp, "+func_env*-4+"\n";
        // mips += "\tmove $sp, $s2\n";
        mips += "\tjr $ra\n"; // Go back from where we finish
        TDS.Instance().LeaveBloc();
        return mips;
    }
}
