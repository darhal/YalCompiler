package yal.arbre.expression.binaire;

import yal.arbre.expression.Expression;

public abstract class Binaire extends Expression {
    
    protected Expression gauche ;
    protected Expression droite ;

    protected Binaire(Expression gauche, Expression droite) {
        super(gauche.getNoLigne());
        this.gauche = gauche;
        this.droite = droite;
    }
    
    public abstract String operateur() ;

    @Override
    public String toString() {
        return "(" + gauche + operateur() + droite + ")" ;
    }
    
    @Override
    public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# opération ");
		sb.append(operateur());
		
		sb.append(", calcul de gauche\n");
		sb.append(gauche.toMIPS());
		
		sb.append("# empiler gauche\n");
		
		sb.append("sw $v0, 0($sp)\n");
		sb.append("addi $sp, $sp, -4\n");
		
		sb.append("# calcul de droite\n");
		sb.append(droite.toMIPS());
		
		sb.append("# dépile gauche dans $t8\n");
		sb.append("addi $sp, $sp, 4\n");
		sb.append("lw $t8, 0($sp)\n");
		return sb.toString();
    }

}
