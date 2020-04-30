package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.arbre.expressions.Variable;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.InvalidArgumentException;

public class Ecrire extends Instruction
{
    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        exp.verifier();

        if (exp.getType() == ExpressionType.FUNCTION){
            throw new InvalidArgumentException(noLigne,
                    "Invalid argument supplied to the function 'ecrire', " +
                    "expected an arithmetic or logic expression while function call has been given with no return value"
            );
        }

        // verify its not a table idf either
        if (exp.getVariableType() == Expression.VariableType.IDENTIFIANT){
            Variable var = (Variable)exp;
            Entry e = var.getEntree();
            Symbole s = TDS.Instance().Identify(e);

            if (s.getType() == Decltype.ARRAY){
                throw new InvalidArgumentException(noLigne,
                        "Invalid argument supplied to the function 'ecrire', " +
                        "expected an arithmetic or logic expression an array has been given"
                );
            }
        }
    }

    @Override
    public String toMIPS() {
        String mips = "";

        if (exp.getType() == ExpressionType.ARITHMETIC) {
            return  mips + exp.toMIPS() +
                    "\n\t# Call write sys call:\n" +
                    "\tmove $a0, $v0\n" +
                    "\tli $v0, 1\n" +
                    "\tsyscall\n" +
                    "\t# Return to line:\n" +
                    "\tli $v0, 11 \t# Syscall code for printing one char\n" +
                    "\tli $a0, '\\n' \t# print new line char\n" +
                    "\tsyscall\n";
        } else if (exp.getType() == ExpressionType.LOGIC) {
            return  mips + exp.toMIPS() +
                    "\n\t # Call write routine for logical expressions\n"+
                    "\tjal write_logical\n"
                    ;
        } else {
            return mips+exp.toMIPS() +
                    "\n\t# Call write sys call:\n" +
                    "\tmove $a0, $v0\n" +
                    "\tli $v0, 1\n" +
                    "\tsyscall\n" +
                    "\t# Return to line:\n" +
                    "\tli $v0, 11 \t# Syscall code for printing one char\n" +
                    "\tli $a0, '\\n' \t# print new line char\n" +
                    "\tsyscall\n";
        }
    }

    public Expression getExpression() {
        return exp;
    }

}
