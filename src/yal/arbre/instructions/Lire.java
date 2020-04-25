package yal.arbre.instructions;

import yal.arbre.expressions.ArrayElement;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.Variable;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.InvalidArgumentException;

public class Lire extends Instruction
{
    private Variable idf;

    public Lire(Variable idf, int n)
    {
        super(n);
        this.idf = idf;
    }

    @Override
    public void verifier()
    {
        idf.verifier();

        // Verify that we dont read into an array
        Entry e = idf.getEntree();
        Symbole s = TDS.Instance().Identify(e);

        if (s.getType() == Decltype.ARRAY && idf.getVariableType() == Expression.VariableType.IDENTIFIANT){
            throw new InvalidArgumentException(noLigne,
                    "Invalid argument supplied to the function 'lire', expected a variable but an array has been given"
            );
        }
    }

    @Override
    public String toMIPS()
    {
        Symbole s = TDS.Instance().Identify(idf.getEntree());
        int offset = -4 * s.getOffset();
        String mips = "";

        if (s.getType() == Decltype.ARRAY) {
            ArrayElement ae = (ArrayElement)idf;
            mips += "\n\t# Reads an integer:\n" +
                    ae.getExpression().toMIPS() +
                    "\tmove $a0, $v0\n" +
                    "\t# Get address of the array element variable '"+idf.getEntree().getIdentifier()+"':\n" +
                    "\tli $t2, "+s.getNoBloc()+"\n" +
                    "\tjal search_var\n" +
                    "\tlw $v0, "+offset+"($t1)\n" +
                    "\tjal get_arr_element_address\n" +
                    "\tmove $t2, $v0\n" +
                    "\t# Start the read syscall:\n"+
                    "\tli $v0, 5\n" +
                    "\tsyscall\n" +
                    "\tsw $v0, ($t2)\n";
        }else {
            mips += "\n\t# Reads an integer:\n" +
                    "\tli $v0, 5\n" +
                    "\tsyscall\n" +
                    "\t# Get address of the variable '"+idf.getEntree().getIdentifier()+"':\n" +
                    "\tli $t2, "+s.getNoBloc()+"\n" +
                    "\tjal search_var\n" +
                    "\tsw $v0, "+offset+"($t1)\n";
        }


        return mips;
    }
}
