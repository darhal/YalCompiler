package yal.arbre.expressions;

import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.entries.VariableEntry;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

/**
 * Class ArrayLongeur
 */
public class ArrayLongeur extends Expression
{
    private VariableEntry entree;

    public ArrayLongeur(VariableEntry entree,  int n)
    {
        super(ExpressionType.ARITHMETIC, n);
        this.entree = entree;
    }

    @Override
    public void verifier() {
        Symbole s = TDS.Instance().Identify(entree);

        if (s.getType() != Decltype.ARRAY){
            throw new AnalyseSemantiqueException(noLigne, "Tentative de mesurer la longueur d'une variable qui n'es pas un tableau '"+ entree.getIdentifier()+"', 'longeur' l'opérateur ne peut être exécuté que sur un tableau.");
        }
    }


    /**
     * Fonction toMips pour générer le code toMips
     * @return
     */
    @Override
    public String toMIPS() {
        Symbole s = TDS.Instance().Identify(entree);
        int offset = -4 * s.getOffset();

        String mips =
                "\n\t# Get the address of the array '"+entree.getIdentifier()+"':\n"+
                "\tmove $a0, $v0 \t# Save the index in $a0\n"+
                "\tli $t2, "+s.getNoBloc()+"\n"+
                "\tjal search_var\n"+
                "\tlw $v0, "+offset+"($t1)\n"+
                "\tlw $v0, 0($v0) \t# Get the size of the array\n"+
                "\tli $t4, 4\n"+
                "\tdiv $v0, $t4\n"+
                "\tmflo $v0\n"
        ;

        return mips;
    }
}
