package yal.arbre;

import yal.arbre.expressions.*;

/**/
public class Fonction extends ArbreAbstrait {
    private Identifiant idf;
    private BlocDInstuctions nbbloc;

public Fonction(){
     super();
    }

    @Override
    public void verifier()
    {

    }

    @Override
    public String toMIPS() {
        String mips = "Fonction"+this.idf+":\n";
        //Ajout label
        mips += "fonc"+this.nbbloc+":\n";
        mips +="#Creation de la base de la pile \n";
        mips +="#Empile l'adresse de retour \n";
        mips +="sw $ra,($sp) \n";
        mips +="addi $sp,$sp,-4 \n";
        //Sauver la base locale de la pile (chainage dynamique)
        mips +="#Sauver la base locale de la pile \n";
        mips +="sw $s7,($sp) \n";
        mips +="addi $sp,$sp,-4 \n";
        //Empiler le n° bloc
        mips +="#Empiler le n° bloc \n");
        mips +="li $v0,"+this.nbbloc+"\n";
        mips +="sw $v0,($sp) \n";
        mips +="addi $sp, $sp, -4 \n";
        //Base locale
        mips +="move $s7,$sp \n";
        //Ajout du bloc
        mips +="#Bloc d'instruction de la fonction "+this.idf+" \n";
        //Etique quand retour detecter
        mips +="Sortie de Fonction"+this.nbbloc+":\n");
        //Restaurer le compteur
        mips +="lw $ra, 12($s7) \n";
        //Restaurer le pointeur de pile
        mips +="#Remonte la pile \n";
        mips +="addi $sp, $s7, 12 \n";
        //Retrouver la base locale s7
        mips +="lw $s7, 8($s7) \n";
        //Ajouter le jump du $ra
        mips +="jr $ra \n";
        mips +="fin Fonction"+nbbloc+":\n";

        return mips;


































       // sb.append(bloc.toMIPS());
    }
}

    }

}
