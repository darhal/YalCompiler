package yal.arbre;

import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.FonctionSymbole;
import yal.declaration.symbols.Symbole;

import java.util.Map;

public class Debut extends BlocDInstructions
{
    public Debut(int n) {
        super(n);
    }

    @Override
    public void verifier() {
        for (FonctionSymbole fnSymbol : TDS.Instance().fnSymbolMap.values()) {
            fnSymbol.getFonction().verifier();
        }
    }

    @Override
    public String toMIPS()
    {
        String code_mips =
                ".data\n" +
                "\tdiv_by0: .asciiz \"[RUNTIME ERROR]: Division by zero is forbidden.\\n\"\n" +
                "\ttrue_str: .asciiz \"vrai\\n\"\n" +
                "\tfalse_str: .asciiz \"faux\\n\"\n" +
                ".text\n" +
                "\n"+
                "main:\n" +
                "\t# Begin stackframe:\n" +
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
