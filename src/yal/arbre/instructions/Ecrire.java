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
        "\t# ecrire​\n"+
        "\tli $v0, 1\n"+
        "\tlw $a0, -4($sp)\n"+
        "\tsyscall\n"+
        "\taddi $sp, $sp, 4";
    }

    public Expression getExpression() {
        return exp;
    }

}
