package yal.arbre.expressions;

import yal.arbre.OperatorsTypes;
import yal.declaration.TDS;
import yal.exceptions.TypeMismatchException;

public class BinaryOperation extends Expression
{
    protected Expression exp1;
    protected Expression exp2;
    protected OperatorsTypes op;

    public BinaryOperation(Expression e1, Expression e2, OperatorsTypes op, ExpressionType type, int n)
    {
        super(type, n);
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = op;
    }

    @Override
    public void verifier() {
        exp1.verifier();
        exp2.verifier();

        if (type == ExpressionType.ARITHMETIC) {
            if (exp1.type != ExpressionType.ARITHMETIC || exp2.type != ExpressionType.ARITHMETIC){
                throw new TypeMismatchException(this.getNoLigne(),
                        "Attempt to perform a binary arithmetic operation on non arithmetic operands.");
            }
        }
    }

    @Override
    public String toMIPS() {
        String mips = exp1.toMIPS();
        mips += "\taddi $sp, $sp, -4\n"; // Reserve stack
        mips += "\tsw $v0, "+ 4 +"($sp)\n";
        mips += exp2.toMIPS(); // exp2 is in v0
        mips += "\tlw $t8, "+ 4 +"($sp)\n"; // load exp1 to t8
        mips += "\taddi $sp, $sp, 4\n"; // Clear stack

        // t8 and v0 now contain exp1 and exp2 respectively
        switch(op){
            case PLUS:
                mips += "\t# Addition:\n";
                mips += "\tadd $v0, $t8, $v0\n"; // Addition
                break;
            case MINUS:
                mips += "\t# Substraction:\n";
                mips += "\tsub $v0, $t8, $v0\n"; // Subtraction
                break;
            case MULTIPLY:
                mips += "\t# Multiplication:\n";
                mips += "\tmul $v0, $t8, $v0\n"; // Multiplication (No overflow!)
                break;
            case DIVIDE:
                mips += "\t# Division:\n";
                mips += "\t# test si on divise par 0\n";
                mips += "\tbeqz $v0, div_by_zero\n";
                mips += "\tdiv $t8, $v0\n"; // Division
                mips += "\tmflo $v0\n"; // Get quotient
                break;
            case GREATER:
                mips += "\tslt $v0, $v0, $t8\n"; // Greater then ($v0 is set to 1 if $t8 > $v0, 0 otherwise)
                break;
            case LESS:
                mips += "\t# Less:\n";
                mips += "\tslt $v0, $t8, $v0\n"; // Less then ($v0 is set to 1 if $t8 < $v0, 0 otherwise)
                break;
            case EQUAL:
                mips += "\t# Equal:\n";
                mips += "\tsub $v0, $t8, $v0\n"; // Simulate equal operation with subtraction (0 if $t8 == $v0, 1 otherwise)
                mips += "\tnor $v0, $v0, $v0\n"; // Flip $v0 using nor
                break;
            case NEQUAL:
                mips += "\t# Not Equal:\n";
                mips += "\tsub $v0, $t8, $v0\n"; // Simulate not equal operation with subtraction (0 if $t8 == $v0, 1 otherwise)
                break;
            case AND:
                mips += "\t# And:\n";
                mips += "\tand $v0, $t8, $v0\n"; // AND
                break;
            case OR:
                mips += "\t# Or:\n";
                mips += "\tor $v0, $t8, $v0\n"; // OR
                break;
            default:
                break;
        }

        return mips;
    }

    public OperatorsTypes getOp() {
        return op;
    }
}
