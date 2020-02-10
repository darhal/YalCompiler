package yal.arbre.expression.binaire.arithmetique;

import yal.arbre.expression.Expression;
import yal.outils.EtiquetteFactory;


public class Div extends BinaireArithmetique {

    public Div(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " / ";
    }
    
	@Override
	public void verifier() {
		super.verifier();
		// Appelle la fabrique à étiquette pour générer le code d'affichage d'erreur en MIPS
		EtiquetteFactory.getInstance().setIndexDiv0();
	}
    
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("# test si on divise par 0\n");
		sb.append("beqz $v0, divByZero\n");
		sb.append("# division gauche droite\n");
		sb.append("div $v0, $t8, $v0\n");
		return sb.toString();
	}
}
