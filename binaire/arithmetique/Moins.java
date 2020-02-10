package yal.arbre.expression.binaire.arithmetique;

import yal.arbre.expression.Expression;


public class Moins extends BinaireArithmetique {

    public Moins(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " - ";
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# différence gauche droite\n");
		sb.append("sub $v0, $t8, $v0\n");
		return sb.toString();
	}
}
