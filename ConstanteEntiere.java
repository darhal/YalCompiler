package yal.arbre.expressions;

public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

   @Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("#Constante entière\n");
		sb.append("li $v0, ");
		sb.append(this.cste);
		sb.append("\n");
		return sb.toString();
	}

}