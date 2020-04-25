package yal.arbre.instructions;

import yal.arbre.expressions.Variable;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.InvalidArgumentException;

public class Lire extends Instruction
{
    private Variable idf;

    // TODO: We might need reading to an array element
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

        if (s.getType() == Decltype.ARRAY){
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

        String mips = "\n\t# Reads an integer: ";
        mips += "\n\tli $v0, 5\n" +
                "\tsyscall\n" +
                "\n\t# Get adress of the variable '"+idf.getEntree().getIdentifier()+"':\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"+
                "\tsw $v0, "+offset+"($t1)\n"
        ;
        return mips;
    }
}
