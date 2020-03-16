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

        for (Symbole s : TDS.Instance().getSymbolMap().values()) {
            if (s.isParam()) {
                s.setOffset((entree.getNbParam() - s.getOffset()) + 3);
                s.setOffset(-s.getOffset());
            }
        }

        TDS.Instance().LeaveBloc();
    }

    @Override
    public String toMIPS() {
        TDS.Instance().EnterBloc(noBloc);
        String fn_idf = entree.getIdentifier();
        int total_sz = TDS.Instance().getVariableZoneSize();
        int total_args_sz = TDS.Instance().getArgsZoneSize();
        int local_var_sz = TDS.Instance().getLocalVarSize();
        int func_env_sz = (1+1+1+total_sz)*4;

        String mips = "\t# Declaration of function: "+ entree.getIdentifier()+"\n";
        mips += fn_idf+":\n";

        mips += "\t# Pushing in the function environments (Creating the stack frame)\n";
        mips += "\t# Return address:\n";
        mips += "\tsw $ra, -"+0+"($sp)\n";
        mips += "\t# Dynamic linking:\n";
        mips += "\tsw $s7, -"+4+"($sp)\n";
        mips += "\t# No bloc:\n";
        mips += "\tli $t8, "+noBloc+"\n";
        mips += "\tsw $t8, -"+8+"($sp)\n";

        mips += "\t# Reserve the space for variables: \n";
        mips += "\taddi $sp, $sp, -"+3*4+"\n"; // adr retour, dynamic linking, no region
        mips += "\tmove $s7, $sp\n";

        if (local_var_sz != 0) {
            mips += "\taddi $sp, $sp, -" + local_var_sz * 4 + "\n"; // adr retour, dynamic linking, no region, local var
        }

        mips += "\t# Function instructions \n";
        mips += inst.toMIPS();

        mips += "\t# End of the function routine :\n";
        mips += fn_idf+"_fin:\n";
        mips += "\t# Popping out the function environments (Popping the stack frame)\n";
        mips += "\taddi $sp, $sp, "+func_env_sz+"\n";
        mips += "\tlw $ra, -"+(0+total_args_sz*4)+"($sp)\n";
        mips += "\tlw $s7, -"+(4+total_args_sz*4)+"($sp)\n";
        mips += "\tjr $ra\n"; // Go back from where we finish

        TDS.Instance().LeaveBloc();
        return mips;
    }
}
