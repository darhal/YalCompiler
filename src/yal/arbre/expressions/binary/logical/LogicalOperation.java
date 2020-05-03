package yal.arbre.expressions.binary.logical;

import yal.arbre.OperatorsTypes;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.arbre.expressions.Variable;
import yal.arbre.expressions.binary.BinaryOperation;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;
import yal.exceptions.TypeMismatchException;

/**
 * Class LogicalOperation
 */
public class LogicalOperation extends BinaryOperation {
    public LogicalOperation(Expression e1, Expression e2, OperatorsTypes op, int n) {
        super(e1, e2, op, ExpressionType.LOGIC, n);
    }

    @Override
    public void verifier() {
        exp1.verifier();
        exp2.verifier();

        if (exp1.type != ExpressionType.LOGIC || exp2.type != ExpressionType.LOGIC) {
            throw new TypeMismatchException(this.getNoLigne(), "Tentative d'effectuer une opération logique binaire sur des opérandes non logiques");
        }

        // Verify that there is no arrays involved in our expression:
        if (exp1.getVariableType() == VariableType.IDENTIFIANT) {
            Entry varEntry = ((Variable)exp1).getEntree();
            Symbole varSymbole = TDS.Instance().Identify(varEntry);

            if (varSymbole.getType() == Decltype.ARRAY){
                throw new AnalyseSemantiqueException(noLigne, "Impossible d'effectuer une opération arithmétique/logique ou de comparaison sur le tableau '"+varEntry.getIdentifier()+"'");
            }
        }

        if (exp2.getVariableType() == VariableType.IDENTIFIANT) {
            Entry varEntry = ((Variable)exp2).getEntree();
            Symbole varSymbole = TDS.Instance().Identify(varEntry);

            if (varSymbole.getType() == Decltype.ARRAY){
                throw new AnalyseSemantiqueException(noLigne, "Impossible d'effectuer une opération arithmétique/logique ou de comparaison sur le tableau '"+varEntry.getIdentifier()+"'");
            }
        }
    }
}
