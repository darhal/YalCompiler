package yal.arbre.instructions;

import yal.arbre.expressions.*;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Class Affectation
 */
public class Affectation extends Instruction
{
    protected Variable idf ;
    protected Expression exp;

    public Affectation(Variable idf, Expression exp, int n) {
        super(n) ;
        this.idf = idf;
        this.exp = exp;
    }

    @Override
    public void verifier() {
        // Verify that entry is declared
        if (idf == null){
            throw new AnalyseSemantiqueException(noLigne, "Tentative d'affecter un identifiant non variable à une expression");
        }

        // Verify that expression is valid
        idf.verifier();
        exp.verifier();

        Entry idfEntry = idf.getEntree();
        Symbole idfSymbole = TDS.Instance().Identify(idfEntry);

        // We are sure we are assigning a plain array to something
        if (idfSymbole.getType() == Decltype.ARRAY && idf.getVariableType() == Expression.VariableType.IDENTIFIANT) {
            if (exp.getVariableType() == Expression.VariableType.IDENTIFIANT) { // if its not an expression
                Variable var = (Variable) exp;
                Entry varEntry = var.getEntree();
                Symbole varSymbole = TDS.Instance().Identify(varEntry);

                if (varSymbole.getType() != Decltype.ARRAY) {
                    throw new AnalyseSemantiqueException(noLigne, "Tentative d'affecter une variable non tableau '" + varEntry.getIdentifier() + "', à une variable de tableau '" + idfEntry.getIdentifier() + "'");
                }
            } else {
                throw new AnalyseSemantiqueException(noLigne, "Tentative d'affecter une expression non tableau à une variable tableau '" + idfEntry.getIdentifier() + "'");
            }
        } else {
            if (exp.getVariableType() == Expression.VariableType.IDENTIFIANT) {
                Variable var = (Variable) exp;
                Entry varEntry = var.getEntree();
                Symbole varSymbole = TDS.Instance().Identify(varEntry);

                if (varSymbole.getType() == Decltype.ARRAY){
                    throw new AnalyseSemantiqueException(noLigne, "Tentative d'attribution du tableau '"+varEntry.getIdentifier()+"' à la variable '"+idfEntry.getIdentifier()+"'");
                }
            }
        }
    }

    /**
     * Fonction toMips pour générer le code toMips
     * @return
     */
    @Override
    public String toMIPS() {
        String mips = "";

        Entry entree = idf.getEntree();
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();

        mips += exp.toMIPS();
        mips += "\n\t# Assignement for the variable '"+entree.getIdentifier()+"':\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"
        ;

        if (s.getType() == Decltype.ARRAY) {
            if (idf.getVariableType() == Expression.VariableType.ARRAY_ELEMENT){
                ArrayElement ae = (ArrayElement) idf;
                mips += "\tmove $t3, $v0\n" +
                        "\tlw $v0, " + offset + "($t1)\n" +
                        "\taddi $sp, $sp, -4\n" + // Reserve stack
                        "\tsw $v0, "+ 4 +"($sp)\n" +
                        ae.getExpression().toMIPS() +
                        "\tmove $a0, $v0\n" +
                        "\tlw $v0, "+ 4 +"($sp)\n" +
                        "\taddi $sp, $sp, 4\n" +
                        "\tjal get_arr_element_address\n" +
                        "\tsw $t3, ($v0)\n";
            } else { // array = array scenario
                mips += "\tmove $a0, $v0\n" +
                        "\tlw $t0, " + offset + "($t1)\n" +
                        "\tjal memcpy\n";
            }
        } else {
            mips += "\tsw $v0, " + offset + "($t1)\n";
        }

        return mips;
    }

    public Expression getExpression() {
        return exp;
    }
}
