package yal;

import java.io.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.declaration.TDS;
import yal.declaration.entries.Entry;
import yal.declaration.symbols.FonctionSymbol;
import yal.declaration.symbols.Symbol;
import yal.exceptions.AnalyseException;

public class Yal {
    
    public Yal(String nomFichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(nomFichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;

            arbre.verifier() ;
            System.out.println("COMPILATION OK") ;

            String nomSortie = nomFichier.replaceAll("[.]yal", ".mips") ;
            PrintWriter flot = new PrintWriter(new BufferedWriter(new FileWriter(nomSortie))) ;
            String code_mips = arbre.toMIPS() +
                    "\n\t# Handle program exit\n"+
                    "exit:\n"+
                    "\tli $v0, 10\n"+
                    "\tsyscall\n"+
                    "\n\t # Handle RUNTIME errors\n"+
                    "div_by_zero:\n"+
                    "\tla $a0, div_by0\n" +
                    "\tli $v0, 4\n" +
                    "\tsyscall\n";

            for (Map.Entry<Entry, Symbol> pair : TDS.Instance().symbolMap.entrySet()) {
                if (pair.getKey().getEntryType() == 0){
                    code_mips += "\n"+((FonctionSymbol)pair.getValue()).getFonction().toMIPS()+"\n";
                }
            }
            flot.println(code_mips);
            flot.close() ;
        } catch (FileNotFoundException ex) {
            System.err.println("Fichier " + nomFichier + " inexistant") ;
        } catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(yal.Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }
        new yal.Yal(args[0]) ;
    }
}
