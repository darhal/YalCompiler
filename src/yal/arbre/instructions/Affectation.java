package yal.arbre.instructions;

import yal.arbre.expressions.*;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class Affectation extends Instruction
{
    protected Identifiant idf ;
    protected Expression exp;

    public Affectation(Identifiant idf, Expression exp, int n) {
        super(n) ;
        this.idf = idf;
        this.exp = exp;
    }

    @Override
    public void verifier() {
        // Verify that entry is declared
        if (((Variable)idf) == null){
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
        Entry entree = ((Variable)idf).getEntree();
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();
        mips += "\n\t# Assignement for the variable '"+entree.getIdentifier()+"':\n"+
                "\tsw $v0, "+offset+"($s7)\n";

        return mips;
    }

    public Expression getExpression() {
        return exp;
    }
}
