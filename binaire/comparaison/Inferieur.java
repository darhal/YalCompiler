package yal.arbre.expression.binaire.comparaison;

import yal.arbre.expression.Expression;

public class Inferieur extends Comparaison {

    public Inferieur(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " < ";
    }

	@Override
	public void verifier() {
		this.gauche.verifier();
		this.droite.verifier();
		
	}
    
    @Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		int indexEtiquette = getEtiquette();
		sb.append(super.toMIPS());
		
		sb.append("# Début comparaison inférieur\n");
		sb.append("Si"+ indexEtiquette +":\n");
		sb.append("# Soustraction des 2 variables comparées\n");
		sb.append("sub $v0, $t8, $v0\n");
		sb.append("# Comparaison à 0 du résultat\n");
		sb.append("bltz $v0, Alors" + indexEtiquette +"\n");
		sb.append("# Sinon supérieur ou égal à 0 renvoie false\n");
		sb.append("Sinon"+ indexEtiquette +":\n");
		sb.append("li $v0, 0\n");
		sb.append("b Fin"+ indexEtiquette +"\n");
		sb.append("# Si inférieur à 0 renvoie true\n");
		sb.append("Alors"+ indexEtiquette +":\n");
		sb.append("li $v0, 1\n");
		sb.append("Fin"+ indexEtiquette +":\n");
				
		return sb.toString();
	}
}
