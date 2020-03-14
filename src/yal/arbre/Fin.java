package yal.arbre;

import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.FonctionSymbole;
import yal.declaration.symbols.Symbole;

import java.util.Map;

public class Fin extends ArbreAbstrait
{
    public Fin(int n){
        super(n);
    }

    @Override
    public void verifier() {
    }

    @Override
    public String toMIPS() {
        String code_mips =
                "\n\t# Handle program exit\n"+
                "exit:\n"+
                "\tli $v0, 10\n"+
                "\tsyscall\n"+
                "\n\t # Handle RUNTIME errors\n"+
                "div_by_zero:\n"+
                "\tla $a0, div_by0\n" +
                "\tli $v0, 4\n" +
                "\tsyscall\n"+
                "\tj exit\n";

        for (FonctionSymbole fnSymbol : TDS.Instance().fnSymbolMap.values()) {
            code_mips += "\n"+fnSymbol.getFonction().toMIPS()+"\n";
        }

        return code_mips;
    }
}
