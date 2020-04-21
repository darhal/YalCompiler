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
                "\tj exit\n\n"+

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
                "\tjr $ra\t#Resume normal execution\n"+

                "\n\t# Allocate array the array size should be in a0, address is in $v0\n"+
                "\t# returned address will be in a0:\n"+
                "\tallocate_array:\n"+
                "\tli $v0, 9 \t# sbrk syscall\n"+
                "\tsyscall\n \t# Issue the syscall\n"+
                "\t# Initialize the array\n"+
                "\tsw $a0, 0($v0)\n"+
                "\tjr $ra \t#Resume normal execution\n"+

                "\n\t# Get value from an array index should be in $t1, table address is in $v0, value is returned in $v0\n"+
                "get_arr_element_value:\n"+
                "\tlw $t2, 0($v0) \t# Get the size of the array\n"+
                "\tsub $t2, $t2, $t1"+
                "\tblez $t2, print_bound_err\n # Branch on less than or equal to zero (if size - index <= 0) then print error and exit"+
                "\taddi $v0, $v0, 1 \t# Skip first index it have the size\n"+
                "\tadd $v0, $v0, $t1 \t# $v0 now have the exact address of the element we are searching for\n"+
                "\tlw $v0, ($v0)\t # Get the value in the address of $v0 into $v0 register and then return\n"+
                "\tjr $ra \t#Resume normal execution\n"+

                "\n\t# Get address from an array. index should be in $t1, table address is in $v0, element address is returned in $v0\n"+
                "get_arr_element_address:\n"+
                "\tlw $t2, 0($v0) \t# Get the size of the array\n"+
                "\tsub $t2, $t2, $t1"+
                "\tblez $t2, print_bound_err\n # Branch on less than or equal to zero (if size - index <= 0) then print error and exit"+
                "\taddi $v0, $v0, 1 \t# Skip first index it have the size\n"+
                "\tadd $v0, $v0, $t1 \t# $v0 now have the exact address of the element we are searching for\n"+
                "\tjr $ra \t#Resume normal execution\n"+

                "print_bound_err:\n"+
                "\tla $a0, out_of_bound\n" +
                "\tli $v0, 4\n" +
                "\tsyscall\n"+
                "\tj exit\n"
                ;

        for (FonctionSymbole fnSymbol : TDS.Instance().fnSymbolMap.values()) {
            code_mips += "\n"+fnSymbol.getFonction().toMIPS()+"\n";
        }

        return code_mips;
    }
}
