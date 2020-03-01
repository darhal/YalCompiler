package yal.arbre.instructions;

import yal.arbre.expressions.Variable;
import yal.declaration.TDS;
import yal.declaration.symbols.Symbole;

public class Lire extends Instruction
{
    private Variable idf;

    public Lire(Variable idf, int n)
    {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier()
    {
        idf.verifier();
    }

    @Override
    public String toMIPS()
    {
        Symbole s = TDS.Instance().Identify(idf.getEntree());
        int offset = -4 * s.getOffset();

        String mips = "\n\t# Reads an integer: ";
        mips += "\n\tli $v0, 5\n";
        mips += "\tsyscall\n";
        mips += "\tsw $v0, "+offset+"($sp)\n";
        return mips;
    }
}
