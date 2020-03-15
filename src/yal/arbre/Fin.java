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
                "\tj exit\n"+
                "\t# Sub routine to find variables outside of the local scope:\n"+
                "\t# $t1: temp base local | $t2: wanted bloc id | $s0: temp bloc id\n"+
                "search_var:\n"+
                "\tmove $t1, $s7\n"+
                "\tsearch_loop:\n"+
                "\tlw $s0, 4($t1)\n"+
                "\tsub $s0, $s0, $t2\n"+
                "\tbeqz $s0, search_loop_end\n"+
                "\tlw $t1, 8($t1)\n"+
                "\tj search_loop\n"+
                "search_loop_end:\n"+
                "\tjr $ra\t#Resume normal execution\n"
                ;

        for (FonctionSymbole fnSymbol : TDS.Instance().fnSymbolMap.values()) {
            code_mips += "\n"+fnSymbol.getFonction().toMIPS()+"\n";
        }

        return code_mips;
    }
}
