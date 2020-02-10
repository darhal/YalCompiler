package yal.arbre.expression.binaire.comparaison;

import yal.arbre.expression.Expression;
import yal.arbre.expression.binaire.Binaire;
import yal.outils.EtiquetteFactory;

public abstract class Comparaison extends Binaire {
     
    protected Comparaison(Expression gauche, Expression droite) {
        super(gauche, droite);
        this.setReturnType("bool");
    }
    
    public int getEtiquette() {
		int etiq = EtiquetteFactory.getInstance().getIndexSi();
		EtiquetteFactory.getInstance().addIndexSi();
		return etiq;
	}

}
