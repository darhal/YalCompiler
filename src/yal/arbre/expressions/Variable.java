package yal.arbre.expressions;

import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;

/**
 * Class Variable
 */
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
        Symbole s = TDS.Instance().Identify(entree);
    }

    /**
     * Fonction toMips pour générer le code toMips
     * @return
     */
    @Override
    public String toMIPS()
    {
        String mips = "";
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();
        mips += "\n\t# Get value of the variable '"+entree.getIdentifier()+"':\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"+
                "\tlw $v0, "+offset+"($t1)\n"
                ;
        return mips;
    }

    public Entry getEntree() {
        return entree;
    }

    @Override
    public VariableType getVariableType() { return VariableType.IDENTIFIANT; }
}
