package yal.arbre.expression.unaire;

import yal.arbre.expression.Expression;

public class NonLogique extends Unaire {
    
    public NonLogique(Expression expr) {
        super(expr);
        
    }

    @Override
    public String operateur() {
        return " non " ;
    }

	@Override
	public void verifier() {
		
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toMIPS());
		sb.append("#non logique\n");
		//Impossible d'utiliser not qui est une macro inversant tout les bits, on utilise un non exclusif avec 1
		sb.append("xori $v0, $v0, 1\n");
		return sb.toString();
	}
    
}
