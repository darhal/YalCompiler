package yal.arbre.instructions;

import yal.arbre.expressions.*;

public class Return extends Instruction {
    private Expression exp;
    private int numBloc;

    public Return(Expression exp,int no,int numBloc){
        super(no);
        this.exp = exp;
        this.numBloc = numBloc;
    }

    @Override
    public void verifier() {
        exp.verifier();
    }
    @Override
    public String toMIPS() {
        String mips = "Retour de la fonction"+":\n";
        mips += exp.toMIPS();
        mips += "sw $v0, 16($s7) \n";
        mips += "Sortie de Fonction "+ numBloc+" \n";
        return mips;
    }

}

