package yal.arbre.expressions;


/**
 * Class ConstanteEntiere
 */
public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    /**
     * Fonction toMips pour générer le code toMips
     * @return
     */
    @Override
    public String toMIPS() {
        return  "\n\t# Integer Constant '"+this.cste+"':\n"+
                "\tli $v0, "+this.cste+"\n"
                ;
    }

    @Override
    public boolean isConst() { return true; }
}
