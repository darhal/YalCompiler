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
        if (type == ExpressionType.ARITHMETIC && (exp1.type == ExpressionType.LOGIC || exp2.type == ExpressionType.LOGIC)) {
            throw new TypeMismatchException(this.getNoLigne(),
                    "Attempt to perform a binary arithmetic operation on non arithmetic operands.");
        }

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
                int random = (int)(Math.random() * 10000 + 1);
                mips += "\t# Début comparaison supérieur\n";
                mips += "si"+ random +":\n";
                mips += "\t# Soustraction des 2 variables comparées\n";
                mips += "\tsub $v0, $t8, $v0\n";
                mips += "\t# Comparaison à 0 du résultat\n";
                mips += "\tbgtz $v0, alors" + random +"\n";
                mips += "\t# Sinon inférieur ou égal à 0 renvoie false\n";
                mips += "sinon"+ random +":\n";
                mips += "\tli $v0, 0\n";
                mips += "\tb Fin"+ random +"\n";
                mips += "\t# Si supérieur à 0 renvoie true\n";
                mips += "alors"+ random +":\n";
                mips += "\tli $v0, 1\n";
                mips += "Fin"+ random +":\n";
                break;
            case LESS:
                mips += "\t# Less:\n";
                mips += "\tslt $v0, $t8, $v0\n"; // Less then ($v0 is set to 1 if $t8 < $v0, 0 otherwise)
                break;
            case EQUAL:
                mips += "\t# Equal:\n";
                mips += "\tsub $v0, $t8, $v0\n"; // Simulate equal operation with subtraction (0 if $t8 == $v0, 1 otherwise)
                mips += "\txori $v0, $v0, 1\n"; // Flip $v0 using xori
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
