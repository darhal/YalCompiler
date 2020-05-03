package yal.arbre.expressions;

import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Class ArrayElement
 */
public class ArrayElement extends Variable
{
    protected Expression exp;

    public ArrayElement(Entry e, Expression exp, int line)
    {
        super(e, line);
        this.entree = e;
        this.exp = exp;
    }

    @Override
    public void verifier()
    {
        exp.verifier();
        Symbole s = TDS.Instance().Identify(entree);

        if (s.getType() != Decltype.ARRAY){
            throw new AnalyseSemantiqueException(noLigne, "Tentative d'accéder à un élément du tableau alors que '"+ entree.getIdentifier()+"' n'est pas un tableau");
        }

        if (exp.getType() != ExpressionType.ARITHMETIC){
            throw new AnalyseSemantiqueException(exp.getNoLigne(), "L'indice du tableau '"+entree.getIdentifier()+"'est une valeur non arithmétique");
        }

        if (exp.getVariableType() == Expression.VariableType.IDENTIFIANT) {
            Variable var = (Variable) exp;
            Entry varEntry = var.getEntree();
            Symbole varSymbole = TDS.Instance().Identify(varEntry);

            if (varSymbole.getType() == Decltype.ARRAY){
                throw new AnalyseSemantiqueException(noLigne, "L'indice du tableau '"+entree.getIdentifier()+"' est une valeur non arithmétique");
            }
        }
    }


    /**
     * Fonction toMips pour générer le code toMips
     * @return
     */
    @Override
    public String toMIPS()
    {
        String mips = "";
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();
        mips += exp.toMIPS()+
                "\n\t# Get the address of the array '"+entree.getIdentifier()+"':\n"+
                "\tmove $a0, $v0 \t# Save the index in $a0\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"+
                "\tlw $v0, "+offset+"($t1)\n"+
                "\tjal get_arr_element_value\n"
        ;
        return mips;
    }

    public Expression getExpression() {
        return exp;
    }


    @Override
    public VariableType getVariableType() { return VariableType.ARRAY_ELEMENT; }
}
