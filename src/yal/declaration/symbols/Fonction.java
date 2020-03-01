package yal.declaration.symbols;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.ExpressionType;
import yal.declaration.entries.Entry;

public class Fonction extends ArbreAbstrait
{
    protected ArbreAbstrait inst;
    protected Entry entree;
    protected ExpressionType returnType;

    public Fonction(Entry entree, ArbreAbstrait inst, ExpressionType type, int ligne)
    {
        super(ligne);
        this.inst = inst;
        this.entree = entree;
        this.returnType = type;
    }

    public ExpressionType getReturnType() {
        return returnType;
    }

    @Override
    public void verifier() {
        inst.verifier();
    }

    @Override
    public String toMIPS() {
        String mips = "\t# Declaration of function: "+ entree.getIdentifier()+"\n";
        mips += entree.getIdentifier()+":\n";
        mips += "\t# Pushing in the function environments (Creating the stack frame)\n";
        mips += "\tmove $s2, $sp\n";
        mips += "\taddi $sp, $sp, -4\n";
        mips += "\tsw $ra, 0($sp)\n"; // Function enviroment
        mips += inst.toMIPS();
        mips += "\t# Popping out the function environments (Popping the stack frame)\n";
        mips += "\tlw $ra, 0($sp)\n";
        mips += "\tmove $sp, $s2\n";
        mips += "\tjr $ra\n"; // Go back from where we finish
        return mips;
    }
}
