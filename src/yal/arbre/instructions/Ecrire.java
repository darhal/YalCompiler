package yal.arbre.instructions;

import yal.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS() +
        "\n\t# Call write sys call:​\n"+
        "\tmove $a0, $v0\n"+
        "\tli $v0, 1\n"+
        "\tsyscall\n"+
        "\t# Return to line:\n"+
        "\tli $v0, 11 \t# Syscall code for printing one char\n"+
        "\tli $a0, '\\n' \t# print new line char\n" +
        "\tsyscall\n";
    }

    public Expression getExpression() {
        return exp;
    }

}
