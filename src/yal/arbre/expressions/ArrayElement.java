package yal.arbre.expressions;

import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class ArrayElement extends Variable
{
    protected Expression exp;

    public ArrayElement(Entry e, Expression exp, int line)
    {
        super(e, line);
        this.entree = e;
        this.exp = exp;
    }

    @Override
    public void verifier()
    {
        exp.verifier();
        Symbole s = TDS.Instance().Identify(entree);

        if (s.getType() != Decltype.ARRAY){
            throw new AnalyseSemantiqueException(noLigne, "Attempt to access an array element while '"+ entree.getIdentifier()+"' is a not an array.");
        }
    }

    @Override
    public String toMIPS()
    {
        String mips = "";
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();
        mips += exp.toMIPS()+
                "\n\t# Get the address of the array '"+entree.getIdentifier()+"':\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"+
                "\tmove $t1, $v0\n"+
                "\tlw $v0, "+offset+"($s7)\n"+
                "\tjal get_arr_element_value\n"
        ;
        return mips;
    }

    public Expression getExpression() {
        return exp;
    }
}
