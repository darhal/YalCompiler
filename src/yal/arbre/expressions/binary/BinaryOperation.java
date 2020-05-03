package yal.arbre.expressions.binary;

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
 * Class BinaryOperation
 */
public class BinaryOperation extends Expression
{
    protected Expression exp1;
    protected Expression exp2;
    protected OperatorsTypes op;

    public BinaryOperation(Expression e1, Expression e2, OperatorsTypes op, ExpressionType type, int n)
    {
        super(type, n);
        this.exp1 = e1;
        this.exp2 = e2;
        this.op = op;
    }

    @Override
    public void verifier() {
        exp1.verifier();
        exp2.verifier();

        if (type == ExpressionType.ARITHMETIC) {
            if (exp1.type != ExpressionType.ARITHMETIC || exp2.type != ExpressionType.ARITHMETIC){
                throw new TypeMismatchException(this.getNoLigne(), "Tentative d'effectuer une opération arithmétique binaire sur des opérandes non arithmétiques");
            }
        }

        /*if (type == ExpressionType.LOGIC) {
            if (exp1.type != ExpressionType.LOGIC || exp2.type != ExpressionType.LOGIC){
                throw new TypeMismatchException(this.getNoLigne(), "Attempt to perform a binary arithmetic operation on non arithmetic operands");
            }
        }*/

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
    /**
     * Fonction toMips pour générer le code toMips avec BinaryOperation
     * @return
     */
    @Override
    public String toMIPS() {
        String mips = exp1.toMIPS();
        mips += "\taddi $sp, $sp, -4\n"; // Reserve stack
        mips += "\tsw $v0, "+ 4 +"($sp)\n";
        mips += exp2.toMIPS(); // exp2 is in v0
        mips += "\tlw $t8, "+ 4 +"($sp)\n"; // load exp1 to t8
        mips += "\taddi $sp, $sp, 4\n"; // Clear stack

        // t8 and v0 now contain exp1 and exp2 respectively
        return mips;
    }

    public OperatorsTypes getOp() {
        return op;
    }
}
