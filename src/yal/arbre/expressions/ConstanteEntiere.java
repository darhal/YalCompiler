package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {
        return  "\t#constate entiere "+this.cste+"\n"+
                "\taddi $sp, $sp, -4 \t# Reserving 4 bytes on the stack for the constant\n"+
                "\tli $t8, "+this.cste+"\n"+
                "\tsw $t8, -4($sp)\n"
                ;
    }

}
