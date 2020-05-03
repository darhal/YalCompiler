package yal.arbre;

import yal.arbre.expressions.Constante;
import yal.arbre.expressions.ConstanteEntiere;
import yal.arbre.expressions.Expression;
import yal.declaration.Decltype;
import yal.declaration.TDS;
import yal.declaration.TableLocal;
import yal.declaration.entries.Entry;
import yal.declaration.entries.FonctionEntry;
import yal.declaration.symbols.ArraySymbole;
import yal.declaration.symbols.FonctionSymbole;
import yal.declaration.symbols.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

import java.util.Map;

/**
 * Class Debut
 */
public class Debut extends BlocDInstructions
{
    public Debut(int n) {
        super(n);
    }

    @Override
    public void verifier() {
        for (FonctionSymbole fnSymbol : TDS.Instance().fnSymbolMap.values()) {
            fnSymbol.getFonction().verifier();
        }

        for (ArbreAbstrait a : programme) {
            a.verifier();
        }

        TableLocal bloc = TDS.Instance().getCurrentTableLocal();
        for (Map.Entry<Entry, Symbole> es : bloc.getSymbolMap().entrySet()){
            Entry e = es.getKey();
            Symbole s = es.getValue();

            if (s.getType() == Decltype.ARRAY) {
                ArraySymbole as = (ArraySymbole)s;
                Expression exp = as.getExpression();

                if (!exp.isConst()) {
                    throw new AnalyseSemantiqueException(e.getLine(), "Le tableau '"+e.getIdentifier()+"' est défini dans le programme principal avec une taille non constante, les tableaux définis dans main doivent avoir une longueur constante");
                } else {
                    ConstanteEntiere cte = (ConstanteEntiere) exp;

                    if (cte.getCste().equals("0")){
                        throw new AnalyseSemantiqueException(e.getLine(), "Le tableau '"+e.getIdentifier()+"' ne peut pas avoir une longueur de 0 (la taille du tableau doit être strictement positive> 0)");
                    }
                }
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
        String code_mips =
                ".data\n" +
                "\tdiv_by0: .asciiz \"[RUNTIME ERROR]:SEMANTICS: La division par zero est interdite.\\n\"\n" +
                "\tarr_sz_err: .asciiz \"[RUNTIME ERROR]:SEMANTICS: La taille du tableau ne peut pas etre nulle ou negative.\\n\"\n" +
                "\tout_of_bound: .asciiz \"[RUNTIME ERROR]:SEMANTICS: L'indice est en dehors des bornes du tableau.\\n\"\n" +
                "\tnegative_index: .asciiz \"[RUNTIME ERROR]:SEMANTICS: L'indice du tableau ne peut pas etre négatif.\\n\"\n" +
                "\tarr_cpy_err: .asciiz \"[RUNTIME ERROR]:SEMANTICS: Tentative d'effectuer une copie de tableau sur un tableau qui n'a pas la meme taille que celui de source.\\n\"\n" +
                "\ttrue_str: .asciiz \"vrai\\n\"\n" +
                "\tfalse_str: .asciiz \"faux\\n\"\n" +
                ".text\n" +
                "\n"+
                "main:\n" +
                "\t# Begin stackframe:\n" +
                "\tmove $s7, $sp\n";

        if (TDS.Instance().getVariableZoneSize() != 0) {
            // Init variables to 0
            code_mips += "\t# Initialize variables to 0:\n";

            for (int i = 0; i < TDS.Instance().getVariableZoneSize(); i++){
                code_mips += "\tsw $zero, "+i * - 4+"($sp)\n";
            }

            code_mips +=
                    "\t# Allocate for the declared variables:\n" +
                    "\taddi $sp, $sp, " + -4 * TDS.Instance().getVariableZoneSize() + "\n"
                ;
        }

        TableLocal bloc = TDS.Instance().getCurrentTableLocal();
        for (Map.Entry<Entry, Symbole> es : bloc.getSymbolMap().entrySet()){
            Entry e = es.getKey();
            Symbole s = es.getValue();
            int offset = -4 * s.getOffset();

            if (s.getType() == Decltype.ARRAY) {
                ArraySymbole as = (ArraySymbole)s;

                code_mips += "\n\t# Calculating the size of the array '"+e.getIdentifier()+"' (size in $v0): "+
                             as.getExpression().toMIPS()+
                             "\tmove $a0, $v0\n"+
                             "\tjal allocate_array\n"+
                             "\tsw $v0, "+offset+"($s7)\n";
            }
        }

        for (ArbreAbstrait a : programme) {
            code_mips += a.toMIPS();
        }

        return code_mips;
    }
}
