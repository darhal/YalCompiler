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

/**
 * Class Equal
 */

public class Equal extends ComparisonOperation {
    public Equal(Expression e1, Expression e2, int n) {
        super(e1, e2, OperatorsTypes.EQUAL, n);
    }

    @Override
    public void verifier() {
        exp1.verifier();
        exp2.verifier();

        if (exp1.type != exp2.type) {
            throw new TypeMismatchException(this.getNoLigne(), "Tentative d'effectuer une opération de comparaison de deux types différents ou l'expression est ambiguë, veuillez utiliser des parenthèses ()");
        }

        // Vérifiez qu'aucun tableau n'est impliqué dans notre expression:
        if (exp1.getVariableType() == VariableType.IDENTIFIANT && exp2.getVariableType() == VariableType.IDENTIFIANT) {
            Entry varEntry1 = ((Variable)exp1).getEntree();
            Symbole varSymbole1 = TDS.Instance().Identify(varEntry1);
            Entry varEntry2 = ((Variable)exp2).getEntree();
            Symbole varSymbole2 = TDS.Instance().Identify(varEntry2);

            if ((varSymbole1.getType() == Decltype.ARRAY && varSymbole2.getType() != Decltype.ARRAY) ||
                (varSymbole1.getType() != Decltype.ARRAY && varSymbole2.getType() == Decltype.ARRAY)) {
                throw new AnalyseSemantiqueException(noLigne, "Tentative d'effectuer une opération de comparaison de deux types différents, ou l'expression est ambiguë, veuillez utiliser des parenthèses ()");
            }
        }
    }

    /**
     * Fonction toMips pour générer le code toMips avec Equal
     * @return
     */
    @Override
    public String toMIPS() {
        String mips = super.toMIPS();
        // t8 and v0 now contain exp1 and exp2 respectively
        mips += "\t# Equal:\n";

        if (exp1.getVariableType() != VariableType.IDENTIFIANT){
            if (exp1.getType() == ExpressionType.ARITHMETIC){
                mips += "\tseq $v0, $t8, $v0\n"; // If t8 == v0 then put 1 in v0 otherwise 0
            }else if (exp1.getType() == ExpressionType.LOGIC){
                mips += "\tsne $v0, $v0, $zero \t# ($v0 != $zero) ? 1 : 0\n"+
                        "\tsne $t8, $t8, $zero \t# ($t8 != $zero) ? 1 : 0\n"+
                        "\tseq $v0, $t8, $v0\n"; // If t8 == v0 then put 1 in v0 otherwise 0
            }
        } else {
            Variable v1 = (Variable)exp1;
            Entry varEntry1 = ((Variable)exp1).getEntree();
            Symbole varSymbole1 = TDS.Instance().Identify(varEntry1);

            if (v1.getVariableType() == VariableType.IDENTIFIANT) {
                if (varSymbole1.getType() == Decltype.ARRAY) {
                    mips += "\tjal compare_arr\n";
                }else{
                    mips += "\tseq $v0, $t8, $v0\n"; // If t8 == v0 then put 1 in v0 otherwise 0
                }
            }
        }

        return mips;
    }
}
