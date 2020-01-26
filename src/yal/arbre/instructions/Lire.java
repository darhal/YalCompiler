package yal.arbre.instructions;

import yal.arbre.expressions.Identifiant;
import yal.arbre.expressions.Variable;
import yal.declaration.TDS;
import yal.declaration.symbols.Symbol;

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
        Symbol s = TDS.Instance().Identify(idf.getEntree());
        int offset = -4 * s.getOffset();

        String mips = "# Reads an integer: ";
        mips += "\n\tli $v0, 5\n";
        mips += "\tsyscall\n";
        mips += "\tsw $v0, "+offset+"($sp)\n";
        return mips;
    }
}
