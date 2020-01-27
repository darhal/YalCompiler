package yal.arbre.expressions;

import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.IntSymbol;
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
        int decalage = -4 * s.getOffset();
        StringBuilder sb = new StringBuilder();
        sb.append("# charge la variable " + nom + "\n");
        sb.append("move $t8, $s7 \n");
        sb.append(itr + ": \n");
        sb.append("lw $v0, 4($t8) \n");
        sb.append("addi $v1, $zero, " + noBloc + " \n");
        sb.append("sub $v0, $v0, $v1\n");
        sb.append("beqz $v0, fin" + itr + "\n");
        sb.append("lw $t8, 8($t8) \n");
        sb.append("j " + itr + " \n");
        sb.append("fin" + itr + ": \n");
        sb.append("lw $v0, " + decalage + "($t8)\n");
        return sb.toString();
    }

    public Entry getEntree() {
        return entree;
    }
}
