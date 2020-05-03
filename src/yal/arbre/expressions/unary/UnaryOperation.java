package yal.arbre.expressions.unary;

import yal.analyse.CodesLexicaux;
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
 * Class UnaryOperation
 */
public class UnaryOperation extends Expression
{
    protected Expression exp;
    protected OperatorsTypes op;

    public UnaryOperation(Expression e, OperatorsTypes op, ExpressionType type, int n)
    {
        super(type, n);
        this.exp = e;
        this.op = op;
    }

    @Override
    public void verifier() {
        exp.verifier();

        // Verifie qu'il n'y a pas de plain arrays  impliqués dans notre expression:
        if (exp.getVariableType() == VariableType.IDENTIFIANT) {
            Entry varEntry = ((Variable)exp).getEntree();
            Symbole varSymbole = TDS.Instance().Identify(varEntry);

            if (varSymbole.getType() == Decltype.ARRAY){
                throw new AnalyseSemantiqueException(noLigne, "Impossible d'effectuer des opérations arithmétiques/logiques sur le tableau '"+varEntry.getIdentifier()+"'");
            }
        }

        if (type == ExpressionType.ARITHMETIC && exp.type != ExpressionType.ARITHMETIC){
            throw new TypeMismatchException(this.getNoLigne(),
                    "Tentative d'effectuer une opération unaire arithmétique sur une expression non arithmétique.");
        }else if (type == ExpressionType.LOGIC && exp.type != ExpressionType.LOGIC){
            throw new TypeMismatchException(this.getNoLigne(),
                    "Tentative d'effectuer une opération unaire logique sur une expression non logique.");
        }
    }

/**
* Fonction toMips pour générer le code toMips
* @return
*/
    @Override
    public String toMIPS() {
        String mips = exp.toMIPS();
        return mips;
    }

    public OperatorsTypes getOp() {
        return op;
    }
}
