package yal.arbre.instructions;

import yal.arbre.expressions.Identifiant;
import yal.arbre.expressions.Variable;
import yal.declaration.TDS;
import yal.declaration.symbols.Symbol;

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
    }

    @Override
    public String toMIPS()
    {
        StringBuilder sb = new StringBuilder();
        //Décallage avec appel méthode getDécalage de idf
        int decalage = idf.getDecalage();
        String itr ;
        sb.append("# Ecrire dans une variable \n");
        sb.append("li $v0, 5\n");
        sb.append("syscall\n");
        sb.append("# sw au bon endroit\n");
        sb.append("# emplile \n");
        sb.append("sw $v0, 0($sp)\n");
        sb.append("addi $sp, $sp, -4\n");
        sb.append("# Remonte le chainage\n");
        sb.append("move $t8, $s7 \n");
        sb.append(itr + ": \n");
        sb.append("lw $v0, 4($t8) \n");
        sb.append("addi $v1, $zero, " + idf.getNoBloc() + " \n");
        sb.append("sub $v1, $v0, $v1\n");
        sb.append("beqz $v1, fin" + itr + "\n");
        sb.append("lw $t8, 8($t8) \n");
        sb.append("j " + itr + " \n");
        sb.append("fin" + itr + ": \n");
        sb.append("#Dépile \n");
        sb.append("addi $sp, $sp, 4\n");
        sb.append("lw $v0, 0($sp)\n");
        sb.append("sw $v0, " + decalage + "($t8)\n");

        return sb.toString();
    }

}