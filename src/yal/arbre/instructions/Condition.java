package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;

public class Condition extends Instruction
{
    protected ArbreAbstrait inst;
    protected Expression exp;

    public Condition(Expression exp, ArbreAbstrait inst, int n)
    {
        super(n);
        this.inst = inst;
        this.exp = exp;
        System.out.println("Condition");
    }

    public void verifier()
    {
        //exp.verifier();
        //inst.verifier();
    }

    public String toMIPS()
    {
        String mips = "\n\t# Condition Instruction:";
        int random = (int)(Math.random() * 1000 + 1);
        String label_name = "si_"+random;
        mips += exp.toMIPS();
        // Wrap these a round a condition
        mips += "\tbne $v0, $zero, "+label_name+"\n";
        mips += "\t"+label_name+":\n";
        mips += inst.toMIPS();
        return mips;
    }
}
