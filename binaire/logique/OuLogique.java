package yal.arbre.expression.binaire.logique;

import yal.arbre.expression.Expression;

public class OuLogique extends BinaireLogique {

    public OuLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " ou " ;
    }
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# ou logique gauche droite\n");
		sb.append("or $v0, $t8, $v0\n");
		return sb.toString();
	}
    
}
