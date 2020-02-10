package yal.arbre.expression.binaire.arithmetique;

import yal.arbre.expression.Expression;
import yal.arbre.expression.binaire.Binaire;

public abstract class BinaireArithmetique extends Binaire {

	protected BinaireArithmetique(Expression gauche, Expression droite) {
		super(gauche, droite) ;
		this.setReturnType("int");
	}

	@Override
	public void verifier() {
		this.gauche.verifier();
		this.droite.verifier();
	
	}

}
