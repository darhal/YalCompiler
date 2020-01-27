package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.arbre.expression.idf.IDFVar;
import yal.exceptions.ListeErreursSemantiques;
import yal.outils.EtiquetteFactory;

public class Affectation extends Instruction{

	private Expression exp;
	private IDFVar idf;
	
	public Affectation(IDFVar idf, Expression aff, int no) {
		super(no);
		this.exp = aff;
		this.idf = idf;
		
	}

	@Override
	public void verifier() {
		// Vérifie que la variable est déclarée
		idf.verifier();
		
		// Vérifie si les types sont compatibles
		if (!idf.getReturnType().equals(exp.getReturnType())) {
			ListeErreursSemantiques.getInstance().addErreur(this.noLigne, "Affectation de type \"" + exp.getReturnType() + "\" à la variable \"" + idf.getNom() + "\" de type \"" + idf.getReturnType() + "\"");
		}
		exp.verifier();
	}

	@Override
	public String toMIPS() {
		String itr = EtiquetteFactory.getInstance().getItr();
		int decalage = idf.getDecalage();
		StringBuilder sb = new StringBuilder();

		sb.append("# calcul de la valeur a affecter à " + idf.getNom() + "\n");
		sb.append(exp.toMIPS());
		sb.append("# emplile \n");
		sb.append("sw $v0, 0($sp)\n");
		sb.append("addi $sp, $sp, -4\n");
		sb.append("# Remonte le chainage\n");
		sb.append("move $t8, $s7 \n");
		sb.append(itr + ": \n");
		sb.append("lw $v0, 4($t8) \n");
		sb.append("addi $v1, $zero, " + idf.getNoBloc() + " \n");
		sb.append("sub $v1, $v0, $v1\n");
		sb.append("beqz $v1, fin" + itr + "\n");
		sb.append("lw $t8, 8($t8) \n");
		sb.append("j " + itr + " \n");
		sb.append("fin" + itr + ": \n");
		sb.append("#Dépile \n");
		sb.append("addi $sp, $sp, 4\n");
		sb.append("lw $v0, 0($sp)\n");
		sb.append("sw $v0, " + decalage + "($t8)\n");
		return sb.toString();
	}

}
