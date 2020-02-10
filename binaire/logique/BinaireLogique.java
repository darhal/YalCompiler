package yal.arbre.expression.binaire.logique;

import yal.arbre.expression.Expression;
import yal.arbre.expression.binaire.Binaire;


public abstract class BinaireLogique extends Binaire {

    protected BinaireLogique(Expression gauche, Expression droite) {
        super(gauche, droite) ;

    }
    
	@Override
	public void verifier() {
		this.gauche.verifier();
		this.droite.verifier();
		
	}
}
