package yal.arbre.instructions;

import yal.arbre.BlocDInstructions;
import yal.arbre.expressions.Expression;

public class Loop extends Instruction
{
    protected BlocDInstructions inst;
    protected Expression exp;

    public Loop(Expression exp, BlocDInstructions inst, int n)
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
        String mips = "";
        mips += exp.toMIPS();
        // TODO: Wrap these a round a loop
        mips += "";
        mips += inst.toMIPS();
        return mips;
    }
}
