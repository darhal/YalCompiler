package yal.arbre.expressions.binary.comparison;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.arbre.expressions.Variable;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.TypeMismatchException;

public class NotEqual extends ComparisonOperation {
    public NotEqual(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.NEQUAL, n);
    }

    @Override
    public void verifier() {
        exp1.verifier();
        exp2.verifier();

        if (exp1.type != exp2.type) {
            throw new TypeMismatchException(this.getNoLigne(), "Attempt to perform a comparison operation two different types, or expression is ambiguous please use parentheses ()");
        }

        // Verify that there is no arrays involved in our expression:
        if (exp1.getVariableType() != exp2.getVariableType()) {
            throw new TypeMismatchException(this.getNoLigne(), "Attempt to perform a comparison operation two different types, or expression is ambiguous please use parentheses ()");
        }

        if (exp1.getVariableType() == VariableType.IDENTIFIANT) {
            Entry varEntry1 = ((Variable)exp1).getEntree();
            Symbole varSymbole1 = TDS.Instance().Identify(varEntry1);
            Entry varEntry2 = ((Variable)exp2).getEntree();
            Symbole varSymbole2 = TDS.Instance().Identify(varEntry2);

            if (varSymbole1.getType() != varSymbole2.getType()){
                throw new AnalyseSemantiqueException(noLigne, "Attempt to perform a comparison operation two different types, or expression is ambiguous please use parentheses ()");
            }
        }
    }

    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Not Equal:\n";

        if (exp1.getVariableType() != VariableType.IDENTIFIANT){
            if (exp1.getType() == ExpressionType.ARITHMETIC){
                mips += "\tsne $v0, $t8, $v0\n"; // If t8 != v0 then put 1 in v0 otherwise 0
            }else if (exp1.getType() == ExpressionType.LOGIC){
                mips += "\tsne $v0, $v0, $zero \t# ($v0 != $zero) ? 1 : 0\n"+
                        "\tsne $t8, $t8, $zero \t# ($t8 != $zero) ? 1 : 0\n"+
                        "\tsne $v0, $t8, $v0\n"; // If t8 != v0 then put 1 in v0 otherwise 0
            }
        } else {
            Variable v1 = (Variable)exp1;
            Entry varEntry1 = ((Variable)exp1).getEntree();
            Symbole varSymbole1 = TDS.Instance().Identify(varEntry1);

            if (v1.getVariableType() == VariableType.IDENTIFIANT) {
                if (varSymbole1.getType() == Decltype.ARRAY) {
                    mips += "\tjal compare_arr\n"+
                            "\tseq $v0, $zero, $v0\n";
                }else{
                    mips += "\tsne $v0, $t8, $v0\n"; // If t8 == v0 then put 1 in v0 otherwise 0
                }
            }
        }
        
        return mips;
    }
}
