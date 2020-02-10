package yal.arbre.expression.binaire.arithmetique;

import yal.arbre.expression.Expression;

public class Plus extends BinaireArithmetique {

    public Plus(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " + " ;
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# addition gauche droite\n");
		sb.append("add $v0, $v0, $t8\n");
		return sb.toString();
	}

}
