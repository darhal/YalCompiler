package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.outils.EtiquetteFactory;

public class Boucle extends Instruction {

	private BlocDInstructions bi;
	private Expression expr;
	
	public Boucle(Expression e, int no) {
		super(no);
		expr = e;
	}

	public void ajouter(BlocDInstructions b) {
		bi = b;
	}
	
	@Override
	public void verifier() {
		expr.verifier();
		bi.verifier();
	}

	@Override
	public boolean isRetourne(boolean bloc) {
		return bi.isRetourne(bloc);
	}
	
	@Override
	public String toMIPS() {
		// Demande une étiquette
		int indexEtiquette = EtiquetteFactory.getInstance().getIndexTant();
		EtiquetteFactory.getInstance().addIndexTant();
		StringBuilder sb = new StringBuilder();
		sb.append("# Début du Tantque " + indexEtiquette + ": label + évaluation de expr\n");
		sb.append("Tantque" + indexEtiquette + " :\n");
		sb.append(expr.toMIPS());
		sb.append("beqz $v0, FinTantque" + indexEtiquette + "\n");
		sb.append("#Tantque " + indexEtiquette + " bloc instruction\n");
		sb.append(bi.toMIPS());
		sb.append("b Tantque" + indexEtiquette + "\n");
		sb.append("FinTantque" + indexEtiquette + ":\n");
		
		return sb.toString();
	}

}
