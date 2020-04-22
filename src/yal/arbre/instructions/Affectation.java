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

        Entry entree = idf.getEntree();
        Symbole s = TDS.Instance().Identify(entree);

        if (s.getType() == Decltype.ARRAY) {
            ArrayElement ae = (ArrayElement) idf;
            ae.verifier();

            if (ae.getExpression().getType() != ExpressionType.ARITHMETIC){
                throw new AnalyseSemantiqueException(ae.getExpression().getNoLigne(), "Index of the array '"+entree.getIdentifier()+"' is a non arithmetic value.");
            }
        }

        idf.verifier();
        // Verify that expression is valid
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        String mips = "";

        Entry entree = idf.getEntree();
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();

        mips += exp.toMIPS();
        mips += "\n\t# Assignement for the variable '"+entree.getIdentifier()+"':\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"
        ;

        if (s.getType() == Decltype.ARRAY) {
            ArrayElement ae = (ArrayElement) idf;
            mips += "\tmove $t3, $v0\n"+
                    ae.getExpression().toMIPS()+
                    "\tmove $a0, $v0\n"+
                    "\tlw $v0, "+offset+"($t1)\n"+
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
