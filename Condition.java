package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.outils.EtiquetteFactory;

public class Condition extends Instruction {
	
	private BlocDInstructions blocSi;
	private BlocDInstructions blocSinon;
	private Expression expr;

	public Condition(Expression e, int no) {
		super(no);
		expr = e;
	}
	
	public void ajouterSi(BlocDInstructions bi) {
		blocSi = bi;
	}
	
	public void ajouterSinon(BlocDInstructions bi) {
		blocSinon = bi;
	}
	
	@Override
	public void verifier() {
		expr.verifier();
		if (blocSi != null) {
			blocSi.verifier();
		}
		if (blocSinon != null) {
			blocSinon.verifier();
		}
	}

	@Override
	public String toMIPS() {
		// Demande une étiquette
		int indexEtiquette = EtiquetteFactory.getInstance().getIndexSi();
		EtiquetteFactory.getInstance().addIndexSi();
		StringBuilder sb = new StringBuilder();
		sb.append("# Début du Si " + indexEtiquette + ": évaluation de expr\n");
		sb.append(expr.toMIPS());
		sb.append("beqz $v0, Sinon" + indexEtiquette + "\n");
		
		sb.append("#Si " + indexEtiquette + " bloc si\n");
		if (blocSi != null) {
			sb.append(blocSi.toMIPS());
		}
		sb.append("b FinSi" + indexEtiquette + "\n");
		
		sb.append("#Si " + indexEtiquette + " bloc sinon\n");
		sb.append("Sinon" + indexEtiquette + ":\n");
		if (blocSinon != null) {
			sb.append(blocSinon.toMIPS());
		}
		
		sb.append("FinSi" + indexEtiquette + ":\n");
		
		return sb.toString();
	}

}
