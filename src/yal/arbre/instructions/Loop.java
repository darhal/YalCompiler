package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;

public class Loop extends Instruction
{
    protected ArbreAbstrait inst;
    protected Expression exp;

    public Loop(Expression exp, ArbreAbstrait inst, int n)
    {
        super(n);
        this.inst = inst;
        this.exp = exp;
    }

    public void verifier()
    {
        exp.verifier();
        inst.verifier();
    }

    public String toMIPS()
    {
        int random = (int)(Math.random() * 1000 + 1);
        String start = "start_loop_"+random;
        String end = "end_loop_"+random;
        String mips = "";
        mips += exp.toMIPS();
        mips += "\t# Branch Instruction:\n";
        mips += "\tbeq $v0, $zero, "+end+"\n";
        mips += start+":\n";
        mips += "\t# Loop instructions:";
        mips += inst.toMIPS();
        mips += "\tbne $v0, $zero, "+start+"\n";
        mips += end+":\n";
        return mips;
    }
}
