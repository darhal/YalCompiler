package yal.arbre.expression.binaire.logique;

import yal.arbre.expression.Expression;


public class EtLogique extends BinaireLogique {

    public EtLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " et " ;
    }
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# et logique gauche droite\n");
		sb.append("and $v0, $t8, $v0\n");
		return sb.toString();
	}
}
