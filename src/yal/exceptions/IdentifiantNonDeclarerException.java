package yal.exceptions;

public class IdentifiantNonDeclarerException extends AnalyseSemantiqueException
{
    public IdentifiantNonDeclarerException(int ligne){
        super(ligne, " Identifiant non declaré.");
    }
}
