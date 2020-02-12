package yal.arbre;

import yal.declaration.TDS;
import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArrayList<ArbreAbstrait> programme ;
    private static boolean FIRST_TIME = true;

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
        String code_mips = "";

        if (FIRST_TIME) {
            code_mips =
                    ".data\n" +
                    "\tdiv_by0: .asciiz \"[RUNTIME ERROR]: Division by zero detected.\"\n" +
                    "\ttrue_str: .asciiz \"vrai\"\n" +
                    "\tfalse_str: .asciiz \"faux\"\n" +
                    ".text\n" +
                    "\n" +
                    "main:\n" +
                    "\t# Begin stackframe:\n" +
                    "\tmove $s7, $sp\n";

            if (TDS.Instance().getVariableZoneSize() != 0) {
                code_mips +=
                        "\t# Allocate for the declared variables:\n" +
                        "\taddi $sp, $sp, " + -4 * TDS.Instance().getVariableZoneSize() + "\n"
                ;
            }

            FIRST_TIME = false;
        }

        for (ArbreAbstrait a : programme) {
            code_mips += a.toMIPS();
        }

        return code_mips;
    }
}
