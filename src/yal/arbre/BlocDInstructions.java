package yal.arbre;

import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Expression;
import yal.arbre.instructions.Ecrire;
import yal.declaration.TDS;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        programme.add(a) ;
    }
    
    @Override
    public String toString() {
        return programme.toString() ;
    }

    @Override
    public void verifier()
    {
        for (ArbreAbstrait a : programme) {
            a.verifier();
        }
    }
    
    @Override
    public String toMIPS()
    {
        String code_mips =
                ".data\n" +
                ".text\n" +
                "\n" +
                "main:\n"+
                "\t# Begin stackframe:\n"+
                "\tmove $s7, $sp\n";

        if (TDS.Instance().getVariableZoneSize() != 0) {
            code_mips +=
                    "\t# Allocate for the declared variables:\n" +
                    "\taddi $sp, $sp, " + -4 * TDS.Instance().getVariableZoneSize() + "\n"
            ;
        }

        for (ArbreAbstrait a : programme) {
            code_mips += a.toMIPS();
        }

        return code_mips;
    }
}
