package yal.arbre.expression.unaire;

import yal.arbre.expression.Expression;


public abstract class Unaire extends Expression {
    
    protected Expression expression ;

    protected Unaire(Expression expr) {
        super(expr.getNoLigne());
        expression = expr ;
    }
    
    public abstract String operateur() ;

    @Override
    public String toString() {
        return "(" + operateur() + expression + ")" ;
    }
    
    @Override
    public String toMIPS() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("# opération ");
    	sb.append(operateur());

    	sb.append(", calcul de l'expression\n");
    	sb.append(expression.toMIPS());

    	return sb.toString();
    }

}
