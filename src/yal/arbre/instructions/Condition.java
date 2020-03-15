package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;

public class Condition extends Instruction
{
    protected ArbreAbstrait inst;
    protected ArbreAbstrait elseInst;
    protected Expression exp;

    public Condition(Expression exp, ArbreAbstrait inst, ArbreAbstrait else_inst, int n)
    {
        super(n);
        this.inst = inst;
        this.exp = exp;
        this.elseInst = else_inst;
    }

    public void verifier()
    {
        exp.verifier();
        inst.verifier();

        if (elseInst != null){
            elseInst.verifier();
        }
    }

    public String toMIPS()
    {
        String mips = "\n\t# Condition Instruction:";
        int random = (int)(Math.random() * 10000 + 1);
        String label_name = "sinon_"+random;
        String end_si = "fsi_"+random;
        mips += exp.toMIPS();
        // Wrap these a round a condition
        mips += "\tbeq $v0, $zero, "+label_name+"\t # Skip the condition if v0 is equal to zero\n";
        mips += "\t# Begin of the instruction inside SI branch:";
        mips += inst.toMIPS()+"\n";
        mips += "\tj "+end_si+"\n\n";
        mips += label_name+":\n";

        if (elseInst != null) {
            mips += "\t# Begin of the SINON branch\n";
            mips += elseInst.toMIPS();
        }

        mips += "\t# End of the branch\n";
        mips += end_si+":\n";
        return mips;
    }
}
