package yal.arbre.expressions;

import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbol;

public class Variable extends Identifiant
{
    protected Entry entree;

    public Variable(Entry e, int line)
    {
        super(e.getIdentifier(), line);
        this.entree = e;
    }

    @Override
    public void verifier()
    {
        TDS.Instance().Identify(entree);
    }

    @Override
    public String toMIPS()
    {
        String mips = "";
        Symbol s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();
        mips +=
                "\n\t# Get value of the variable '"+entree.getIdentifier()+"':\n"+
                "\tlw $v0, "+offset+"($sp)\n";

        return mips;
    }

    public Entry getEntree() {
        return entree;
    }
}
