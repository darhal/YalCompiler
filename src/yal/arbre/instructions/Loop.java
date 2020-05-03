package yal.arbre.instructions;

import yal.arbre.ArbreAbstrait;
import yal.arbre.expressions.Expression;
import yal.arbre.expressions.ExpressionType;
import yal.declaration.TDS;
import yal.exceptions.AnalyseSemantiqueException;
import java.util.UUID;

/**
 * Class Loop
 */
public class Loop extends Instruction
{
    protected ArbreAbstrait inst;
    protected Expression exp;

    public Loop(Expression exp, ArbreAbstrait inst, int n)
    {
        super(n);
        this.inst = inst;
        this.exp = exp;
    }

    public void verifier()
    {
        exp.verifier();
        inst.verifier();

        if (exp.getType() != ExpressionType.LOGIC){
            throw new AnalyseSemantiqueException(this.noLigne, "La condition de boucle doit être une expression logique");
        }
    }

    /**
     * Fonction toMips pour générer le code toMips pour les boucles
     * @return
     */
    public String toMIPS()
    {
        long random = TDS.Instance().uniqueString(); // generation d'un UUID unique
        String start = "start_loop_"+random;
        String end = "end_loop_"+random;
        String mips = "";
        mips += start+":\n";
        mips += exp.toMIPS();
        mips += "\t# Branch Instruction:\n";
        mips += "\tbeq $v0, $zero, "+end+"\n";
        mips += "\t# Loop instructions:\n";
        mips += inst.toMIPS();
        mips += "\tbne $v0, $zero, "+start+"\n";
        mips += end+":\n";
        return mips;
    }
}
