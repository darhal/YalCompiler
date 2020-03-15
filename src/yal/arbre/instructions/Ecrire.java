package yal.arbre.instructions;

import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
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
    }

    @Override
    public String toMIPS() {
        int random = hashCode(); // Unique
        String wtrue_lbl = "wtrue_"+random;
        String wfalse_lbl = "wfalse_"+random;
        String skip_lbl = "wend_"+random;
        String mips = "";

        if (exp.getType() == ExpressionType.ARITHMETIC) {
            return mips+exp.toMIPS() +
                    "\n\t# Call write sys call:​\n" +
                    "\tmove $a0, $v0\n" +
                    "\tli $v0, 1\n" +
                    "\tsyscall\n" +
                    "\t# Return to line:\n" +
                    "\tli $v0, 11 \t# Syscall code for printing one char\n" +
                    "\tli $a0, '\\n' \t# print new line char\n" +
                    "\tsyscall\n";
        }else if (exp.getType() == ExpressionType.LOGIC){
            return mips+exp.toMIPS() +
                    "\n\t # Evalue if the expression is true or false\n"+
                    "\tbeq $v0, $zero, "+wfalse_lbl+"\n"+
                    wtrue_lbl+":\n"+
                    "\tla $a0, true_str\n" +
                    "\tli $v0, 4\n" +
                    "\tsyscall\n"+
                    "\tj "+skip_lbl+"\n"+
                    wfalse_lbl+":\n"+
                    "\tla $a0, false_str\n" +
                    "\tli $v0, 4\n" +
                    "\tsyscall\n"+
                    skip_lbl+":\n"
                    ;
        }else{
            return mips+exp.toMIPS() +
                    "\n\t# Call write sys call:​\n" +
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
