package yal.arbre.expressions;

import yal.analyse.CodesLexicaux;
import yal.declaration.TDS;

public class BinaryOperation extends Expression
{
    protected Expression exp1;
    protected Expression exp2;
    protected int op;

    public BinaryOperation(Expression e1, Expression e2, int op, int n)
    {
        super(n);
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = op;
    }

    @Override
    public void verifier() {
        exp1.verifier();
        exp2.verifier();
    }

    @Override
    public String toMIPS() {
        int local_offset = TDS.Instance().getVariableZoneSize() * -4;
        String mips = exp1.toMIPS();
        mips += "\taddi $sp, $sp, -4\n"; // Reserve stack
        mips += "\tsw $v0, "+ local_offset +"($sp)\n";
        mips += exp2.toMIPS(); // exp2 is in v0
        mips += "\tlw $t8, "+ local_offset +"($sp)\n"; // load exp1 to t8
        mips += "\taddi $sp, $sp, 4\n"; // Clear stack

        // t8 and v0 now contain exp1 and exp2 respectively
        switch(op){
            case CodesLexicaux.OP_PLUS:
                mips += "\tadd $v0, $t8, $v0\n"; // Addition
                break;
            case CodesLexicaux.OP_MINUS:
                mips += "\tsub $v0, $t8, $v0\n"; // Subtraction
                break;
            case CodesLexicaux.OP_MULTIPLY:
                mips += "\tmul $v0, $t8, $v0\n"; // Multiplication (No overflow!)
                break;
            case CodesLexicaux.OP_DIVIDE:
                mips += "\tdiv $t8, $v0\n"; // Division
                mips += "\tmflo $v0\n"; // Get quotient
                break;
            case CodesLexicaux.OP_GREATER:
                // TODO: Implement this (Use not less and not equal)
                /*
                mips += "\tslt $v0, $t8, $v0\n"; // Less then ($v0 is set to 1 if $t8 < $v0, 0 otherwise)
                mips += "\txori $v0, $v0, 1\n"; // Flip $v0 using xori (Now its greater or equal)
                */
                break;
            case CodesLexicaux.OP_LESS:
                mips += "\tslt $v0, $t8, $v0\n"; // Less then ($v0 is set to 1 if $t8 < $v0, 0 otherwise)
                break;
            case CodesLexicaux.OP_EQUAL:
                mips += "\tsub $v0, $t8, $v0\n"; // Simulate equal operation with subtraction (0 if $t8 == $v0, 1 otherwise)
                mips += "\txori $v0, $v0, 1\n"; // Flip $v0 using xori
                break;
            case CodesLexicaux.OP_NEQUAL:
                mips += "\tsub $v0, $t8, $v0\n"; // Simulate not equal operation with subtraction (0 if $t8 == $v0, 1 otherwise)
                break;
            case CodesLexicaux.OP_AND:
                mips += "\tand $v0, $t8, $v0\n"; // AND
                break;
            case CodesLexicaux.OP_OR:
                mips += "\tor $v0, $t8, $v0\n"; // OR
                break;
            default:
                break;
        }

        return mips;
    }
}
