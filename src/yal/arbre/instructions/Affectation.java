package yal.arbre.instructions;

import yal.arbre.expressions.*;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class Affectation extends Instruction
{
    protected Variable idf ;
    protected Expression exp;

    public Affectation(Variable idf, Expression exp, int n) {
        super(n) ;
        this.idf = idf;
        this.exp = exp;
    }

    @Override
    public void verifier() {
        // Verify that entry is declared
        if (idf == null){
            throw new AnalyseSemantiqueException(noLigne, "Attempt to affect a non variable identifier to an expression.");
        }

        idf.verifier();
        // Verify that expression is valid
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        String mips = "";

        mips += exp.toMIPS();
        Entry entree = idf.getEntree();
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();

        mips += "\n\t# Assignement for the variable '"+entree.getIdentifier()+"':\n"+
                "\n\t# Get adress of the variable '"+entree.getIdentifier()+"':\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"
        ;

        if (s.getType() == Decltype.ARRAY) {
            ArrayElement ae = (ArrayElement) idf;
            mips += "\tmove $t3, $v0\n"+
                    ae.getExpression().toMIPS()+
                    "\tlw $v0, "+offset+"($t1)\n"+
                    "\tmove $t1, $t3\n"+
                    "\tjal get_arr_element_address\n"+
                    "\tsw $t3, ($v0)\n";
        }else{
            mips += "\tsw $v0, "+offset+"($t1)\n";
        }

        return mips;
    }

    public Expression getExpression() {
        return exp;
    }
}
